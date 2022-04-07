package com.example.demo.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.shopping.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    Long getUserId(int id1);

    Object getOrderByDay(@Param("i") int i, @Param("name") String name);

    Object getOrderByMonth(@Param("date") String date, @Param("name") String name);
}
