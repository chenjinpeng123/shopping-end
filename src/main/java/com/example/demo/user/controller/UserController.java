package com.example.demo.user.controller;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.vo.UpdatePasswordVO;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.service.UserService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @PostMapping("/login")
    @ResponseBody
    public R examine(@RequestBody User user){
        return userService.inspect(user);
    }

    @CrossOrigin
    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody User user){
        return R.ok(userService.addUser(user));
    }



    @PostMapping("/uploadPicture")
    @ResponseBody
    @CrossOrigin
    public R uploadPicture(Long userId, MultipartFile file) {
      return userService.uploadPicture(userId,file);
    }

}
