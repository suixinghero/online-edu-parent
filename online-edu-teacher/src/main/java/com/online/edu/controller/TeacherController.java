package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.po.Teacher;
import com.online.edu.req.TeacherAddReq;
import com.online.edu.req.TeacherQueryReq;
import com.online.edu.req.TeacherUpdateReq;
import com.online.edu.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-04-02
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class TeacherController {
    private Logger logger = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherService teacherService;

    //查询所有的讲师功能
    @GetMapping("/queryTeacherList")
    public R queryTeacherList() {
        try {
            logger.info("查询所有的讲师功能 开始。");
            //查询结果
            R result = teacherService.queryTeacherList();
            logger.info("查询所有的讲师功能 完成。查询结果:result: {}", JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("查询所有的讲师功能 失败。异常信息{}", e.getMessage());
            return R.error();
        }
    }

    //按照讲师的id进行逻辑删除
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Long id) {
        try {
            logger.info("按照讲师的id进行逻辑删除 开始。req:{} ", id);
            //查询结果
            R result = teacherService.removeByTeacherId(id);
            logger.info("按照讲师的id进行逻辑删除 完成。req:{}查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("按照讲师的id进行逻辑删除 失败。req:{} 异常信息{}",id, e.getMessage());
            return R.error();
        }
    }

    //简单分页查询讲师列表
    @GetMapping("/pageList/{page}/{limit}")
    public R pageList(@PathVariable Integer page, @PathVariable Integer limit) {
        try {
            logger.info("简单分页查询讲师列表 开始。 page:{} limit:{}", page, limit);
            //查询结果
            R result = teacherService.pageList(page, limit);
            logger.info("简单分页查询讲师列表 完成。 page:{} limit:{} 查询结果:result: {}",  page, limit, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("简单分页查询讲师列表 失败。 page:{} limit:{} 异常信息{}", page, limit, e.getMessage());
            return R.error();
        }
    }

    //多条件组合查询讲师列表带分页
    @PostMapping("/moreConditionPageList/{page}/{limit}")
    public R moreConditionPageList(@PathVariable Integer page, @PathVariable Integer limit,
                                   @RequestBody(required = false) TeacherQueryReq req) {
        try {
            logger.info("多条件组合查询讲师列表带分页 开始。req:{} page:{} limit:{}", JSON.toJSONString(req), page, limit);
            //封装参数
            Page<Teacher> pageReq = new Page<>(page, limit);
            //查询结果
            R result = teacherService.moreConditionPageList(pageReq, req);
            logger.info("多条件组合查询讲师列表带分页 完成。req:{} page:{} limit:{} 查询结果:result: {}", JSON.toJSONString(req), page, limit, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("多条件组合查询讲师列表带分页 失败。req:{} page:{} limit:{} 异常信息{}", JSON.toJSONString(req), page, limit, e.getMessage());
            return R.error();
        }
    }

    //添加讲师
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody TeacherAddReq req) {
        try {
            logger.info("添加讲师 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = teacherService.addTeacher(req);
            logger.info("添加讲师 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("添加讲师 失败。req:{} 异常信息{}", JSON.toJSONString(req), e.getMessage());
            return R.error();
        }
    }

    //根据Id查询讲师
    @GetMapping("/queryTeacherById/{id}")
    public R queryTeacherById(@PathVariable Long id) {
        try {
            logger.info("根据Id查询讲师 开始。req:{} ", id);
            //查询结果
            R result = teacherService.QueryTeacherById(id);
            logger.info("根据Id查询讲师 完成。req:{}查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("根据Id查询讲师 失败。req:{} 异常信息{}",id, e.getMessage());
            return R.error();
        }
    }

    //根据条件更新讲师
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody TeacherUpdateReq req) {
        try {
            logger.info("根据条件更新讲师 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = teacherService.UpdateTeacher(req);
            logger.info("根据条件更新讲师 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("根据条件更新讲师 失败。req:{} 异常信息{}", JSON.toJSONString(req), e.getMessage());
            return R.error();
        }
    }


}

