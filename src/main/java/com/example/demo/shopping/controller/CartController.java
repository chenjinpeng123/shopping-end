package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.vo.CartVO;
import com.example.demo.shopping.service.CartService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody CartVO cartVO){
        return cartService.add(cartVO);
    }

    @PostMapping("/delete")
    @ResponseBody
    public R delete(Long id){
        return cartService.delete(id);
    }
}
