package com.example.demo.shopping.controller;
import com.example.demo.shopping.entity.User;
import com.example.demo.shopping.entity.vo.UpdatePasswordVO;
import com.example.demo.shopping.service.UserService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    @ResponseBody
    public R list(){
        return userService.list();
    }

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

    @PostMapping("/delete")
    @ResponseBody
    public R delete(Long id){
        return userService.delete(id);
    }

}
