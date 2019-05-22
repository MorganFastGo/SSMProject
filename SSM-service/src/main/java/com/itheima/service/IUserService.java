package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findOtherRole(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
