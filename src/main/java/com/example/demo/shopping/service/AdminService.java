package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.Admin;
import com.example.demo.shopping.entity.vo.UpdatePasswordVO;
import com.example.demo.util.R;

public interface AdminService {
    R login(Admin admin);

    R add(Admin admin);

    R update(UpdatePasswordVO updatePasswordVO);
}
