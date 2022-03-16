package com.example.demo.user.entity.vo;

import lombok.Data;

/**
 * 修改密码
 */
@Data
public class UpdatePasswordVO {
    // 当前用户id
    private Long userId;

    // 新密码
    private String newPassword;
}
