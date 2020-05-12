package com.online.edu.controller;

import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.service.VodUpLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author xujin
 * @createtime 2020-05-05 15:46
 * @description
 */
@RestController
@RequestMapping("/edu/vod")
@CrossOrigin
public class VodUpLoadController {
    private Logger logger = LoggerFactory.getLogger(VodUpLoadController.class);
    @Autowired
    private VodUpLoadService vodUpLoadService;
    //上传视频到阿里云
    @PostMapping("/upLoadVod")
    public R upLoadVod(@RequestParam("file") MultipartFile file){
        try {
            logger.info("上传视频 开始。fileName{} ",file.getOriginalFilename());
            R result = vodUpLoadService.upLoadVod(file);
            logger.info("上传视频 完成。fileName{}, result{}", file.getOriginalFilename(),JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("上传视频 失败。fileName{} ,异常信息{}",file.getOriginalFilename(), e.getMessage());
            return R.error();
        }
    }

    //删除阿里云端的视频
    @DeleteMapping("/deleteVodById/{id}")
    public R deleteVodById(@PathVariable("id") String id){
        try {
            logger.info("删除阿里云端的视频 开始。id{} ",id);
            R result = vodUpLoadService.deleteVodById(id);
            logger.info("删除阿里云端的视频 完成。id{}, result{}",id,JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("删除阿里云端的视频 失败。id{} ,异常信息{}",id, e.getMessage());
            return R.error();
        }
    }

    //批量删除阿里云端的视频
    @DeleteMapping("/deleteVods")
    public R deleteVods(@RequestParam(value = "vodIds") List<String> vodIds){
        try {
            logger.info("批量删除阿里云端的视频 开始。vodIds{} ",JSON.toJSONString(vodIds));
            R result = vodUpLoadService.deleteVods(vodIds);
            logger.info("批量删除阿里云端的视频  完成。vodIds{}, result{}",JSON.toJSONString(vodIds),JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("批量删除阿里云端的视频 失败。vodIds{} ,异常信息{}",JSON.toJSONString(vodIds), e.getMessage());
            return R.error();
        }
    }

    //通过视频ID获取播放凭证
    @GetMapping("/getVideoPlayAuth/{id}")
    public R getVideoPlayAuth(@PathVariable("id")String id){
        try {
            logger.info("通过视频ID获取播放凭证 开始。id{} ",id);
            R result = vodUpLoadService.getVideoPlayAuth(id);
            logger.info("通过视频ID获取播放凭证 完成。id{}, result{}",id,JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过视频ID获取播放凭证 失败。id{} ,异常信息{}",id, e.getMessage());
            return R.error();
        }
    }

}
