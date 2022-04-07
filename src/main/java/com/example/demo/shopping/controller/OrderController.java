package com.example.demo.shopping.controller;

import com.example.demo.shopping.entity.dto.OrderAddDTO;
import com.example.demo.shopping.entity.vo.*;
import com.example.demo.shopping.service.OrderService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("order")
@AllArgsConstructor
@CrossOrigin
public class OrderController {
    private final OrderService orderService;

    /**
     * 生成订单
     */
    @PostMapping("/add")
    @ResponseBody
    public R add(@RequestBody OrderVO orderVO) {
        return orderService.add(orderVO);
    }

    /**
     * 生成订单前，检测商品数量是否足够
     */
    @PostMapping("/changeNum")
    @ResponseBody
    public R changeNum(@RequestBody OrderVO orderVO) {
        List<OrderAddVO> orderAddVOS = orderVO.getOrderAddVOS();
        return orderService.changeNum(orderAddVOS);
    }

    /**
     * 查询该用户的订单
     */
    @GetMapping("/userList")
    @ResponseBody
    public R userList(Long userId) {
        return orderService.userList(userId);
    }

    /**
     * 改变订单状态
     */
    @PostMapping("/updateState")
    @ResponseBody
    public R updateState(String state,Long id) {
        return orderService.updateState(state,id);
    }

    /**
     * 管理员删除订单
     */
    @PostMapping("/delete")
    @ResponseBody
    public R delete(Long id) {
        return orderService.delete(id);
    }

    /**
     * 用户对订单进行评价
     */
    @PostMapping("/addEvaluate")
    @ResponseBody
    public R addEvaluate(@RequestBody OrderEvaluateVO orderEvaluateVO) {
        Long id = orderEvaluateVO.getId();
        String evaluate = orderEvaluateVO.getEvaluate();
        Integer score = orderEvaluateVO.getScore();
        return orderService.addEvaluate(id, evaluate, score);
    }

    /**
     * 用户删除订单（在数据库并未真正的删除）
     */
    @PostMapping("/userDelete")
    @ResponseBody
    public R userDelete(Long id) {
        return orderService.userDelete(id);
    }

    /**
     * 查询商家的订单
     */
    @GetMapping("/merList")
    @ResponseBody
    public R merList(Long merId) {
        return orderService.meiList(merId);
    }

    /**
     * 商家删除订单
     */
    @PostMapping("/merDelete")
    @ResponseBody
    public R merDelete(Long id) {
        return orderService.merDelete(id);
    }

    /**
     * 管理员发货
     */
    @PostMapping("/deliver")
    @ResponseBody
    public R deliver(Long id) {
        return orderService.deliver(id);
    }

    /**
     * 订单查看时的搜索
     */
    @PostMapping("/checkSearch")
    @ResponseBody
    public R checkSearch(@RequestBody CheckSearchVO checkSearchVO) {
        return orderService.checkSearch(checkSearchVO);
    }

    /**
     * 查询订单的时间分布
     */
    @GetMapping("/searchTime")
    @ResponseBody
    public R orderSearchTime(String name) {
        return orderService.searchTime(name);
    }
}
