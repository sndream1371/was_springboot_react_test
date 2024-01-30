package com.wayside.was.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.wayside.was.model.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  // 일반적인 예외
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {
    return new ResponseEntity<>(ApiResponse.failure(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // 데이터 검증 실패 (예: @Valid 사용 시)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    ApiResponse<Object> response = ApiResponse.failure("Validation failed");
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  // HTTP 메소드 지원하지 않을 때
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiResponse<Object>> handleMethodNotSupportedException(
      HttpRequestMethodNotSupportedException ex) {
    ApiResponse<Object> response = ApiResponse.failure("HTTP method not supported");
    return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
  }

  // 매핑되는 핸들러 없을 때 (404 에러)
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ApiResponse<Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
    ApiResponse<Object> response = ApiResponse.failure("No handler found for your request");
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
  }

  // 사용자 정의 예외 처리
  // @ExceptionHandler(CustomException.class)
  // public ResponseEntity<ApiResponse<Object>>
  // handleCustomException(CustomException ex) {
  // ApiResponse<Object> response = ApiResponse.failure(ex.getMessage());
  // return new ResponseEntity<>(response, ex.getStatus());
  // }

  // 기타 예외 핸들러들...
}
