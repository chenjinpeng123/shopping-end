package com.example.demo.shopping.entity.vo;

import lombok.Data;

@Data
public class UpdateGoodVO {
    private Long id;
    private String name;
    private String address;
    private Double price;
    private Double balance;
}
