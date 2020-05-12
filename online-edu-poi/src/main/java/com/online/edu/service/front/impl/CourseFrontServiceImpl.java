package com.online.edu.service.front.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.common.R;
import com.online.edu.mapper.CourseMapper;
import com.online.edu.po.Course;
import com.online.edu.service.ChapterClientService;
import com.online.edu.service.front.CourseFrontService;
import com.online.edu.vo.CourseVo;
import com.online.edu.vo.CourseWebVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author xujin
 * @createtime 2020-05-09 22:59
 * @description
 */
@Service
public class CourseFrontServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseFrontService {
    @Autowired
    private ChapterClientService chapterClientService;
    @Override
    public R getCourseFrontByPage(Integer page, Integer limit) {
        Page<Course> pageReq = new Page<>(page, limit);
        baseMapper.selectPage(pageReq,null);
        List<CourseVo> courseVos = buildCourseVos(pageReq.getRecords());
        Map<String,Object> data = new HashMap<>();
        data.put("items", courseVos);
        data.put("current", pageReq.getCurrent());
        data.put("pages", pageReq.getPages());
        data.put("size", pageReq.getSize());
        data.put("total", pageReq.getTotal());
        data.put("hasNext", pageReq.hasNext());
        data.put("hasPrevious", pageReq.hasPrevious());
        return R.ok().data(data);
    }


    private List<CourseVo> buildCourseVos(List<Course> records) {
        List<CourseVo> courseVos = new ArrayList<>();
        Optional.ofNullable(records).orElse(Collections.emptyList()).forEach(c->{
            courseVos.add(CourseVo.newBuilder().id(c.getId()).teacherId(c.getTeacherId())
                    .subjectId(c.getSubjectId()).title(c.getTitle()).price(c.getPrice()).lessonNum(c.getLessonNum())
                    .cover(c.getCover()).buyCount(c.getBuyCount()).viewCount(c.getViewCount()).status(c.getStatus())
                    .gmtCreate(c.getGmtCreate()).gmtModified(c.getGmtModified()).build());
        });
        return courseVos;
    }

    @Override
    @Transactional
    public R queryCourseWebVoById(String id) {
        //获取课程信息
        CourseWebVo courseWebVo = baseMapper.selectCourseWebVoById(id);
        //更新课程浏览数
        Course course = Optional.ofNullable(baseMapper.selectById(id)).orElse(new Course());
        course.setViewCount(course.getViewCount()+1L);
        int count = baseMapper.updateById(course);
        R result = chapterClientService.queryChapterByCourseId(id);
        Assert.isTrue(count > 0 && result.getSuccess(),"获取课程信息失败");
        return R.ok().data("course", courseWebVo).data("chapterVoList", result.getData().get("items"));
    }

}
