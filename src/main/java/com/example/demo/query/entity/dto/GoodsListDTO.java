package com.example.demo.query.entity.dto;

import lombok.Data;

import java.util.List;

// 商品列表
@Data
public class GoodsListDTO {
    private Long id;
    private Long merId;
    private Long groupId;
    private String name;
    private Double price;
    private String introduction;
    private String[] pictures;
    private String address;
    private Double balance;
    private String[] colors;
}
