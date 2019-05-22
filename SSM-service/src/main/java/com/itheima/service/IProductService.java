package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);
}
