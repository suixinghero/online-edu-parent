package com.online.edu.controller;


import com.alibaba.fastjson.JSON;
import com.online.edu.common.R;
import com.online.edu.req.SubjectAddReq;
import com.online.edu.service.SubjectService;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xujin
 * @since 2020-04-29
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class SubjectController {
    private Logger logger = LoggerFactory.getLogger(SubjectController.class);
    @Autowired
    private SubjectService subjectService;
    //通过上传excel文件获取文件的内容
    @PostMapping("/import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file){
        try {
            logger.info("上传excel文件 开始。file{}",JSON.toJSONString(file));
            R result = subjectService.importExcelSubject(file);
            logger.info("上传excel文件 完成。file{},result{}", JSON.toJSONString(file),JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("上传excel文件 失败。file{},异常信息{}",JSON.toJSONString(file), e.getMessage());
            return R.error();
        }
    }

    //查询课程分类列表信息
    @GetMapping("/querySubjectList")
    public R querySubjectList(){
        try {
            logger.info("查询课程分类列表信息 开始。");
            R result = subjectService.querySubjectList();
            logger.info("查询课程分类列表信息 完成。result{}", JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("查询课程分类列表信息 失败。异常信息{}",e.getMessage());
            return R.error();
        }
    }

    //通过ID删除课程分类信息
    @DeleteMapping("/{id}")
    public R deleteSubjectById(@PathVariable("id")String id){
        try {
            logger.info("通过ID删除课程分类信息 开始。id:{} ", id);
            //查询结果
            R result = subjectService.deleteSubjectById(id);
            logger.info("通过ID删除课程分类信息 完成。id:{}查询结果:result: {}", id, JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("通过ID删除课程分类信息 失败。id:{} 异常信息{}",id, e.getMessage());
            return R.error();
        }
    }

    //添加课程分类信息
    @PostMapping("/addCategory")
    public R addCategory(@RequestBody SubjectAddReq req){
        try {
            logger.info("添加课程分类信息 开始。req:{} ", JSON.toJSONString(req));
            //查询结果
            R result = subjectService.addCategory(req);
            logger.info("添加课程分类信息 完成。req:{}  查询结果:result: {}", JSON.toJSONString(req), JSON.toJSONString(result));
            return result;
        } catch (Exception e) {
            logger.error("添加课程分类信息 失败。req:{} 异常信息{}", JSON.toJSONString(req), e.getMessage());
            return R.error();
        }
    }
}

