package com.example.demo.goods.service;

import com.example.demo.goods.entity.vo.UploadGoodsVO;
import com.example.demo.util.R;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;


public interface GoodService {
    R uploadGoods(HttpSession session, UploadGoodsVO uploadGoodsVO);
}
