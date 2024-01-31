package com.wayside.was.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wayside.was.mapper.RequestResponseLogMapper;
import com.wayside.was.model.log.RequestResponseLog;

@Service
public class LogService {
  private final RequestResponseLogMapper logMapper;

  @Autowired
  public LogService(RequestResponseLogMapper logMapper) {
    this.logMapper = logMapper;
  }

  @Transactional
  public void logRequestResponse(RequestResponseLog log) {
    logMapper.insertLog(log);
  }
}
