package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.Consignee;
import com.example.demo.util.R;

public interface ConsigneeService {
    R list(Long userId);

    R add(Consignee consignee);

    R delete(Long id);
}
