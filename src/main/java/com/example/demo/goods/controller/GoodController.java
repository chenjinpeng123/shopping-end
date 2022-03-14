package com.example.demo.goods.controller;

import com.example.demo.goods.entity.vo.UploadGoodsVO;
import com.example.demo.goods.service.GoodService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("good")
@AllArgsConstructor
@CrossOrigin
public class GoodController {

    private final GoodService goodService;

    @PostMapping("/upload")
    @ResponseBody
    public R upload(HttpSession session, UploadGoodsVO uploadGoodsVO){
        return goodService.uploadGoods(session, uploadGoodsVO);
    }
}
