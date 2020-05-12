package com.online.edu.controller.front;

import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.controller.VodUpLoadController;
import com.online.edu.service.Front.VodUpLoadFrontService;
import com.online.edu.service.VodUpLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-10 15:16
 * @description 专门用于前台的controller
 */
@RestController
@RequestMapping("/edu/vodFront")
@CrossOrigin
public class VodUpLoadFrontController {
    private Logger logger = LoggerFactory.getLogger(VodUpLoadFrontController.class);
    @Autowired
    private VodUpLoadFrontService vodUpLoadFrontService;
    //通过视频ID获取播放凭证
    @GetMapping("/getVideoPlayAuth/{id}")
    public R getVideoPlayAuth(@PathVariable("id")String id){
        try {
            logger.info("通过视频ID获取播放凭证 开始。id{} ",id);
            R result = vodUpLoadFrontService.getVideoPlayAuth(id);
            logger.info("通过视频ID获取播放凭证 完成。id{}, result{}",id,JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过视频ID获取播放凭证 失败。id{} ,异常信息{}",id, e.getMessage());
            return R.error();
        }
    }
}
