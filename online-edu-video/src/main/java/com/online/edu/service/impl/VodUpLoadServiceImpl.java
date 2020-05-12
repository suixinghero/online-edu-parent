package com.online.edu.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.online.edu.common.R;
import com.online.edu.properties.VodProperties;
import com.online.edu.service.VideoClientService;
import com.online.edu.service.VodUpLoadService;
import com.online.edu.util.AliyunVodSDKUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author xujin
 * @createtime 2020-05-05 16:06
 * @description
 */
@Service
public class VodUpLoadServiceImpl implements VodUpLoadService {
    @Autowired
    private VodProperties vodProperties;

    @Override
    public R upLoadVod(MultipartFile file) throws IOException {
        //获取上传视频的名称
        String title=file.getOriginalFilename().substring(0,Objects.requireNonNull(file.getOriginalFilename()).indexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyid(), vodProperties.getKeysecret(),
                title, file.getOriginalFilename(), file.getInputStream());
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        Assert.isTrue(response.isSuccess(),"上传视频失败");
        return  R.ok().data("videoId",response.getVideoId());
    }

    @Override
    public R deleteVodById(String id) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyid(),vodProperties.getKeysecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(id);
        client.getAcsResponse(request);
        return R.ok();
    }

    @Override
    public R deleteVods(List<String> vodIds) throws ClientException {
        String idString = vodIdsToString(vodIds);
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyid(),vodProperties.getKeysecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(idString);
        client.getAcsResponse(request);
        return R.ok();
    }


    //把视频ID集合转换成字符串
    private String vodIdsToString(List<String> vodIds) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < vodIds.size(); i++) {
            if (i == vodIds.size()-1){
                stringBuilder.append(vodIds.get(i));
                break;
            }
            stringBuilder.append(vodIds.get(i)).append(",");
        }
        return stringBuilder.toString();
    }

    @Override
    public R getVideoPlayAuth(String id) throws ClientException {
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyid(), vodProperties.getKeysecret());
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        //设置请求参数
        request.setVideoId(id);
        //获取请求响应
        GetVideoPlayAuthResponse response  = client.getAcsResponse(request);
        //输出请求结果
        //播放凭证
        String playAuth = response.getPlayAuth();
        return  R.ok().data("playAuth",playAuth);
    }

}
