package com.example.demo.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Admin {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long roleId;
    private String userName;
    private String password;
    private String phone;

    public Admin() {
        this.roleId = 3L;
    }
}
