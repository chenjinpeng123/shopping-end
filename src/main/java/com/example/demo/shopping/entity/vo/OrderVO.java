package com.example.demo.shopping.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class OrderVO {
    private OrderAddressVO orderAddressVO;
    private List<OrderAddVO> orderAddVOS;
}
