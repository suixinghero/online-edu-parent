package com.online.edu.service;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xujin
 * @createtime 2020-05-06 15:43
 * @description
 */
@FeignClient(value = "online-edu-oss")
public interface FileUpLoadClientService {
    //上传图片
    @PostMapping("/edu/oss/upload")
    R uploadImage(@RequestParam("file") MultipartFile file, @RequestParam(value = "directoryName") String directoryName);
}
