package com.example.demo.shopping.controller;

import com.example.demo.shopping.service.GoodGroupService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("group")
@AllArgsConstructor
@CrossOrigin
public class GoodGroupController {
    private GoodGroupService goodGroupService;

    @GetMapping("/list")
    @ResponseBody
    public R list(){
        return goodGroupService.list();
    }
}
