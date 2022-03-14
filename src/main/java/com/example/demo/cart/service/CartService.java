package com.example.demo.cart.service;

import com.example.demo.cart.entity.vo.CartVO;
import com.example.demo.util.R;


public interface CartService {
    R add(CartVO cartVO);

    R queryList(Long userId);

    R delete(Long id);
}
