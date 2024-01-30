package com.wayside.was.service;

import com.wayside.was.mapper.EmpMapper;
import com.wayside.was.model.EmpDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpService {

  @Autowired
  private final EmpMapper empMapper;

  public EmpService(EmpMapper empMapper) {
    this.empMapper = empMapper;
  }

  public List<EmpDto> findAll() {
    return empMapper.findAll();
  }

  public EmpDto findById(Long id) {
    return empMapper.findById(id);
  }

  public EmpDto findByParamId(Long id) {
    return empMapper.findByParamId(id);
  }

  public EmpDto findByPostId(Long id) {
    return empMapper.findByPostId(id);
  }

  @Transactional
  public void registerEmp(EmpDto emp) {
    empMapper.insertEmp(emp);
  }

  // 사용자 정보를 json array형태로 받을경우 처리
  @Transactional
  public void registerEmps(List<EmpDto> emps) {
    for (EmpDto emp : emps) {
      empMapper.insertEmp(emp);
    }

  }

}
