package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.sysLog;
import com.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogImpl implements ISysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(sysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<sysLog> findAll() {
        return sysLogDao.findAll();
    }
}
