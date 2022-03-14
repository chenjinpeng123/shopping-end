package com.example.demo.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long merId;
    private Long groupId;
    private String name;
    private Double price;
    private String introduction;
    private String picture;
    private String address;
    private Double balance;
    private String color;
}
