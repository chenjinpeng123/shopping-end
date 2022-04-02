package com.example.demo.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Consignee {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private String address;
    private String phone;
}
