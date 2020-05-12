package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.po.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.req.ChapterAddReq;
import com.online.edu.req.ChapterUpdateReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 课程小节 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
public interface ChapterService extends IService<Chapter> {
    /**
     * 通过课程ID查询课程章节和小节信息
     * @param id 课程ID
     * @return 通用返回对象
     */
    R queryChapterByCourseId(String id);

    /**
     * 添加课程章节
     * @param req 课程章节添加对象
     * @return 通用返回对象
     */
    R addChapter(ChapterAddReq req);

    /**
     * 通过章节ID查询章节信息
     * @param id 章节ID
     * @return 通用返回对象
     */
    R queryChapterById(String id);

    /**
     * 通过章节ID修改章节信息
     * @param req 章节修改请求
     * @return 通用返回对象
     */
    R updateChapter(ChapterUpdateReq req);

    /**
     * 通过ID删除章节信息
     * @param id 章节ID
     * @return 通用返回对象
     */
    R deleteChapter(String id);
}
