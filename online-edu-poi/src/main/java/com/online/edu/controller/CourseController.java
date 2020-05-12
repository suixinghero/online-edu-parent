package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.PageReq;
import com.online.edu.common.R;
import com.online.edu.po.Course;
import com.online.edu.req.CourseInfoFormAddReq;
import com.online.edu.req.CourseInfoFormUpdateReq;
import com.online.edu.req.CourseQueryReq;
import com.online.edu.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-04-30
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class CourseController {
    private Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;
    //添加课程信息
    @PostMapping("/addCourseInfo")
    public R  addCourseInfo(@RequestBody CourseInfoFormAddReq req){
        try {
            logger.info("添加课程信息 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = courseService.addCourseInfo(req);
            logger.info("添加课程信息 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("添加课程信息 失败。req:{} 异常信息{}", JSON.toJSONString(req), e.getMessage());
            return R.error();
        }
    }

    //根据ID查询课程信息
    @GetMapping("/queryCourseInfo/{id}")
    public R queryCourseInfo(@PathVariable("id")String id){
        try {
            logger.info("查询课程信息 开始。id:{} ", id);
            //查询结果
            R result = courseService.queryCourseInfo(id);
            logger.info("查询课程信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("查询课程信息 失败。id:{} 异常信息{}", id, e.getMessage());
            return R.error();
        }
    }

    //根据ID修改课程信息
    @PutMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoFormUpdateReq req){
        try {
            logger.info("更新课程信息 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = courseService.updateCourseInfo(req);
            logger.info("更新课程信息 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("更新课程信息 失败。req:{} 异常信息{}", JSON.toJSONString(req), e.getMessage());
            return R.error();
        }
    }

    //多条件组合查询课程列表带分页
    @PostMapping("/moreConditionPageList/{page}/{limit}")
    public R moreConditionPageList(@PathVariable Integer page, @PathVariable Integer limit,
                                   @RequestBody(required = false) CourseQueryReq req) {
        try {
            logger.info("多条件组合查询课程列表带分页 开始。req:{} page:{} limit:{}", JSON.toJSONString(req), page, limit);
            //封装参数
            Page<Course> pageReq = new Page<>(page,limit);
            //查询结果
            R result = courseService.moreConditionPageList(pageReq,req);
            logger.info("多条件组合查询课程列表带分页 完成。req:{} page:{} limit:{} 查询结果:result: {}", JSON.toJSONString(req), page, limit, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("多条件组合查询课程列表带分页 失败。req:{} page:{} limit:{} 异常信息{}", JSON.toJSONString(req), page, limit, e.getMessage());
            return R.error();
        }
    }

    //通过ID删除课程信息
    @DeleteMapping("/deleteCourseInfoById/{id}")
    public R deleteCourseInfoById(@PathVariable("id")String id){
        try {
            logger.info("删除课程信息 开始。id:{} ", id);
            //查询结果
            R result = courseService.deleteCourseInfoById(id);
            logger.info("删除课程信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("删除课程信息 失败。id:{} 异常信息{}", id, e.getMessage());
            return R.error();
        }
    }
    //根据课程id获取课程发布基本信息
    @GetMapping("/queryCoursePublishVoById/{id}")
    public R queryCoursePublishVoById(@PathVariable("id")String id){
        try {
            logger.info("根据课程id获取课程发布基本信息 开始。id:{} ", id);
            //查询结果
            R result = courseService.queryCoursePublishVoById(id);
            logger.info("根据课程id获取课程发布基本信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("根据课程id获取课程发布基本信息 失败。id:{} 异常信息{}", id, e.getMessage());
            return R.error();
        }
    }

    //根据id发布课程
    @GetMapping("/publishCourseById/{id}")
    public R publishCourseById(@PathVariable("id")String id){
        try {
            logger.info("根据id发布课程 开始。id:{} ", id);
            //查询结果
            R result = courseService.publishCourseById(id);
            logger.info("根据id发布课程 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("根据id发布课程 失败。id:{} 异常信息{}", id, e.getMessage());
            return R.error();
        }
    }

    //根据教师ID查询课程信息
    @GetMapping("/queryCourseInfoByTeacherId/{id}")
    public R queryCourseInfoByTeacherId(@PathVariable("id")String id){
        try {
            logger.info("根据教师ID查询课程信息 开始。id:{} ", id);
            //查询结果
            R result = courseService.queryCourseInfoByTeacherId(id);
            logger.info("根据教师ID查询课程信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("根据教师ID查询课程信息 失败。id:{} 异常信息{}", id, e.getMessage());
            return R.error();
        }
    }
}

