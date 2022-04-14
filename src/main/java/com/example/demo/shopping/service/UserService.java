package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.User;
import com.example.demo.shopping.entity.vo.UpdatePasswordVO;
import com.example.demo.util.R;
import org.springframework.web.multipart.MultipartFile;

public interface UserService  {

//    检查用户名和密码是否正确
    R inspect(User user);

//    增加用户
    Integer addUser(User user);

//    上传用户图片
    R uploadPicture(Long userId, MultipartFile file);

    R updatePassword(UpdatePasswordVO updatePasswordVO);

    R list();

    R delete(Long id);
}
