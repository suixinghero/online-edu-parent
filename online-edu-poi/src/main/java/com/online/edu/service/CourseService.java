package com.online.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.PageReq;
import com.online.edu.common.R;
import com.online.edu.po.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.req.CourseInfoFormAddReq;
import com.online.edu.req.CourseInfoFormUpdateReq;
import com.online.edu.req.CourseQueryReq;
import com.online.edu.vo.CoursePublishVo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-04-30
 */
public interface CourseService extends IService<Course> {
    /**
     * 添加课程信息
     * @param req 编辑课程基本信息的表单添加对象
     * @return 通用返回对象
     */
    R addCourseInfo(CourseInfoFormAddReq req);

    /**
     * 根据ID查询课程信息
     * @param id 课程ID
     * @return 通用返回对象
     */
    R queryCourseInfo(String id);

    /**
     * 根据ID修改课程信息
     * @param req 编辑课程基本信息的表单更新对象
     * @return 通用返回对象
     */
    R updateCourseInfo(CourseInfoFormUpdateReq req);

    /**
     * 多条件组合查询课程列表带分页
     * @param pageReq 分页封装对象
     * @req 查询条件
     * @return 通用返回对象
     */
    R moreConditionPageList(Page<Course> pageReq,CourseQueryReq req);

    /**
     * 通过ID删除课程信息
     * @param id 课程ID
     * @return 通用返回对象
     */
    R deleteCourseInfoById(String id);
    /**
     * 根据课程id获取课程发布基本信息
     * @param id 课程ID
     * @return 通用返回对象
     */
    R queryCoursePublishVoById(String id);

    /**
     * 根据id发布课程
     * @param id  课程ID
     * @return 通用返回对象
     */
    R publishCourseById(String id);

    /**
     * 根据教师ID查询课程信息
     * @param id 教师ID
     * @return 通用返回对象
     */
    R queryCourseInfoByTeacherId(String id);
}
