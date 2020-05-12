package com.online.edu.controller.front;

import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.controller.TeacherController;
import com.online.edu.service.TeacherService;
import com.online.edu.service.front.TeacherFrontService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-09 21:14
 * @description 专门提供与前台的controller
 */
@RestController
@RequestMapping("/edu/teacherFront")
@CrossOrigin
public class TeacherFrontController {
    private Logger logger = LoggerFactory.getLogger(TeacherFrontController.class);
    @Autowired
    private TeacherFrontService teacherFrontService;
    //简单分页查询讲师列表
    @GetMapping("/getTeacherFrontByPage/{page}/{limit}")
    public R getTeacherFrontByPage(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit) {
        try {
            logger.info("简单分页查询讲师列表 开始。 page:{} limit:{}", page, limit);
            //查询结果
            R result = teacherFrontService.getTeacherFrontByPage(page, limit);
            logger.info("简单分页查询讲师列表 完成。 page:{} limit:{} 查询结果:result: {}",  page, limit, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("简单分页查询讲师列表 失败。 page:{} limit:{} 异常信息{}", page, limit, e.getMessage());
            return R.error();
        }
    }

    //通过教师ID查询课程信息和教师基本信息
    @GetMapping("/getCourseInfoByTeacherInfo/{id}")
    public R getCourseInfoByTeacherInfo(@PathVariable("id")String id) {
        try {
            logger.info("通过教师ID查询课程信息和教师基本信息 开始。id{}", id);
            //查询结果
            R result = teacherFrontService.getCourseInfoByTeacherInfo(id);
            logger.info("通过教师ID查询课程信息和教师基本信息 完成。 id{}, 查询结果:result: {}", id ,JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过教师ID查询课程信息和教师基本信息 失败。 id{},异常信息{}", id, e.getMessage());
            return R.error();
        }
    }
}
