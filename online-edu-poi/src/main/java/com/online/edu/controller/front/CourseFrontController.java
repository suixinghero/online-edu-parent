package com.online.edu.controller.front;

import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.service.front.CourseFrontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-09 22:57
 * @description 提供给前台用的controller
 */
@RestController
@RequestMapping("/edu/courseFront")
@CrossOrigin
public class CourseFrontController {
    private Logger logger = LoggerFactory.getLogger(CourseFrontController.class);
    @Autowired
    private CourseFrontService courseFrontService;
    //简单的分页查询课程信息
    @GetMapping("/getCourseFrontByPage/{page}/{limit}")
    public R getCourseFrontByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        try {
            logger.info("简单的分页查询课程信息 开始。 page:{} limit:{}",  page, limit);
            //查询结果
            R result = courseFrontService.getCourseFrontByPage(page,limit);
            logger.info("简单的分页查询课程信息 完成。 page:{} limit:{} 查询结果:result: {}",  page, limit, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("简单的分页查询课程信息 失败。 page:{} limit:{} 异常信息{}", page, limit, e.getMessage());
            return R.error();
        }
    }

    //根据课程id获取网站课程详情页信息
    @GetMapping("/queryCourseWebVoById/{id}")
    public R queryCourseWebVoById(@PathVariable("id") String id) {
        try {
            logger.info("根据课程id获取网站课程详情页信息 开始。 ", id);
            //查询结果
            R result = courseFrontService.queryCourseWebVoById(id);
            logger.info("根据课程id获取网站课程详情页信息 完成。  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("根据课程id获取网站课程详情页信息 失败。  异常信息{}", id, e.getMessage());
            return R.error();
        }
    }
}
