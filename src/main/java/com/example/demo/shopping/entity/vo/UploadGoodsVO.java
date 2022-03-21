package com.example.demo.shopping.entity.vo;

import lombok.Data;


@Data
public class UploadGoodsVO {
    private String name;
    private Double price;
    private Long groupId;
    private String introduction;
    private Double balance;
    private String address;
    private Long merId;
    private String picture;
    private String color;
}
