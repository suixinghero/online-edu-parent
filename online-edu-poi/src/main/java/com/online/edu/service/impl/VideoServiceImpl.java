package com.online.edu.service.impl;

import com.online.edu.common.R;
import com.online.edu.po.Video;
import com.online.edu.mapper.VideoMapper;
import com.online.edu.req.VideoInfoFormAddReq;
import com.online.edu.req.VideoInfoFormUpdateReq;
import com.online.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.service.VodUpLoadClientService;
import com.online.edu.vo.VideoInfoFormVo;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * <p>
 * 课程小节 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {
    @Autowired
    private VodUpLoadClientService vodUpLoadClientService;
    @Override
    public R addVideo(VideoInfoFormAddReq req) {
        req = Optional.ofNullable(req).orElse(new VideoInfoFormAddReq());
        Video video = Video.newBuilder().title(req.getTitle()).courseId(req.getCourseId())
                .chapterId(req.getChapterId()).videoSourceId(req.getVideoSourceId())
                .sort(req.getSort()).isFree(req.getFree()).videoOriginalName(req.getVideoOriginalName()).build();
        int count = baseMapper.insert(video);
        Assert.isTrue(count > 0,"添加课程小节失败");
        return R.ok();
    }
    @Override
    public R updateVideo(VideoInfoFormUpdateReq req) {
        req = Optional.ofNullable(req).orElse(new VideoInfoFormUpdateReq());
        Video video = Video.newBuilder().id(req.getId()).title(req.getTitle()).chapterId(req.getChapterId())
                .chapterId(req.getChapterId()).videoSourceId(req.getVideoSourceId())
                .sort(req.getSort()).isFree(req.getFree()).videoOriginalName(req.getVideoOriginalName()).build();
        int count = baseMapper.updateById(video);
        Assert.isTrue(count > 0,"更新课程小节失败");
        return R.ok();
    }
    @Override
    public R queryVideoById(String id) {
        Video video = baseMapper.selectById(id);
        video = Optional.ofNullable(video).orElse(new Video());
        VideoInfoFormVo videoInfoFormVo = VideoInfoFormVo.newBuilder().id(video.getId()).title(video.getTitle())
                .courseId(video.getCourseId()).chapterId(video.getChapterId())
                .videoSourceId(video.getVideoSourceId()).sort(video.getSort())
                .free(video.getIsFree()).videoOriginalName(video.getVideoOriginalName()).build();
        return R.ok().data("item",videoInfoFormVo);
    }

    @Transactional
    @Override
    public R deleteVideoById(String id) {
        Video video = Optional.ofNullable(baseMapper.selectById(id)).orElse(new Video());
        if (!StringUtils.isEmpty(video.getVideoSourceId())) {
            //删除小节视频
            R result = vodUpLoadClientService.deleteVodById(video.getVideoSourceId());
            Assert.isTrue(result.getSuccess(), "课程小节视频删除失败");
        }
        //删除小节
        int count = baseMapper.deleteById(id);
        Assert.isTrue(count > 0,"课程小节删除失败");
        return R.ok();
    }
}
