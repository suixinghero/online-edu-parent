package com.online.edu.service.front;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.common.R;
import com.online.edu.po.Course;
import com.online.edu.vo.CourseWebVo;


/**
 * @author xujin
 * @createtime 2020-05-09 22:59
 * @description
 */

public interface CourseFrontService extends IService<Course> {
    /**
     * 简单的分页查询课程信息
     * @param page 起始页
     * @param limit 每页条数
     * @return 通用返回对象
     */
    R getCourseFrontByPage(Integer page, Integer limit);

    /**
     * 根据课程id获取网站课程详情页信息
     * @param id 课程ID
     * @return 通用返回对象
     */
    R queryCourseWebVoById(String id);


}
