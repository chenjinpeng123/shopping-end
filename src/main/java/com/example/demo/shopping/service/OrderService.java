package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.vo.CheckSearchVO;
import com.example.demo.shopping.entity.vo.OrderAddVO;
import com.example.demo.shopping.entity.vo.OrderVO;
import com.example.demo.util.R;

import java.util.List;

public interface OrderService{
    R add(OrderVO orderVO);

    R userList(Long userId);

    R updateState(String state,Long id);

    R delete(Long id);

    R addEvaluate(Long id, String evaluate, Integer score);

    R userDelete(Long id);

    R changeNum(List<OrderAddVO> orderAddVOS);

    R meiList(Long merId);

    R merDelete(Long id);

    R deliver(Long id);

    R checkSearch(CheckSearchVO checkSearchVO);

    R searchTime(String name);
}
