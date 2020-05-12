package com.online.edu.service.front;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.common.R;
import com.online.edu.po.Teacher;


/**
 * @author xujin
 * @createtime 2020-05-09 21:15
 * @description
 */

public interface TeacherFrontService  extends IService<Teacher> {
    /**
     * 简单分页查询讲师列表
     * @param page 起始页码
     * @param limit 分页条数
     * @return 查询结果
     */
    R getTeacherFrontByPage(Integer page, Integer limit);

    /**
     * 通过教师ID查询课程信息和教师基本信息
     * @param id 教师ID
     * @return 通用返回对象
     */
    R getCourseInfoByTeacherInfo(String id);
}
