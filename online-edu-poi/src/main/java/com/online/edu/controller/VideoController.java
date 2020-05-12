package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.req.ChapterAddReq;
import com.online.edu.req.VideoInfoFormAddReq;
import com.online.edu.req.VideoInfoFormUpdateReq;
import com.online.edu.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程小节 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/edu/video")
@CrossOrigin
public class VideoController {
    private Logger logger = LoggerFactory.getLogger(VideoController.class);
    @Autowired
    private VideoService videoService;
    //添加课程小节信息
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody VideoInfoFormAddReq req){
        try {
            logger.info("添加课程小节信息 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = videoService.addVideo(req);
            logger.info("添加课程小节信息 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("添加课程小节信息 失败。req:{} 异常信息{}", JSON.toJSONString(req),  e.getMessage());
            return R.error();
        }
    }
    //通过ID更新课程小节
    @PutMapping("/updateVideo")
    public R updateVideo(@RequestBody VideoInfoFormUpdateReq req){
        try {
            logger.info("通过ID更新课程小节 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = videoService.updateVideo(req);
            logger.info("通过ID更新课程小节 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过ID更新课程小节 失败。req:{} 异常信息{}", JSON.toJSONString(req),  e.getMessage());
            return R.error();
        }
    }

    //通过ID查询课程小节
    @GetMapping("/queryVideoById/{id}")
    public R queryVideoById(@PathVariable("id") String id){
        try {
            logger.info("通过ID查询课程小节 开始。id:{} ", id);
            //查询结果
            R result = videoService.queryVideoById(id);
            logger.info("通过ID查询课程小节 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过ID查询课程小节 失败。id:{} 异常信息{}", id,  e.getMessage());
            return R.error();
        }
    }
    //通过ID删除课程小节
    @DeleteMapping("/deleteVideoById/{id}")
    public R deleteVideoById(@PathVariable("id")String id){
        try {
            logger.info("通过ID删除课程小节 开始。id:{} ", id);
            //查询结果
            R result = videoService.deleteVideoById(id);
            logger.info("通过ID删除课程小节 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过ID删除课程小节 失败。id:{} 异常信息{}", id,  e.getMessage());
            return R.error();
        }
    }

}

