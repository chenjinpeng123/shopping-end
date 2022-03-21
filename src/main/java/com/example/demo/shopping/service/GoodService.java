package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.vo.UploadGoodsVO;
import com.example.demo.util.R;
import org.springframework.web.multipart.MultipartFile;


public interface GoodService {
    R uploadGoods(UploadGoodsVO uploadGoodsVO);

    R uploadPic(MultipartFile file, Long groupId);
}
