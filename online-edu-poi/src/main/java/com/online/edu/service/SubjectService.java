package com.online.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.online.edu.common.R;
import com.online.edu.po.Subject;
import com.online.edu.req.SubjectAddReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xujin
 * @since 2020-04-29
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 过上传excel文件获取课程分类信息
     * @param file 上传文件项
     * @return 通用返回对象
     */
    R importExcelSubject(MultipartFile file) throws IOException;

    /**
     * 查询课程分类列表信息
     * @return 通用返回对象
     */
    R querySubjectList();

    /**
     * 通过ID删除课程分类信息
     * @param id 课程分类ID
     * @return 通用返回对象
     */
    R deleteSubjectById(String id);

    /**
     * 添加课程分类信息
     * @param req 课程科目添加请求对象
     * @return 通用返回对象
     */
    R addCategory(SubjectAddReq req);
}
