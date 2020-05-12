package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.po.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.req.VideoInfoFormAddReq;
import com.online.edu.req.VideoInfoFormUpdateReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 课程章节 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
public interface VideoService extends IService<Video> {
    /**
     * 添加课程小节信息
     * @param req 编辑课时基本信息的表单添加对象
     * @return 通用返回对象
     */
    R addVideo(VideoInfoFormAddReq req);

    /**
     * 通过ID更新课程小节
     * @param req 编辑课时基本信息的表单更新对象
     * @return 通用返回对象
     */
    R updateVideo(VideoInfoFormUpdateReq req);

    /**
     * 通过ID查询课程小节
     * @param id 小节ID
     * @return 通用返回对象
     */
    R queryVideoById(String id);

    /**
     * 通过ID删除课程小节
     * @param id 小节ID
     * @return 通用返回对象
     */
    R deleteVideoById(String id);
}
