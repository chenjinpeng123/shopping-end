package com.example.demo.goods.entity.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class UploadGoodsVO {
    private String name;
    private Double price;
    private Long groupId;
    private String introduction;
    private Double balance;
    private String address;
    private List<MultipartFile> files;
    private List<MultipartFile> colorFiles;
}
