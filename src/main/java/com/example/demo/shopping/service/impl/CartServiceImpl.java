package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.shopping.entity.Cart;
import com.example.demo.shopping.entity.vo.CartVO;
import com.example.demo.shopping.mapper.CartMapper;
import com.example.demo.shopping.service.CartService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

  //  private final HttpServletRequest request;
    private final CartMapper cartMapper;

    @Override
    public R add(CartVO cartVO) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",cartVO.getUserId())
                .eq("good_id",cartVO.getGoodId());
        Cart cartOne = cartMapper.selectOne(wrapper);
        if (cartOne != null) {
            return R.failed("不能重复添加");
        } else {
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartVO,cart);
            cartMapper.insert(cart);
            return R.ok("添加成功");
        }
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
