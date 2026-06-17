package com.itheima.controller;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /**
     * 上传文件 - 参数名file
     */
    @PostMapping("/upload")
    public Result upload(String username, Integer age , MultipartFile file) throws Exception {
        log.info("上传文件：{}, {}, {}", username, age, file);
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+ extension;

        if(!file.isEmpty()){
            file.transferTo(new File("D:\\images\\" + newFileName));
        }
        return Result.success();
    }

}