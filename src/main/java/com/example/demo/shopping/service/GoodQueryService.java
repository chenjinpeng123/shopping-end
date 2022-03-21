package com.example.demo.shopping.service;

import com.example.demo.util.R;

public interface GoodQueryService {
    R list(Integer page, Integer num, Long groupId, String name);

    R detail(Long id);

    R find(String goodName);
}
