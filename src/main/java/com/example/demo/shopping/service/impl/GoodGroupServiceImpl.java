package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.shopping.entity.GoodsGroup;
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

    @Override
    public R allList() {
        return R.ok(goodGroupMapper.selectList(new QueryWrapper<GoodsGroup>()));
    }

    @Override
    public R add(GoodsGroup goodsGroup) {
        UpdateWrapper<GoodsGroup> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",goodsGroup.getId());
        if (goodGroupMapper.update(goodsGroup,wrapper) == 0) {
            return R.ok(goodGroupMapper.insert(goodsGroup));
        } else {
            return R.ok(1);
        }
    }

    @Override
    public R delete(Long id) {
        return R.ok(goodGroupMapper.deleteById(id));
    }

}
