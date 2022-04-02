package com.example.demo.shopping.service;

import com.example.demo.shopping.entity.vo.UpdateGoodVO;
import com.example.demo.shopping.entity.vo.UploadGoodsVO;
import com.example.demo.util.R;
import org.springframework.web.multipart.MultipartFile;


public interface GoodService {

    /** 上传商品 */
    R uploadGoods(UploadGoodsVO uploadGoodsVO);

    /** 上传图片*/
    R uploadPic(MultipartFile file, Long groupId);

    R changeState(Long id);

    R delete(Long id);

    R update(UpdateGoodVO updateGoodVO);
}
