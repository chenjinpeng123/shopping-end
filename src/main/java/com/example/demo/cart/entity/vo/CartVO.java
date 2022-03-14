package com.example.demo.cart.entity.vo;

import lombok.Data;

@Data
public class CartVO {
    private Long goodId;
    private String name;
    private Double price;
    private Integer num;
    private String picture;
    private Long userId;
}
