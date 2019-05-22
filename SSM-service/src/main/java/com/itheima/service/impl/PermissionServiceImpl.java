package com.itheima.service.impl;

import com.itheima.dao.IPermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        permissionDao.savePermission(permission);
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String id) {
        permissionDao.deleteById(id);
    }
}
