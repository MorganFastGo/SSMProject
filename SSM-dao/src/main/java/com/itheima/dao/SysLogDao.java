package com.itheima.dao;

import com.itheima.domain.sysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(sysLog sysLog);

    @Select("select * from syslog")
    List<sysLog> findAll();
}
