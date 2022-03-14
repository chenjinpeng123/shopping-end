package com.example.demo.cart.controller;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.vo.CartVO;
import com.example.demo.cart.service.CartService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
