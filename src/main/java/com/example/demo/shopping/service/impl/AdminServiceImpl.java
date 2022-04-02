package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.shopping.entity.Admin;
import com.example.demo.shopping.mapper.AdminMapper;
import com.example.demo.shopping.service.AdminService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminMapper adminMapper;

    @Override
    public R login(Admin admin) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",admin.getUserName())
                .eq("password",admin.getPassword());
        Admin adminOne = adminMapper.selectOne(wrapper);
        return R.ok(Objects.requireNonNullElse(adminOne, "用户名或密码错误"));
    }

    @Override
    public R add(Admin admin) {
        return R.ok(adminMapper.insert(admin));
    }

}
