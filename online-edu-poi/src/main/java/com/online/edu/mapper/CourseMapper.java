package com.online.edu.mapper;

import com.online.edu.po.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.online.edu.vo.CoursePublishVo;
import com.online.edu.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xujin
 * @since 2020-04-30
 */
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 根据课程id获取课程发布基本信息
     * @param id 课程ID
     * @return 课程发布页面显示对象
     */
    CoursePublishVo selectCoursePublishVoById(String id);

    /**
     * 根据课程id获取网站课程详情页信息
     * @param id 课程ID
     * @return 网站课程详情页显示对象
     */
    CourseWebVo selectCourseWebVoById(String id);
}
