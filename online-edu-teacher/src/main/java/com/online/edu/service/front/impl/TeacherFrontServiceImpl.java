package com.online.edu.service.front.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.common.R;
import com.online.edu.mapper.TeacherMapper;
import com.online.edu.po.Teacher;
import com.online.edu.req.TeacherAddReq;
import com.online.edu.service.CourseClientService;
import com.online.edu.service.front.TeacherFrontService;
import com.online.edu.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author xujin
 * @createtime 2020-05-09 21:16
 * @description
 */
@Service
public class TeacherFrontServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherFrontService {
    @Autowired
    private CourseClientService courseClientService;

    @Override
    public R getTeacherFrontByPage(Integer page, Integer limit) {
        Page<Teacher> pageReq = new Page<>(page, limit);
        baseMapper.selectPage(pageReq,null);
        List<TeacherVo> teacherVos = buildTeacherVos(pageReq.getRecords());
        Map<String,Object> data = new HashMap<>();
        data.put("items", teacherVos);
        data.put("current", pageReq.getCurrent());
        data.put("pages", pageReq.getPages());
        data.put("size", pageReq.getSize());
        data.put("total", pageReq.getTotal());
        data.put("hasNext", pageReq.hasNext());
        data.put("hasPrevious", pageReq.hasPrevious());
        return R.ok().data(data);
    }



    private List<TeacherVo> buildTeacherVos(List<Teacher> data){
        List<TeacherVo> teacherVos = new ArrayList<>();
        Optional.ofNullable(data).orElse(Collections.emptyList()).forEach(t-> {
            teacherVos.add(TeacherVo.newBuilder().id(t.getId()).name(t.getName()).intro(t.getIntro()).
                    career(t.getCareer()).level(t.getLevel()).avatar(t.getAvatar()).sort(t.getSort()).isDeleted(t.getIsDeleted()).
                    gmtCreate(t.getGmtCreate()).gmtModified(t.getGmtModified()).build());
        });
        return teacherVos;
    }

    @Override
    public R getCourseInfoByTeacherInfo(String id) {
        Teacher teacher = baseMapper.selectById(id);
        R result = courseClientService.queryCourseInfoByTeacherId(id);
        Assert.isTrue(result.getSuccess(),"查询课程信息和教师基本信息失败");
        return R.ok().data("teacher",buildTeacherVo(teacher)).data("courseList",result.getData().get("items"));
    }
    private TeacherVo buildTeacherVo(Teacher teacher) {
        teacher = Optional.ofNullable(teacher).orElse(new Teacher());
        return TeacherVo.newBuilder().id(teacher.getId()).name(teacher.getName()).intro(teacher.getIntro())
                .career(teacher.getCareer()).level(teacher.getLevel()).avatar(teacher.getAvatar()).sort(teacher.getSort())
                .isDeleted(teacher.getIsDeleted()).gmtCreate(teacher.getGmtCreate()).gmtModified(teacher.getGmtModified()).build();
    }
}
