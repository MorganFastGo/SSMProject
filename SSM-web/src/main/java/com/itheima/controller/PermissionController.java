package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList=permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String savePermission(Permission permission) {
        permissionService.savePermission(permission);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        Permission permission=permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/deleteById")
    public String deleteById(String id) {
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }
}
