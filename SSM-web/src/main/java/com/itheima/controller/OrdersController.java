package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageInterceptor;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService orderService;
@RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "page",required = true,defaultValue = "1") int page, @RequestParam(value = "size",required = true,defaultValue = "4") int size){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = orderService.findAll(page,size);
    PageInfo pageInfo = new PageInfo(ordersList);
    mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id",required = true) String orderId){
    Orders order= orderService.findById(orderId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;
    }
}
