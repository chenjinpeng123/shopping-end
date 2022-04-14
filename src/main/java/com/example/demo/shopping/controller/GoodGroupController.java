package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.GoodsGroup;
import com.example.demo.shopping.entity.vo.GoodsGroupVO;
import com.example.demo.shopping.service.GoodGroupService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("group")
@AllArgsConstructor
@CrossOrigin
public class GoodGroupController {
    private final GoodGroupService goodGroupService;

    @GetMapping("/list")
    @ResponseBody
    public R list(){
        return goodGroupService.list();
    }

    @GetMapping("/allList")
    @ResponseBody
    public R allList(){
        return goodGroupService.allList();
    }

    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody GoodsGroup goodsGroup){
        return goodGroupService.add(goodsGroup);
    }

    @PostMapping("/delete")
    @ResponseBody
    public R delete(Long id){
        return goodGroupService.delete(id);
    }

}
