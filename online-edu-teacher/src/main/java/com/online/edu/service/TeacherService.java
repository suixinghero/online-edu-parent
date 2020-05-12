package com.online.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.po.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.req.TeacherAddReq;
import com.online.edu.req.TeacherQueryReq;
import com.online.edu.req.TeacherUpdateReq;


/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-04-02
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 查询所有的讲师功能
     * @return 查询结果
     */
    R queryTeacherList();

    /**
     * 通过老师ID逻辑删除老师信息
     * @param id 老师ID
     * @return 通用返回结果
     */
    R removeByTeacherId(Long id);
    /**
     * 简单分页查询讲师列表
     * @param page 起始页码
     * @param limit 分页条数
     * @return 查询结果
     */
    R pageList(Integer page, Integer limit);

    /**
     * 多条件组合查询讲师列表带分页
     * @param teacherPage 分页参数
     * @param req 查询条件
     *@return 查询结果
     */
    R moreConditionPageList(Page<Teacher> teacherPage, TeacherQueryReq req);

    /**
     * 添加讲师
     * @param req
     * @return 通用返回对象
     */
    R addTeacher(TeacherAddReq req);

    /**
     * 根据Id查询讲师
     * @param id 讲师ID
     * @return 查询结果
     */
    R QueryTeacherById(Long id);

    /**
     * 根据条件更新讲师
     * @param req 更新条件
     * @return 更新结果
     */
    R UpdateTeacher(TeacherUpdateReq req);
}
