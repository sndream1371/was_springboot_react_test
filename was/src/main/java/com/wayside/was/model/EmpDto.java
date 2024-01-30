package com.wayside.was.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmpDto {

  private Long id;
  private String name;
  private String position;
  private BigDecimal salary;

  // 요청메세지에서 JSON에서 날짜 형식("hire_date" : "20240130")을 받아들일 수 있도록
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
  private Date hireDate;

  // 기본 생성자
  public EmpDto() {
  }

  // 모든 필드를 포함한 생성자
  public EmpDto(Long id, String name, String position, BigDecimal salary, Date hireDate) {
    this.id = id;
    this.name = name;
    this.position = position;
    this.salary = salary;
    this.hireDate = hireDate;
  }

  // Getter와 Setter 메서드들
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  // toString 메서드 (옵션)
  @Override
  public String toString() {
    return "Emp{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", position='" + position + '\'' +
        ", salary=" + salary +
        ", hireDate=" + hireDate +
        '}';
  }
}
