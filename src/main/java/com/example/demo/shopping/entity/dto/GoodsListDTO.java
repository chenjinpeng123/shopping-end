package com.example.demo.shopping.entity.dto;

import lombok.Data;

// ๅๅๅ่กจ
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
    private Boolean state;
}
