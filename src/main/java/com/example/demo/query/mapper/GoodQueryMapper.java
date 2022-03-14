package com.example.demo.query.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.goods.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodQueryMapper extends BaseMapper<Goods> {

}
