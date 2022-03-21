package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.vo.UploadGoodsVO;
import com.example.demo.shopping.service.GoodService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("good")
@AllArgsConstructor
@CrossOrigin
public class GoodController {

    private final GoodService goodService;

    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestBody UploadGoodsVO uploadGoodsVO){
        return goodService.uploadGoods(uploadGoodsVO);
    }

    @PostMapping("/uploadPic")
    @ResponseBody
    public R uploadPic(@RequestParam MultipartFile file, @RequestParam Long groupId){
        return goodService.uploadPic(file,groupId);
    }
}
