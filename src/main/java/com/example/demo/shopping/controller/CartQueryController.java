package com.example.demo.shopping.controller;

import com.example.demo.shopping.service.CartService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("queryCart")
@AllArgsConstructor
@CrossOrigin
public class CartQueryController {

    private final CartService cartService;

    @GetMapping("/list")
    @ResponseBody
    public R list(Long userId) {
        return cartService.queryList(userId);
    }

}
