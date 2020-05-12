package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.req.VideoInfoFormAddReq;
import com.online.edu.req.VideoInfoFormUpdateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-06 14:53
 * @description
 */
@FeignClient(value = "online-edu-poi")
public interface VideoClientService {
    //添加课程小节信息
    @PostMapping("/edu/video/addVideo")
    R addVideo(@RequestBody VideoInfoFormAddReq req);
    //通过ID更新课程小节
    @PutMapping("/edu/video/updateVideo")
    R updateVideo(@RequestBody VideoInfoFormUpdateReq req);
    //通过ID查询课程小节
    @GetMapping("/edu/video/queryVideoById/{id}")
    R queryVideoById(@PathVariable("id") String id);
    //通过ID删除课程小节
    @DeleteMapping("/edu/video/deleteVideoById/{id}")
    R deleteVideoById(@PathVariable("id") String id);
}
