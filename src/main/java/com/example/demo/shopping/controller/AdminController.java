package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.Admin;
import com.example.demo.shopping.service.AdminService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@AllArgsConstructor
@CrossOrigin
public class AdminController {
    private final AdminService adminService;

    @ResponseBody
    @PostMapping("/login")
    public R login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }

    @ResponseBody
    @PostMapping("/add")
    public R add(@RequestBody Admin admin) {
        return adminService.add(admin);
    }
}
