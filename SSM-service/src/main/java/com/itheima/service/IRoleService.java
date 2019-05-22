package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface IRoleService {

    List<Role> findAll();

    void saveRole(Role role);

    Role findById(String id);

    void deleteRole(String id) throws Exception;

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
