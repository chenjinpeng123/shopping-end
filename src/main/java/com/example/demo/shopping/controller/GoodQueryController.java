package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.vo.GoodDetailVO;
import com.example.demo.shopping.entity.vo.GoodListVO;
import com.example.demo.shopping.service.GoodQueryService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<Long> groupId = goodListVO.getGroupId();
        String name = goodListVO.getName();
        return goodQueryService.list(page, num, groupId, name, goodListVO.getRoleId(), goodListVO.getUserId());
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
