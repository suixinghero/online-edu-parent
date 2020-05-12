package com.online.edu.controller;

import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.service.FileUpLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author xujin
 * @createtime 2020-04-16 17:12
 * @description
 */
@RestController
@RequestMapping("/edu/oss")
@CrossOrigin
public class FileUpLoadController {
    private Logger logger = LoggerFactory.getLogger(FileUpLoadController.class);
    @Autowired
    private FileUpLoadService fileUpLoadService;
    //上传图片
    @PostMapping("/upload")
    public R uploadImage(@RequestParam("file") MultipartFile file,@RequestParam(value = "directoryName") String directoryName){
        try {
            logger.info("上传图片 开始。file{} directoryName{}",JSON.toJSONString(file),directoryName);
            String url = fileUpLoadService.uploadImage(file,directoryName);
            logger.info("上传图片 完成。url{}", url);
            return R.ok().data("imgUrl",url);
        } catch (Exception e) {
            logger.error("上传图片 失败。异常信息{},directoryName{}", e.getMessage(),directoryName);
            return R.error();
        }
    }
}
