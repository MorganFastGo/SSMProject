package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface IOrdersService {

    List<Orders> findAll(int page, int size);

    Orders findById(String orderId);
}
