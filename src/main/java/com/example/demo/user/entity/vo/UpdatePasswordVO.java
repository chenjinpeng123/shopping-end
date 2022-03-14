package com.example.demo.user.entity.vo;

import lombok.Data;

/**
 * 修改密码
 */
@Data
public class UpdatePasswordVO {
    // 原始密码
    private String oldPassword;
    // 新密码
    private String newPassword;
}
