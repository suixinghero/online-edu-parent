package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.req.TeacherAddReq;
import com.online.edu.req.TeacherQueryReq;
import com.online.edu.req.TeacherUpdateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-06 15:38
 * @description
 */
@FeignClient(value = "online-edu-teacher")
public interface TeacherClientService {
    //查询所有的讲师功能
    @GetMapping("/edu/teacher/queryTeacherList")
    R queryTeacherList();

    //按照讲师的id进行逻辑删除
    @DeleteMapping("/edu/teacher/{id}")
    R removeById(@PathVariable("id") Long id);

    //简单分页查询讲师列表
    @GetMapping("/edu/teacher/pageList/{page}/{limit}")
    R pageList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit);

    //多条件组合查询讲师列表带分页
    @PostMapping("/edu/teacher/moreConditionPageList/{page}/{limit}")
    R moreConditionPageList(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                                   @RequestBody(required = false) TeacherQueryReq req);

    //添加讲师
    @PostMapping("/edu/teacher/addTeacher")
    R addTeacher(@RequestBody TeacherAddReq req);

    //根据Id查询讲师
    @GetMapping("/edu/teacher/queryTeacherById/{id}")
    R queryTeacherById(@PathVariable("id") Long id);

    //根据条件更新讲师
    @PostMapping("/edu/teacher/updateTeacher")
    R updateTeacher(@RequestBody TeacherUpdateReq req);
}
