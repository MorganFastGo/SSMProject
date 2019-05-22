package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProductDao {
    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product(PRODUCTNUM,PRODUCTNAME,CITYNAME,DEPARTURETIME,PRODUCTPRICE,PRODUCTDESC,PRODUCTSTATUS)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")

    void save(Product product);


    @Select("select * from product where id=#{id}")
    Product findById(String id);
}
