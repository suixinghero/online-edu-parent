package com.online.edu.service;

import com.online.edu.common.R;
import com.online.edu.req.SubjectAddReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xujin
 * @createtime 2020-05-06 15:36
 * @description
 */
@FeignClient(value = "online-edu-poi")
public interface SubjectClientService {
    //通过上传excel文件获取文件的内容
    @PostMapping("/edu/subject/import")
    R importExcelSubject(@RequestParam("file") MultipartFile file);
    //查询课程分类列表信息
    @GetMapping("/edu/subject/querySubjectList")
    R querySubjectList();

    //通过ID删除课程分类信息
    @DeleteMapping("/edu/subject/{id}")
    R deleteSubjectById(@PathVariable("id")String id);

    //添加课程分类信息
    @PostMapping("/edu/subject/addCategory")
    R addCategory(@RequestBody SubjectAddReq req);
}
