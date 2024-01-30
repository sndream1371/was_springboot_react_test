package com.wayside.was.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.wayside.was.model.EmpDto;

import java.util.List;

@Mapper
public interface EmpMapper {
  // 모든 직원 정보를 조회합니다.
  // @Select("SELECT id,name,position,salary,hire_date FROM emp")
  List<EmpDto> findAll();

  // ID를 기반으로 특정 직원 정보를 조회합니다.
  // @Select("SELECT id,name,position,salary,hire_date FROM emp WHERE id = #{id}")
  EmpDto findById(Long id);

  EmpDto findByParamId(@Param("id") Long id);

  EmpDto findByPostId(Long id);

  void insertEmp(EmpDto emp);
}
