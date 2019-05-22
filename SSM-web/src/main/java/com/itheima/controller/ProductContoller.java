package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductContoller {
    @Autowired
    private IProductService service;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> all = service.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
        mv.addObject("productList",all);
        mv.setViewName("product-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String saveOne(Product product) {
        service.save(product);
        return "redirect:findAll.do";
    }

}
