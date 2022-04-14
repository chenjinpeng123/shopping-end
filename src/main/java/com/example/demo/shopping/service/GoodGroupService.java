package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.GoodsGroup;
import com.example.demo.util.R;

public interface GoodGroupService {
    R list();

    R allList();

    R add(GoodsGroup goodsGroup);

    R delete(Long id);

}
