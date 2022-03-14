package com.example.demo.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodMapper extends BaseMapper<Goods> {

}
