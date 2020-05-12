package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.req.ChapterAddReq;
import com.online.edu.req.ChapterUpdateReq;
import com.online.edu.service.ChapterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程章节 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
@RestController
@RequestMapping("/edu/chapter")
@CrossOrigin
public class ChapterController {
    private Logger logger = LoggerFactory.getLogger(ChapterController.class);
    @Autowired
    private ChapterService chapterService;
    //通过课程ID查询课程章节和小节信息
    @GetMapping("/queryChapterByCourseId/{id}")
    public R queryChapterByCourseId(@PathVariable("id")String id){
        try {
            logger.info("查询课程章节和小节信息信息 开始。id:{} ", id);
            //查询结果
            R result = chapterService.queryChapterByCourseId(id);
            logger.info("查询课程章节和小节信息信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("查询课程章节和小节信息信息 失败。id:{} 异常信息{}", id,  e.getMessage());
            return R.error();
        }
    }

    //添加课程章节
    @PostMapping("/addChapter")
    public R addChapter(@RequestBody ChapterAddReq req){
        try {
            logger.info("添加课程章节 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = chapterService.addChapter(req);
            logger.info("添加课程章节 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("添加课程章节 失败。req:{} 异常信息{}", JSON.toJSONString(req),  e.getMessage());
            return R.error();
        }
    }

    //通过章节ID查询章节信息
    @GetMapping("/queryChapterById/{id}")
    public R queryChapterById(@PathVariable("id")String id){
        try {
            logger.info("查询章节信息 开始。id:{} ", id);
            //查询结果
            R result = chapterService.queryChapterById(id);
            logger.info("查询章节信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("查询章节信息 失败。id:{} 异常信息{}", id,  e.getMessage());
            return R.error();
        }

    }

    //通过章节ID修改章节信息
    @PutMapping("/updateChapter")
    public R updateChapter(@RequestBody ChapterUpdateReq req){
        try {
            logger.info("修改章节信息 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = chapterService.updateChapter(req);
            logger.info("修改章节信息 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("修改章节信息 失败。req:{} 异常信息{}", JSON.toJSONString(req),  e.getMessage());
            return R.error();
        }
    }

    //通过ID删除章节信息
    @DeleteMapping("/deleteChapter/{id}")
    public R deleteChapter(@PathVariable("id")String id){
        try {
            logger.info("删除章节信息 开始。id:{} ", id);
            //查询结果
            R result = chapterService.deleteChapter(id);
            logger.info("删除章节信息 完成。id:{}  查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("删除章节信息 失败。id:{} 异常信息{}",id, e.getMessage());
            return R.error();
        }
    }
}

