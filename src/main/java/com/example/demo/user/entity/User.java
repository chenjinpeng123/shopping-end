package com.example.demo.user.entity;


import lombok.Data;

@Data
public class User  {
    private Long id;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private String avatar;
}
