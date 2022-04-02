package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.vo.UpdateGoodVO;
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

    /**
     * 上传商品
     * */
    @PostMapping("/upload")
    @ResponseBody
    public R upload(@RequestBody UploadGoodsVO uploadGoodsVO){
        return goodService.uploadGoods(uploadGoodsVO);
    }

    /**
     * 上传图片
     * */
    @PostMapping("/uploadPic")
    @ResponseBody
    public R uploadPic(@RequestParam MultipartFile file, @RequestParam Long groupId){
        return goodService.uploadPic(file,groupId);
    }

    /**
     * 改变商品的状态
     * */
    @PostMapping("/changeState")
    @ResponseBody
    public R changeState(Long id){
        return goodService.changeState(id);
    }

    /**
     * 删除商品
     */
    @PostMapping("/delete")
    @ResponseBody
    public R delete(Long id) {
        return goodService.delete(id);
    }

    /**
     * 修改商品
     */
    @PostMapping("/update")
    @ResponseBody
    public R update(@RequestBody UpdateGoodVO updateGoodVO) {
        return goodService.update(updateGoodVO);
    }
}
