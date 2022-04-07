package com.example.demo.shopping.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderSearchTimeDTO {
    private List<Object> orderNum;
    private List<Object> dropShip;
    private List<Object> pushDelivery;
    private List<Object> money;
    private List<String> dates;
}
