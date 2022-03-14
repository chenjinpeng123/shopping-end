package com.example.demo.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.entity.vo.CartVO;
import com.example.demo.cart.mapper.CartMapper;
import com.example.demo.cart.service.CartService;
import com.example.demo.user.service.UserService;
import com.example.demo.user.service.impl.UserServiceImpl;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

  //  private final HttpServletRequest request;
    private final CartMapper cartMapper;

    @Override
    public R add(CartVO cartVO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartVO, cart);
        System.out.println(cart);
        cartMapper.insert(cart);
        return R.ok("增加成功");
    }

    @Override
    public R queryList(Long userId) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return R.ok(cartMapper.selectList(wrapper));
    }

    @Override
    public R delete(Long id) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return R.ok(cartMapper.delete(wrapper));
    }
}
