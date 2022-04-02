package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.shopping.entity.Goods;
import com.example.demo.shopping.entity.Order;
import com.example.demo.shopping.entity.dto.OrderAddDTO;
import com.example.demo.shopping.entity.vo.OrderAddVO;
import com.example.demo.shopping.entity.vo.OrderVO;
import com.example.demo.shopping.mapper.GoodMapper;
import com.example.demo.shopping.mapper.OrderMapper;
import com.example.demo.shopping.service.OrderService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    @Resource
    private final OrderMapper orderMapper;
    private final GoodMapper goodMapper;

    @Override
    public R add(OrderVO orderVO) {
        List<OrderAddVO> orderAddVOSList = orderVO.getOrderAddVOS();
        Order order = new Order();
        for (OrderAddVO orderAddVO:orderAddVOSList) {
            Long goodId = orderAddVO.getGoodId();

            //减少对应商品数量
            Goods good = goodMapper.selectById(goodId);
            double balance = good.getBalance() - orderAddVO.getNum();
            if (balance <= 0) {
                try {
                    throw new RuntimeException(good.getName()+"数量不够");
                }catch (Exception e) {
                    return R.failed("对不起,"+good.getName()+"数量只剩下"+ Double.valueOf(String.valueOf(good.getBalance())).intValue() +"台,请减少数量后重新下单");
                }
            }
            good.setBalance(balance);
            goodMapper.update(good, new UpdateWrapper<Goods>().eq("id",goodId));

            OrderAddDTO orderAddDTO = goodMapper.getOrderAddDTO(goodId);
            order.setConsigneeAddress(orderVO.getOrderAddressVO().getAddress());
            order.setConsigneeName(orderVO.getOrderAddressVO().getName());
            order.setConsigneePhone(orderVO.getOrderAddressVO().getPhone());
            order.setCreateDate(getStringDate());
            order.setDealPrice(orderAddVO.getDealPrice());
            order.setGoodsId(orderAddVO.getGoodId());
            order.setGroupId(orderAddDTO.getGroupId());
            order.setMerId(orderAddDTO.getMerId());
            order.setName(orderAddVO.getName());
            order.setNum(orderAddVO.getNum());
            order.setPicture(orderAddVO.getPicture());
            order.setUnitPrice(orderAddVO.getUnitPrice());
            order.setUserId(orderAddVO.getUserId());
            order.setState("待发货");
            order.setDeleteState(0);
            orderMapper.insert(order);
        }
        return R.ok("下单成功");
    }

    @Override
    public R userList(Long userId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return R.ok(orderMapper.selectList(wrapper));
    }

    @Override
    public R updateState(String state,Long id) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(new Order(state),wrapper));
    }

    @Override
    public R delete(Long id) {
        return R.ok(orderMapper.deleteById(id));
    }

    @Override
    public R addEvaluate(Long id, String evaluate, Integer score) {
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        Order order = new Order();
        order.setEvaluate(evaluate);
        order.setScore(score);
        order.setState("已评价");
        return R.ok(orderMapper.update(order,wrapper));
    }

    @Override
    public R userDelete(Long id) {
        Order order = orderMapper.selectById(id);
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        if (order.getDeleteState() == 2) {
            order.setDeleteState(3);
        } else {
            order.setDeleteState(1);
        }
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(order,wrapper));
    }

    @Override
    public R changeNum(List<OrderAddVO> orderAddVOS) {
        for (OrderAddVO orderAddVO:orderAddVOS) {
            Long goodId = orderAddVO.getGoodId();
            //减少对应商品数量
            Goods good = goodMapper.selectById(goodId);
            double balance = good.getBalance() - orderAddVO.getNum();
            if (balance <= 0) {
                return R.failed("对不起," + good.getName() + "数量只剩下" + Double.valueOf(String.valueOf(good.getBalance())).intValue() + "台,请减少数量后重新下单");
            }
        }
        return R.ok("正常");
    }

    @Override
    public R meiList(Long merId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("mer_id",merId);
        return R.ok(orderMapper.selectList(wrapper));
    }

    @Override
    public R merDelete(Long id) {
        Order order = orderMapper.selectById(id);
        UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
        if (order.getDeleteState() == 1) {
            order.setDeleteState(3);
        } else {
            order.setDeleteState(2);
        }
        wrapper.eq("id",id);
        return R.ok(orderMapper.update(order,wrapper));
    }


    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }
}
