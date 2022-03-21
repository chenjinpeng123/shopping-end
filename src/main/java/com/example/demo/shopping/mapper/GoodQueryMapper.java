package com.example.demo.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.shopping.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodQueryMapper extends BaseMapper<Goods> {

}
