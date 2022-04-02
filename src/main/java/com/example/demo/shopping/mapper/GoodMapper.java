package com.example.demo.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.shopping.entity.Goods;
import com.example.demo.shopping.entity.dto.OrderAddDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GoodMapper extends BaseMapper<Goods> {

    OrderAddDTO getOrderAddDTO(@Param("id") Long goodId);
}
