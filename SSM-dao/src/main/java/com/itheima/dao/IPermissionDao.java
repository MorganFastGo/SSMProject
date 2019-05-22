package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    @Results(id ="permissionMapper" ,value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IRoleDao.findRoleByPermissionId",fetchType = FetchType.LAZY))
    })
    List<Permission> findPermissionByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);

    @Select("select * from permission where id=#{id}")
    @ResultMap(value = "permissionMapper")
    Permission findById(String id);

    @Select("delete from permission where id=#{id}")
    void deleteById(String id);
}
