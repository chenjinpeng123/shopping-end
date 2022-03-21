package com.example.demo.shopping.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.shopping.entity.User;
import com.example.demo.shopping.entity.vo.UpdatePasswordVO;
import com.example.demo.shopping.mapper.UserMapper;
import com.example.demo.shopping.service.UserService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    // 设置文件的最大值
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;
    // 限制文件上传的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/jpg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @Override
    public R inspect(User user) {
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUserName,user.getUserName())
                .eq(User::getPassword,user.getPassword());
        User userOne = userMapper.selectOne(userWrapper);
        if (userOne != null){
            return R.ok(userOne);
        } else{
            return R.ok("用户名或密码错误");
        }
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }


    @Override
    public R uploadPicture(Long userId, MultipartFile file) {
        // 判断文件是否为空
        if (file.isEmpty()) {
            return R.failed("文件为空");
        }
        if (file.getSize() > AVATAR_MAX_SIZE) {
            return R.failed("文件超出限制");
        }
        // 判断文件的类型是否是我们规定的和后缀类型
        String contentType = file.getContentType();
        // 如果集合包含某个元素则返回值true
        if (!AVATAR_TYPE.contains(contentType)){
            return R.failed("文件类型不支持");
        }

        // 上传的文件.../upload/文件.png
        String parent = "E:\\vue3.6.0实战\\shopping3\\public\\img\\head";
        // File对象指向这个路径，判断File是否存在
        File dir = new File(parent);
        if (!dir.exists()) { // 检测目录是否存在
            dir.mkdir(); //创建当前的目录
        }

        // 获取到这个文件名称,UUID工具将生成一个新的字符串作为文件名
        // 例如：avatar01.png
        String originalFilename = file.getOriginalFilename();
        // 获取.后面的数据(例.png)
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        // 避免名字重复,用UUID重新生成名字,拼接后缀
        String filename = UUID.randomUUID().toString().toUpperCase() + suffix;

        File dest = new File(dir, filename); //是一个空文件,文件名是filename
        // 参数file中数据写入到这个(dest)空文件中,后缀要一致
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            return R.failed("文件读写异常");
        }

        // 修改数据库中对应文件的路径
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        User user = new User();
        user.setAvatar("img/head/" + filename);
        wrapper.eq("id",userId);
        userMapper.update(user,wrapper);
        return R.ok(user.getAvatar());
    }

    @Override
    public R updatePassword(UpdatePasswordVO updatePasswordVO) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",updatePasswordVO.getUserId());
        User user = new User();
        user.setPassword(updatePasswordVO.getNewPassword());
        return R.ok(userMapper.update(user, wrapper));
    }
}
