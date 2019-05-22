package com.itheima.service.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=null;
        try {
            UserInfo  userInfo = userDao.findByName(username);
            if (userInfo != null) {
                /*user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthorities(userInfo.getRoles()));*/
                user= new User(userInfo.getUsername(),userInfo.getPassword()
                        ,userInfo.getStatus()==0?false:true,true,
                        true,true,getAuthorities(userInfo.getRoles()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public ArrayList<SimpleGrantedAuthority> getAuthorities(List<Role> roles){
        ArrayList<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.saveUser(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRole(String userId) {
        return userDao.findOtherRole(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
