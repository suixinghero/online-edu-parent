package com.onlin.edu;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.online.edu.util.AliyunVodSDKUtils;
import org.junit.Test;

import java.util.List;

/**
 * @author xujin
 * @createtime 2020-05-04 17:12
 * @description
 */

public class VodSdkTest {
    //账号AccessKey信息请填写(必选)
    private static String access_key_id = "";
    //账号AccessKey信息请填写(必选)
    private static String access_key_secret = "";
    /**
     * 获取视频播放凭证
     * @throws ClientException
     */
    @Test
    public void testGetVideoPlayAuth() throws ClientException {
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(access_key_id, access_key_secret);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            //设置请求参数
            request.setVideoId("68777ad070694d7ca06a8cbce871f262");
            //获取请求响应
            response = client.getAcsResponse(request);
            //输出请求结果
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    /**
     * 获取视频播放地址
     * @throws ClientException
     */
    @Test
    public void testGetPlayInfo() throws ClientException {
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(access_key_id, access_key_secret);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            //设置请求参数
            //注意：这里只能获取非加密视频的播放地址
            request.setVideoId("6bb7e05dc0db4714a7c20583342b205b");
            //获取请求响应
            response = client.getAcsResponse(request);
            //输出请求结果
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
