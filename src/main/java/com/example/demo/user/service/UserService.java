package com.example.demo.user.service;

import com.example.demo.user.entity.User;
import com.example.demo.user.entity.vo.UpdatePasswordVO;
import com.example.demo.util.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService  {

//    检查用户名和密码是否正确
    R inspect(User user);

//    增加用户
    Integer addUser(User user);

//    上传用户图片
    R uploadPicture(Long userId, MultipartFile file);

    R updatePassword(UpdatePasswordVO updatePasswordVO);
}
