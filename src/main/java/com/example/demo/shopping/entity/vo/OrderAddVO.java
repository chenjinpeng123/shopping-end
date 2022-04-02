package com.example.demo.shopping.entity.vo;

import lombok.Data;

@Data
public class OrderAddVO {
    private String name;
    private Double unitPrice;
    private Integer num;
    private String picture;
    private Long userId;
    private Double dealPrice;
    private Long goodId;
}
