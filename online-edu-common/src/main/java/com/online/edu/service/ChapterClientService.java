package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.req.ChapterAddReq;
import com.online.edu.req.ChapterUpdateReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author xujin
 * @createtime 2020-05-06 15:30
 * @description
 */
@FeignClient(value = "online-edu-poi")
public interface ChapterClientService {
    //通过课程ID查询课程章节和小节信息
    @GetMapping("/edu/chapter/queryChapterByCourseId/{id}")
    R queryChapterByCourseId(@PathVariable("id")String id);

    //添加课程章节
    @PostMapping("/edu/chapter/addChapter")
    R addChapter(@RequestBody ChapterAddReq req);

    //通过章节ID查询章节信息
    @GetMapping("/edu/chapter/queryChapterById/{id}")
    R queryChapterById(@PathVariable("id")String id);

    //通过章节ID修改章节信息
    @PutMapping("/edu/chapter/updateChapter")
    R updateChapter(@RequestBody ChapterUpdateReq req);

    //通过ID删除章节信息
    @DeleteMapping("/edu/chapter/deleteChapter/{id}")
    R deleteChapter(@PathVariable("id")String id);
}
