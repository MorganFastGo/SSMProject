package com.itheima.dao;

import com.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

public interface IMemberDao {
    @Select("select * from member where id= #{memberId}")
    Member findById(String memberId) ;
}
