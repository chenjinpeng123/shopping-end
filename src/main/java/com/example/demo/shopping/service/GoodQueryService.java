package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.vo.GoodListVO;
import com.example.demo.util.R;

import java.util.List;

public interface GoodQueryService {
    R list(Integer page, Integer num, List<Long> groupId, String name, Long roleId, Long userId);

    R detail(Long id);

    R find(String goodName);

    R backList(GoodListVO goodListVO);
}
