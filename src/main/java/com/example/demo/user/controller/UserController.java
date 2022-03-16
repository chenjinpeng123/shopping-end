package com.example.demo.user.controller;
import com.example.demo.user.entity.User;
import com.example.demo.user.entity.vo.UpdatePasswordVO;
import com.example.demo.user.entity.vo.UploadPicture;
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
@CrossOrigin
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public R examine(@RequestBody User user){
        return userService.inspect(user);
    }

    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody User user){
        return R.ok(userService.addUser(user));
    }



    @PostMapping("/uploadPicture")
    @ResponseBody
    public R uploadPicture(@RequestParam MultipartFile file, @RequestParam Long userId) {
        return userService.uploadPicture(userId,file);
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public R updatePassword(@RequestBody UpdatePasswordVO updatePasswordVO) {
        return userService.updatePassword(updatePasswordVO);
    }

}
