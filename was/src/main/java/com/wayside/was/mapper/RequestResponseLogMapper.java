package com.wayside.was.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.wayside.was.model.log.RequestResponseLog;

@Mapper
public interface RequestResponseLogMapper {
  void insertLog(RequestResponseLog log);
}
