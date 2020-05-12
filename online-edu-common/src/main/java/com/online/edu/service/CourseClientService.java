package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.req.CourseInfoFormAddReq;
import com.online.edu.req.CourseInfoFormUpdateReq;
import com.online.edu.req.CourseQueryReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-06 15:33
 * @description
 */
@FeignClient(value = "online-edu-poi")
public interface CourseClientService {
    //添加课程信息
    @PostMapping("/edu/course/addCourseInfo")
    R  addCourseInfo(@RequestBody CourseInfoFormAddReq req);
    //根据ID查询课程信息
    @GetMapping("/edu/course/queryCourseInfo/{id}")
    R queryCourseInfo(@PathVariable("id")String id);

    //根据ID修改课程信息
    @PutMapping("/edu/course/updateCourseInfo")
    R updateCourseInfo(@RequestBody CourseInfoFormUpdateReq req);

    //多条件组合查询课程列表带分页
    @PostMapping("/edu/course/moreConditionPageList/{page}/{limit}")
    R moreConditionPageList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                                   @RequestBody(required = false) CourseQueryReq req);
    //通过ID删除课程信息
    @DeleteMapping("/edu/course/deleteCourseInfoById/{id}")
    R deleteCourseInfoById(@PathVariable("id")String id);
    //根据课程id获取课程发布基本信息
    @GetMapping("/edu/course/queryCoursePublishVoById/{id}")
    R queryCoursePublishVoById(@PathVariable("id")String id);
    //根据id发布课程
    @GetMapping("/edu/course/publishCourseById/{id}")
    R publishCourseById(@PathVariable("id")String id);

    //根据教师ID查询课程信息
    @GetMapping("/edu/course/queryCourseInfoByTeacherId/{id}")
    R queryCourseInfoByTeacherId(@PathVariable("id")String id);
}
