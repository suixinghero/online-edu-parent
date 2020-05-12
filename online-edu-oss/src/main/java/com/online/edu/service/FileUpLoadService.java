package com.online.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author xujin
 * @createtime 2020-04-17 14:03
 * @description
 */

public interface FileUpLoadService {
    /**
     * 上传图片
     * @param file 上传文件项
     * @param directoryName 目录名称
     * @return 头像url
     */
    String uploadImage(MultipartFile file,String directoryName) throws IOException;
}
