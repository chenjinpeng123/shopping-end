package com.example.demo.user.entity.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadPicture {
    private Long userId;
    private MultipartFile file;
}
