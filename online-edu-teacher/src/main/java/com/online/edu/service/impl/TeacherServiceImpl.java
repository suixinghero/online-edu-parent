package com.online.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.R;
import com.online.edu.po.Teacher;
import com.online.edu.mapper.TeacherMapper;
import com.online.edu.req.TeacherAddReq;
import com.online.edu.req.TeacherQueryReq;
import com.online.edu.req.TeacherUpdateReq;
import com.online.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.vo.TeacherVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-04-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public R queryTeacherList() {
        List<Teacher> teachers = baseMapper.selectList(null);
        List<TeacherVo> teacherVos = buildTeacherVos(teachers);
        return R.ok().data("items",teacherVos);
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
    public R removeByTeacherId(Long id) {
        int count = baseMapper.deleteById(id);
        Assert.isTrue(count>0, "通过老师ID逻辑删除老师信息失败：" + count);
        return R.ok();
    }

    @Override
    public R pageList(Integer page, Integer limit) {
        Page<Teacher> pageReq = new Page<>(page, limit);
        IPage<Teacher> res = baseMapper.selectPage(pageReq,null);
        List<TeacherVo> teacherVos = buildTeacherVos(res.getRecords());
        return R.ok().data("total",res.getTotal()).data("items",teacherVos);
    }

    @Override
    public R moreConditionPageList(Page<Teacher> teacherPage, TeacherQueryReq req) {
        //关键：TeacherQueryReq有传递过来的条件值，判断，如果有值，拼接条件
        IPage<Teacher> res = null;
        if (req == null) {
            res = baseMapper.selectPage(teacherPage, null);
            return R.ok().data("total",res.getTotal()).data("items",buildTeacherVos(res.getRecords()));
        }
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(req.getName())) {
            queryWrapper.like("name", req.getName());
        }
        if (req.getLevel() != null) {
            queryWrapper.eq("level", req.getLevel());
        }
        if (!StringUtils.isEmpty(req.getBegin())) {
            queryWrapper.ge("gmt_create", req.getBegin());
        }
        if (!StringUtils.isEmpty(req.getEnd())) {
            queryWrapper.le("gmt_create", req.getEnd());
        }
        res = baseMapper.selectPage(teacherPage, queryWrapper);
        return R.ok().data("total",res.getTotal()).data("items",buildTeacherVos(res.getRecords()));
    }

    @Override
    public R addTeacher(TeacherAddReq req) {
        Teacher teacher = buildTeacher(req);
        int count = baseMapper.insert(teacher);
        Assert.isTrue(count>0, "添加讲师失败：" + count);
        return R.ok();
    }


    private Teacher buildTeacher(TeacherAddReq req) {
        req = Optional.ofNullable(req).orElse(new TeacherAddReq());
        return Teacher.newBuilder().name(req.getName())
                .intro(req.getIntro()).career(req.getCareer()).level(req.getLevel())
                .avatar(req.getAvatar()).sort(req.getSort()).build();
    }

    @Override
    public R QueryTeacherById(Long id) {
        Teacher teacher = baseMapper.selectById(id);
        TeacherVo teacherVo = buildTeacherVo(teacher);
        return R.ok().data("items",teacherVo);
    }


    private TeacherVo buildTeacherVo(Teacher teacher) {
        teacher = Optional.ofNullable(teacher).orElse(new Teacher());
        return TeacherVo.newBuilder().id(teacher.getId()).name(teacher.getName()).intro(teacher.getIntro())
                .career(teacher.getCareer()).level(teacher.getLevel()).avatar(teacher.getAvatar()).sort(teacher.getSort())
                .isDeleted(teacher.getIsDeleted()).gmtCreate(teacher.getGmtCreate()).gmtModified(teacher.getGmtModified()).build();
    }

    @Override
    public R UpdateTeacher(TeacherUpdateReq req) {
        Teacher teacher = buildUpdateReq(req);
        int count = baseMapper.updateById(teacher);
        Assert.isTrue(count>0, "根据条件更新讲师失败：" + count);
        return R.ok();
    }
    private Teacher buildUpdateReq(TeacherUpdateReq req) {
        req = Optional.ofNullable(req).orElse(new TeacherUpdateReq());
        return Teacher.newBuilder().id(req.getId()).name(req.getName())
                .intro(req.getIntro()).career(req.getCareer()).level(req.getLevel())
                .avatar(req.getAvatar()).sort(req.getSort()).gmtCreate(req.getGmtCreate())
                .gmtModified(req.getGmtModified()).build();
    }

}
