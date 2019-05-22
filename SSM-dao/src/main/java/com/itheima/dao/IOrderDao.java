package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IOrderDao {
    @Select("select * from orders")
 /*   @Results(id = "resultMap",value = {
            @Result(id = true,column ="id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property ="orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.dao.IProductDao.findById")),
    })*/
    @Results(id = "resultMap",value = {
            @Result(id = true,column ="id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property ="orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.dao.IProductDao.findById",fetchType = FetchType.LAZY)),
            @Result(column = "memberId",property = "member",javaType = Member.class,one=@One(select = "com.itheima.dao.IMemberDao.findById",fetchType = FetchType.LAZY)),
            @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.ITravellerDao.findTravellerByOrderId",fetchType = FetchType.LAZY))
    })
    List<Orders> findAll();

@Select("select * from orders where id = #{orderId}")
/*@Results(value = {
        @Result(id = true,column ="id",property = "id"),
        @Result(column = "orderNum",property = "orderNum"),
        @Result(column = "orderTime",property = "orderTime"),
        @Result(column = "orderStatus",property ="orderStatus"),
        @Result(column = "peopleCount",property = "peopleCount"),
        @Result(column = "orderDesc",property = "orderDesc"),
        @Result(column = "payType",property = "payType"),
        @Result(column = "productId",property = "product",javaType = Product.class,one = @One(select = "com.itheima.dao.IProductDao.findById",fetchType = FetchType.LAZY)),
        @Result(column = "memberId",property = "member",javaType = Member.class,one=@One(select = "com.itheima.dao.IMemberDao.findById",fetchType = FetchType.LAZY)),
        @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.ITravellerDao.findById",fetchType = FetchType.LAZY))
})*/
@ResultMap(value = "resultMap")
    Orders findById(String orderId);
}
