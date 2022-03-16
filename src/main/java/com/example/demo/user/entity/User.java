package com.example.demo.user.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User  {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String avatar;
    private Integer age;
}
