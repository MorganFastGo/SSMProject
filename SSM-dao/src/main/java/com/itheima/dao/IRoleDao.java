package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userInfoId})")
    @Results(id ="roleMapper" ,value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "roleName",property ="roleName" ),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.IPermissionDao.findPermissionByRoleId",fetchType = FetchType.LAZY))
    })
    List<Role> findRoleByUserId(String userInfoId) throws Exception;

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);

    @Select("select * from role where id=#{id}")
    @ResultMap(value = "roleMapper")
    Role findById(String id);

    @Delete("delete from role where id=#{id}")
    void deleteRole(String id) throws Exception;

    @Select("select * from role where id in (select roleId from role_permission where permissionId=#{permissionId})")
    List<Role> findRoleByPermissionId(String permissionId);

    @Select("select * from permission where id not in (select permissionID from role_permission where roleId =#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    @Insert("insert into role_permission (roleId,permissionId)values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
