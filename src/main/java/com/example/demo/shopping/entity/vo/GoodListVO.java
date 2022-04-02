package com.example.demo.shopping.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class GoodListVO {
    private Integer page;
    private Integer num;
    private List<Long> groupId;
    private String name;
    private Long roleId;
    private Long userId;
}
