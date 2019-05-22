package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IOrderDao;
import com.itheima.domain.Orders;
import com.itheima.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrdersServiceImpl implements IOrdersService {
   @Autowired
    private IOrderDao orderDao;
    @Override
    public List<Orders> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String orderId) {
        return orderDao.findById(orderId);
    }
}
