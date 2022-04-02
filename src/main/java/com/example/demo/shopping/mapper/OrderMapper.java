package com.example.demo.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.shopping.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    Long getUserId(int id1);
}
