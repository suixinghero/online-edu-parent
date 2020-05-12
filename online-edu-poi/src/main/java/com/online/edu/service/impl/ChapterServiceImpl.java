package com.online.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.online.edu.common.R;
import com.online.edu.mapper.VideoMapper;
import com.online.edu.po.Chapter;
import com.online.edu.mapper.ChapterMapper;
import com.online.edu.po.Video;
import com.online.edu.req.ChapterAddReq;
import com.online.edu.req.ChapterUpdateReq;
import com.online.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.vo.ChapterInfoVo;
import com.online.edu.vo.ChapterVo;
import com.online.edu.vo.VideoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程章节 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoMapper videoMapper;
    @Override
    public R queryChapterByCourseId(String id) {
        //得到课程章节列表
        QueryWrapper<Chapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        chapterWrapper.orderByAsc("sort","id");
        List<Chapter> chapters = baseMapper.selectList(chapterWrapper);
        List<ChapterInfoVo> chapterInfoVos = new ArrayList<>();
        Optional.ofNullable(chapters).orElse(Collections.emptyList()).forEach(c->{
            chapterInfoVos.add(ChapterInfoVo.newBuilder().id(c.getId()).title(c.getTitle()).build());
        });
        //得到课程小节列表
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",id);
        videoWrapper.orderByAsc("sort","id");
        List<Video> videos = videoMapper.selectList(videoWrapper);
        //把课程小节按课程章节ID分组
        Map<String, List<Video>> groupByChapterId = Optional.ofNullable(videos).orElse(Collections.emptyList()).stream().collect(Collectors.groupingBy(Video::getChapterId));
        chapterInfoVos.forEach(c->{
            List<VideoVo> videoVos = new ArrayList<>();
            Optional.ofNullable(groupByChapterId.get(c.getId())).orElse(Collections.emptyList()).forEach(d->{
                videoVos.add(VideoVo.newBuilder().id(d.getId()).title(d.getTitle()).videoSourceId(d.getVideoSourceId()).
                        free(d.getIsFree()).build());
            });
            c.setChildren(videoVos);
        });
        return R.ok().data("items",chapterInfoVos);
    }

    @Override
    public R addChapter(ChapterAddReq req) {
        req = Optional.ofNullable(req).orElse(new ChapterAddReq());
        Chapter chapter = Chapter.newBuilder().courseId(req.getCourseId()).title(req.getTitle()).sort(req.getSort()).build();
        int count = baseMapper.insert(chapter);
        Assert.isTrue(count > 0,"添加课程章节失败");
        return R.ok();
    }

    @Override
    public R queryChapterById(String id) {
        Chapter chapter = baseMapper.selectById(id);
        chapter = Optional.ofNullable(chapter).orElse(new Chapter());
        ChapterVo chapterVo = ChapterVo.newBuilder().id(chapter.getId()).courseId(chapter.getCourseId())
                .title(chapter.getTitle()).sort(chapter.getSort()).build();
        return R.ok().data("item",chapterVo);
    }

    @Override
    public R updateChapter(ChapterUpdateReq req) {
        req = Optional.ofNullable(req).orElse(new ChapterUpdateReq());
        Chapter chapter = Chapter.newBuilder().id(req.getId()).title(req.getTitle()).sort(req.getSort()).build();
        int count = baseMapper.updateById(chapter);
        Assert.isTrue(count > 0,"更新章节失败");
        return R.ok();
    }

    @Override
    public R deleteChapter(String id) {
        QueryWrapper<Video> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("chapter_id",id);
        Integer count = videoMapper.selectCount(videoWrapper);
        Assert.isTrue(count == 0,"该章节下有小节,不可删除");
        int chapterCount = baseMapper.deleteById(id);
        Assert.isTrue(chapterCount > 0,"章节删除失败");
        return R.ok();
    }
}
