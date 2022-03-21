package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.vo.CartVO;
import com.example.demo.util.R;


public interface CartService {
    R add(CartVO cartVO);

    R queryList(Long userId);

    R delete(Long id);
}
