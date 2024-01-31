package com.wayside.was.aspect;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wayside.was.model.log.RequestResponseLog;
import com.wayside.was.service.LogService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;

@Aspect
@Component
public class LoggingAspect {
  private final LogService logService;

  private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

  @Autowired
  public LoggingAspect(LogService logService) {
    this.logService = logService;
  }

  @Autowired
  private Environment env;

  // @Around("execution(* com.wayside.was..*(..)) &&
  // @annotation(org.springframework.web.bind.annotation.RequestMapping)")
  // <-이설정으로만 안됨 아래설정으로 됨

  @Around("execution(* com.wayside.was..*(..)) && " +
      "(   @annotation(org.springframework.web.bind.annotation.RequestMapping) " +
      "|| @annotation(org.springframework.web.bind.annotation.GetMapping) " +
      "|| @annotation(org.springframework.web.bind.annotation.PostMapping) )")
  public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
        .getRequest();

    // application.properties에서 값을 가져옵니다.
    String isEnabled = env.getProperty("aop.logging.enabled", "false");

    logger.info("AOP [" + isEnabled + "]");

    if (!Boolean.parseBoolean(isEnabled)) {
      // AOP 로직 비활성화 - 메서드 실행만 하고 빠져나감
      logger.info("AOP is disabled..");
      Object result = joinPoint.proceed();
      return result;
    }

    logger.info("AOP is working. Method: " + joinPoint.getSignature().toShortString());
    logger.info("AOP is working. request.getMethod(): " + request.getMethod());

    String methodType = request.getMethod();
    String requestParams = "";

    // GET 요청 처리: 쿼리 스트링 사용
    if ("GET".equalsIgnoreCase(methodType)) {
      requestParams = request.getQueryString();
      logger.info("AOP is working. requestParams: " + requestParams);
    }
    // POST, PUT, DELETE 요청 처리: JSON 본문 처리
    else if ("POST".equalsIgnoreCase(methodType) || "PUT".equalsIgnoreCase(methodType)
        || "DELETE".equalsIgnoreCase(methodType)) {
      Object[] args = joinPoint.getArgs();
      for (Object arg : args) {
        // if (arg instanceof RequestResponseLog) { // 요청 본문의 클래스
        if (arg.getClass().getPackage().getName().equals("com.wayside.was.model")) { // 패키지 폴더에 정의된 모델의 클래스
          ObjectMapper mapper = new ObjectMapper();
          requestParams = mapper.writeValueAsString(arg);
          break; // JSON 문자열을 찾았으므로 반복 중단
        }
      }
    }

    // 요청 처리 전 로그
    RequestResponseLog log = new RequestResponseLog();
    log.setRequestMethod(request.getMethod());
    log.setRequestUri(request.getRequestURI());
    log.setRequestBody(requestParams); // 요청 본문
    log.setTimestamp(LocalDateTime.now());

    Object result = joinPoint.proceed(); // 실제 메소드 실행

    // 응답 처리 후 로그
    if (result instanceof ResponseEntity) {
      ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
      // log.setResponseBody(responseEntity.getBody().toString());
      Object responseBody = responseEntity.getBody();
      log.setResponseBody(responseBody.toString());
    }

    logService.logRequestResponse(log);

    return result;
  }
}
