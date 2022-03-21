package com.example.demo.shopping.service.impl;

import com.example.demo.shopping.entity.vo.GoodsGroupVO;
import com.example.demo.shopping.mapper.GoodGroupMapper;
import com.example.demo.shopping.service.GoodGroupService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodGroupServiceImpl implements GoodGroupService {
    private final GoodGroupMapper goodGroupMapper;

    @Override
    public R list() {
        List<GoodsGroupVO> goodsGroupVOS = goodGroupMapper.goodsGroupVOList();
        return R.ok(goodsGroupVOS);
    }
}
