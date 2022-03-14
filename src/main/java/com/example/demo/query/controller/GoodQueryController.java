package com.example.demo.query.controller;

import com.example.demo.goods.entity.vo.UploadGoodsVO;
import com.example.demo.goods.service.GoodService;
import com.example.demo.query.entity.vo.GoodDetailVO;
import com.example.demo.query.entity.vo.GoodListVO;
import com.example.demo.query.service.GoodQueryService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("queryGood")
@AllArgsConstructor
@CrossOrigin
public class GoodQueryController {
    private final GoodQueryService goodQueryService;

    @GetMapping("/list")
    @ResponseBody
    public R list(GoodListVO goodListVO){
        int page = goodListVO.getPage();
        int num = goodListVO.getNum();
        Long groupId = goodListVO.getGroupId();
        String name = goodListVO.getName();
        return goodQueryService.list(page, num, groupId, name);
    }

    @GetMapping("/detail")
    @ResponseBody
    public R detail(GoodDetailVO goodDetailVO){
        Long id = goodDetailVO.getId();
        return goodQueryService.detail(id);
    }

    @GetMapping("/find")
    @ResponseBody
    public R find(String goodName){
        return goodQueryService.find(goodName);
    }

}
