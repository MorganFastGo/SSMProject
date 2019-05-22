package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList=roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveRole(Role role) {
        roleService.saveRole(role);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Role role=roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(String id) throws Exception {
        roleService.deleteRole(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam("id")String roleId) {
        Role role = roleService.findById(roleId);
        List<Permission> permissionList=roleService.findOtherPermissions(roleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam("roleId")String roleId,@RequestParam("ids") String[]permissionIds) {
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }
}
