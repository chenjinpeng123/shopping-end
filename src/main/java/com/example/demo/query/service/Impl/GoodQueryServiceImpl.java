package com.example.demo.query.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.goods.entity.Goods;
import com.example.demo.query.entity.dto.GoodsListDTO;
import com.example.demo.query.mapper.GoodQueryMapper;
import com.example.demo.query.service.GoodQueryService;
import com.example.demo.util.BackPage;
import com.example.demo.util.R;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GoodQueryServiceImpl implements GoodQueryService {

    private final GoodQueryMapper goodQueryMapper;

    @Override
    public R list(Integer page, Integer num, Long groupId, String name) {
        BackPage<GoodsListDTO> postBackPage = new BackPage<>();
        // 设置条件构造器
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        if (groupId != null) {
            wrapper.eq("group_id",groupId);
        }
        if (name != null) {
            wrapper.like("name",name);
        }
        // 构造分页信息，其中的Page<>(page, num)的第一个参数是页数，而第二个参数是每页的记录数
        IPage<Goods> goodsIPage = new Page<>(page, num);
        // 在构造器的基础上分页
        goodsIPage = goodQueryMapper.selectPage(goodsIPage,wrapper);
        // 封装数据，其中getRecords()是获取记录数，getCurrent()获取当前页数，getPages()获取总页数，getTotal()获取记录总数，还要其他更多的方法，大家可以自行查看，在这里就不过多赘述了

        // 图片转换为数组
        List<GoodsListDTO> goodsListDTOS = new ArrayList<>();
        for (int i = 0; i < goodsIPage.getRecords().size(); i++) {
            GoodsListDTO goodsListDTO = new GoodsListDTO();
            BeanUtils.copyProperties(goodsIPage.getRecords().get(i), goodsListDTO);
            String pic = goodsIPage.getRecords().get(i).getPicture();
            String[] pics = pic.split(";");
            goodsListDTO.setPictures(pics);
            goodsListDTOS.add(goodsListDTO);
        }

        postBackPage.setContentList(goodsListDTOS);
        postBackPage.setCurrentPage(goodsIPage.getCurrent());
        postBackPage.setTotalPage(goodsIPage.getPages());
        postBackPage.setTotalNum(goodsIPage.getTotal());
        return R.ok(postBackPage);
    }

    @Override
    public R detail(Long id) {
        Goods goods = goodQueryMapper.selectById(id);
        GoodsListDTO goodsListDTO = new GoodsListDTO();
        if (goods != null) {
            BeanUtils.copyProperties(goods, goodsListDTO);
            if (goods.getPicture() != null){
                String pic = goods.getPicture();
                String[] pics = pic.split(";");
                goodsListDTO.setPictures(pics);
            }
            if (goods.getColor() != null) {
                String color = goods.getColor();
                String[] colors = color.split(";");
                goodsListDTO.setColors(colors);
            }
            return R.ok(goodsListDTO);
        }
        return R.failed("没有数据");
    }

    @Override
    public R find(String goodName) {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.like("name",goodName);
        List<Goods> goods = goodQueryMapper.selectList(wrapper);
        List<GoodsListDTO> goodsListDTOS = new ArrayList<>();
        for (Goods good : goods) {
            GoodsListDTO goodsListDTO = new GoodsListDTO();
            BeanUtils.copyProperties(good, goodsListDTO);
            String pic = good.getPicture();
            String[] pics = pic.split(";");
            goodsListDTO.setPictures(pics);
            goodsListDTOS.add(goodsListDTO);
        }
        return R.ok(goodsListDTOS);
    }
}
