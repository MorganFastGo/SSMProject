package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{orderId})")
    List<Traveller> findTravellerByOrderId(String orderId);
}
