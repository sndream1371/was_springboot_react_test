package com.wayside.was.controller;

import com.wayside.was.model.ApiResponse;
import com.wayside.was.model.EmpDto;
import com.wayside.was.service.EmpService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EmpController {

  private static final Logger logger = LogManager.getLogger(EmpController.class);
  private final EmpService empService;

  // 생성자 주입을 사용
  // @Autowired
  public EmpController(EmpService empService) {
    this.empService = empService;
  }

  // 모든 직원 조회
  @GetMapping("/api/emp/all")
  public List<EmpDto> findAll() {
    return empService.findAll();
  }

  // 특정 기준에 따른 직원 조회 (param을 사용하여 필터링하는 로직이 필요)
  @GetMapping("/api/emp/{id}")
  public EmpDto getEmpByParam(@PathVariable Long id) {
    // 여기에 param을 사용하여 Emp를 조회하는 로직을 구현
    logger.info("request id :" + id);
    EmpDto emp = empService.findById(id);
    return emp;
  }

  // 쿼리 파라미터로 ID를 받는 GET 요청 (예: /api/employees?id=123)
  @GetMapping("/api/emp")
  public ResponseEntity<EmpDto> getEmployeeById(@RequestParam Long id) {
    EmpDto employee = empService.findByParamId(id);
    if (employee != null) {
      return ResponseEntity.ok(employee);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // POST방식
  // 입력파라미터에 단문으로만 요청
  @PostMapping("/api/emp/postFindbyString")
  public ResponseEntity<EmpDto> postEmployeeById(@RequestBody Long empId) {
    EmpDto employee = empService.findByPostId(empId);
    if (employee != null) {
      return ResponseEntity.ok(employee);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // POST방식
  // 입력 파라미터를 EmpDto 형식으로 호출 <- JSON형식으로 호출하는 말과 동일
  @PostMapping("/api/emp/postFindbyJson")
  public ResponseEntity<EmpDto> postEmployeeById(@RequestBody EmpDto empdto) {
    EmpDto employee = empService.findByPostId(empdto.getId());

    logger.info("employee :" + employee.toString());
    System.out.println("employee :" + employee.toString());

    if (employee != null) {
      return ResponseEntity.ok(employee);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/api/emp/empRegister")
  public ResponseEntity<?> registerEmp(@RequestBody EmpDto emp) {

    logger.info("Received emp: {}", emp);

    empService.registerEmp(emp);
    // Map<String, String> response = new HashMap<>();
    // response.put("result", "ok");
    // response.put("result_message", "success Insert");
    // return ResponseEntity.ok(response);
    return ResponseEntity.ok(ApiResponse.success(emp));

  }

  @PostMapping("/api/emp/empsRegister")
  public ResponseEntity<?> registerEmp(@RequestBody List<EmpDto> emps) {

    logger.info("Received emp: {}", emps);

    empService.registerEmps(emps);
    Map<String, String> response = new HashMap<>();
    response.put("result", "ok");
    response.put("result_message", "success Insert");
    return ResponseEntity.ok(response);
  }

}
