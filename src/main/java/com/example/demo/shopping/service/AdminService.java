package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.Admin;
import com.example.demo.util.R;

public interface AdminService {
    R login(Admin admin);

    R add(Admin admin);
}
