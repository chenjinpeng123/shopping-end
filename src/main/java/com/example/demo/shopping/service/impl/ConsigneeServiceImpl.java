package com.example.demo.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.shopping.entity.Consignee;
import com.example.demo.shopping.mapper.ConsigneeMapper;
import com.example.demo.shopping.service.ConsigneeService;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsigneeServiceImpl implements ConsigneeService {
    private final ConsigneeMapper consigneeMapper;

    @Override
    public R list(Long userId) {
        QueryWrapper<Consignee> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return R.ok(consigneeMapper.selectList(wrapper));
    }

    @Override
    public R add(Consignee consignee) {
        return R.ok(consigneeMapper.insert(consignee));
    }

    @Override
    public R delete(Long id) {
        return R.ok(consigneeMapper.deleteById(id));
    }
}
