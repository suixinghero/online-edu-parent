package com.online.edu.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.online.edu.properties.OSSProperties;
import com.online.edu.service.FileUpLoadService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * @author xujin
 * @createtime 2020-04-17 14:04
 * @description
 */
@Service
public class FileUpLoadServiceImpl implements FileUpLoadService {
    @Autowired
    private OSSProperties ossProperties;
    @Override
    public String uploadImage(MultipartFile file,String directoryName) throws IOException {
        //1.获取上传文件名称，获取上传文件输入流
        //2.解决文件重名问题
        String suffix = file.getOriginalFilename().substring(Objects.requireNonNull(file.getOriginalFilename()).indexOf("."));
        //把文件的名称设置成唯一值,uuid
        String uuid=UUID.randomUUID().toString().replace("-","");
        String fileName=uuid+""+suffix;
        InputStream inputStream = file.getInputStream();
        //3.按日期分文件夹
        String folderName = new DateTime().toString("yyyy/MM/dd");
        //4.创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossProperties.getEndpoint(), ossProperties.getKeyid(), ossProperties.getKeysecret());
        ossClient.putObject(ossProperties.getBucketname(), directoryName + "/" +folderName+"/"+ fileName, inputStream);
        ossClient.shutdown();
        return "http://"+ossProperties.getBucketname()+"."+ossProperties.getEndpoint()+"/"+directoryName + "/" + folderName+"/"+ fileName;
    }
}
