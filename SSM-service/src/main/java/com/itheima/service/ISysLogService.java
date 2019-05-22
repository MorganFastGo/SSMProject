package com.itheima.service;

import com.itheima.domain.sysLog;

import java.util.List;

public interface ISysLogService {

    void save(sysLog sysLog);

    List<sysLog> findAll();
}
