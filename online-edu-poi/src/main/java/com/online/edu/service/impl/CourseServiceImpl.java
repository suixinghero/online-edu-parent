package com.online.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.online.edu.common.PageReq;
import com.online.edu.common.R;
import com.online.edu.constants.Constants;
import com.online.edu.mapper.*;
import com.online.edu.po.*;
import com.online.edu.req.CourseInfoFormAddReq;
import com.online.edu.req.CourseInfoFormUpdateReq;
import com.online.edu.req.CourseQueryReq;
import com.online.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.service.VodUpLoadClientService;
import com.online.edu.vo.CourseInfoFormVo;
import com.online.edu.vo.CoursePublishVo;
import com.online.edu.vo.CourseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-04-30
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private VodUpLoadClientService vodUpLoadClientService;
    @Transactional
    @Override
    public R addCourseInfo(CourseInfoFormAddReq req) {
        req = Optional.ofNullable(req).orElse(new CourseInfoFormAddReq());
        Course course = Course.newBuilder().teacherId(req.getTeacherId()).subjectId(req.getSubjectId())
                .title(req.getTitle()).price(req.getPrice()).lessonNum(req.getLessonNum())
                .cover(req.getCover()).build();
        int courseIsSuccess = baseMapper.insert(course);
        Assert.isTrue(courseIsSuccess > 0,"添加课程失败");
        CourseDescription courseDescription = CourseDescription.newBuilder().id(course.getId()).description(req.getDescription()).build();
        int courseDescIsSuccess = courseDescriptionMapper.insert(courseDescription);
        Assert.isTrue(courseDescIsSuccess > 0,"添加课程描述失败");
        return R.ok().data("courseId",course.getId());
    }

    @Override
    public R queryCourseInfo(String id) {
        Course course = baseMapper.selectById(id);
        Assert.isTrue(course != null,"查询课程信息失败");
        CourseInfoFormVo courseInfoFormVo = CourseInfoFormVo.newBuilder().teacherId(course.getTeacherId()).subjectId(course.getSubjectId())
                .title(course.getTitle()).price(course.getPrice()).lessonNum(course.getLessonNum())
                .cover(course.getCover()).id(course.getId()).build();
        CourseDescription courseDescription = courseDescriptionMapper.selectById(course.getId());
        Assert.isTrue(courseDescription != null,"查询课程描述信息失败");
        courseInfoFormVo.setDescription(courseDescription.getDescription());
        Subject secondSubject = subjectMapper.selectById(course.getSubjectId());
        Assert.isTrue(secondSubject != null,"查询课程分类信息失败");
        courseInfoFormVo.setSubjectParentId(secondSubject.getParentId());
        return R.ok().data("item",courseInfoFormVo);
    }

    @Override
    @Transactional
    public R updateCourseInfo(CourseInfoFormUpdateReq req) {
        req = Optional.ofNullable(req).orElse(new CourseInfoFormUpdateReq());
        Course course = Course.newBuilder().id(req.getId()).teacherId(req.getTeacherId()).subjectId(req.getSubjectId())
                .title(req.getTitle()).price(req.getPrice()).lessonNum(req.getLessonNum())
                .cover(req.getCover()).build();
        int updateCourseCount = baseMapper.updateById(course);
        Assert.isTrue(updateCourseCount > 0,"更新课程信息失败");
        CourseDescription courseDescription = CourseDescription.newBuilder().id(req.getId()).description(req.getDescription()).build();
        int updateDescCount = courseDescriptionMapper.updateById(courseDescription);
        Assert.isTrue(updateDescCount > 0,"更新课描述程信息失败");
        return R.ok().data("courseId",course.getId());
    }

    @Override
    public R moreConditionPageList(Page<Course> pageReq,CourseQueryReq req) {
        IPage<Course> res = null;
        if (req == null) {
            res = baseMapper.selectPage(pageReq, null);
            return R.ok().data("total",res.getTotal()).data("items",buildCourseVos(res.getRecords()));
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(req.getTitle())) {
            queryWrapper.like("title", req.getTitle());
        }
        if (!StringUtils.isEmpty(req.getTeacherId())) {
            queryWrapper.eq("teacher_id", req.getTeacherId());
        }
        if (!StringUtils.isEmpty(req.getSubjectId())) {
            queryWrapper.eq("subject_id", req.getSubjectId());
        }
        res = baseMapper.selectPage(pageReq, queryWrapper);
        return R.ok().data("total",res.getTotal()).data("items",buildCourseVos(res.getRecords()));
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

    @Transactional
    @Override
    public R deleteCourseInfoById(String id) {
        QueryWrapper<CourseDescription> descriptionWrapper = new QueryWrapper<>();
        descriptionWrapper.eq("id",id);
        //删除课程描述
        int desCount = courseDescriptionMapper.delete(descriptionWrapper);
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",id);
        videoWrapper.select("video_source_id");
        //批量删除小节视频
        List<Video> videos = videoMapper.selectList(videoWrapper);
        if (videos != null) {
            List<String> videoSourceIds = new ArrayList<>();
            videos.forEach(v->{
                videoSourceIds.add(v.getVideoSourceId());
            });
            R result = vodUpLoadClientService.deleteVods(videoSourceIds);
            Assert.isTrue(result.getSuccess(),"批量删除小节视频失败");
        }
        //删除课程小节
        int videoCount = videoMapper.delete(videoWrapper);
        QueryWrapper<Chapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        //删除课程章节
        int chapterCount = chapterMapper.delete(chapterWrapper);
        QueryWrapper<Course> courseWrapper = new QueryWrapper<>();
        courseWrapper.eq("id",id);
        //删除课程
        int courseCount = baseMapper.delete(courseWrapper);
        Assert.isTrue(courseCount > 0 && desCount >0 && chapterCount >= 0 && videoCount >= 0,
                "删除课程信息失败");
        return R.ok();
    }

    @Override
    public R queryCoursePublishVoById(String id) {
        CoursePublishVo coursePublishVo = baseMapper.selectCoursePublishVoById(id);
        return R.ok().data("item",coursePublishVo);
    }

    @Override
    public R publishCourseById(String id) {
        Course course = Course.newBuilder().id(id).status(Constants.CourseStatus.NORMAL).build();
        int count = baseMapper.updateById(course);
        Assert.isTrue(count > 0,"发布课程失败");
        return R.ok();
    }

    @Override
    public R queryCourseInfoByTeacherId(String id) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        queryWrapper.orderByDesc("gmt_modified");
        List<Course> courses = baseMapper.selectList(queryWrapper);
        return R.ok().data("items",buildCourseVos(courses));
    }
}
