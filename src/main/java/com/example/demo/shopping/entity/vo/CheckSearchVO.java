package com.example.demo.shopping.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class CheckSearchVO {
    private Integer page;
    private Long merId;
    private Long id;
    private List<Long> groupId;
    private String state;
    private String name;
}
