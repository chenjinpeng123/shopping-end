package com.example.demo.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.shopping.entity.GoodsGroup;
import com.example.demo.shopping.entity.vo.GoodsGroupVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodGroupMapper extends BaseMapper<GoodsGroup> {
    public List<GoodsGroupVO> goodsGroupVOList();
}
