package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    private IUserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList", userList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) {
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {

        ModelAndView mv = new ModelAndView();
        UserInfo userInfo=userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }



    @RequestMapping("/findUserByIdAndAllRole.do")
    @PreAuthorize("hasRole('aDD')")
    public ModelAndView findUserByIdAndAllRol(@RequestParam("id") String userId) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
       List<Role> roleList=userService.findOtherRole(userId);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
       return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam("userId")String userId,@RequestParam("ids") String[]roleIds) {
            userService.addRoleToUser(userId,roleIds);
            return "redirect:findAll.do";

    }
}
