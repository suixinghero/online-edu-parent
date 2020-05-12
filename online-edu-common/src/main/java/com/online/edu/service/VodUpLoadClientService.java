package com.online.edu.service;

import com.online.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xujin
 * @createtime 2020-05-06 15:45
 * @description
 */
@FeignClient(value = "online-edu-video")
public interface VodUpLoadClientService {
    //上传视频到阿里云
    @PostMapping("/edu/vod/upLoadVod")
    R upLoadVod(@RequestParam("file") MultipartFile file);

    //删除阿里云端的视频
    @DeleteMapping("/edu/vod/deleteVodById/{id}")
    R deleteVodById(@PathVariable("id") String id);
    //批量删除阿里云端的视频
    @DeleteMapping("/edu/vod/deleteVods")
    R deleteVods(@RequestParam(value = "vodIds") List<String> vodIds);

    //通过视频ID获取播放凭证
    @GetMapping("/edu/vod/getVideoPlayAuth/{id}")
    R getVideoPlayAuth(@PathVariable("id")String id);
}
