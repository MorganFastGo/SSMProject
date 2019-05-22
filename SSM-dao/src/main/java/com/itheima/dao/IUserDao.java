package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results(id = "mapper",value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column = "status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByUserId"))
    })
  UserInfo findByName(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(username,password,email,phoneNum,STATUS) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);

   @Select("select * from users where id = #{id}")
   @ResultMap(value = "mapper")
    UserInfo findById(String id);

   @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRole(String userId);

   @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
