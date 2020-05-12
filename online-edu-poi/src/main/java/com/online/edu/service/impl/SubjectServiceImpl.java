package com.online.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.online.edu.common.R;
import com.online.edu.mapper.SubjectMapper;
import com.online.edu.po.Subject;
import com.online.edu.req.SubjectAddReq;
import com.online.edu.service.SubjectService;
import com.online.edu.vo.FirstSubjectVo;
import com.online.edu.vo.SecondSubjectVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xujin
 * @since 2020-04-29
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Transactional
    @Override
    public R importExcelSubject(MultipartFile file) throws IOException {
        //1.获取文件的输入流
        InputStream inputStream = file.getInputStream();
        //2.创建workbook
        Workbook workbook = new XSSFWorkbook(inputStream);
        //3.通过workbook得到sheet
        Sheet sheet = workbook.getSheetAt(0);
        //4.sheet获取root
        //获取一共有多少行
        int rows = sheet.getPhysicalNumberOfRows();
        Set<Subject> firstLevelClassification = new HashSet<>();
        Set<Subject> secondLevelClassification = new HashSet<>();
        for (int i = 1; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                String parentTitle = null;
                for (int j = 0; j < cells; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null && j == 0) {
                        Subject subject = new Subject();
                        subject.setTitle(cell.getStringCellValue());
                        parentTitle = cell.getStringCellValue();
                        firstLevelClassification.add(subject);
                    }
                    if (cell != null && j == 1) {
                        Subject subject = new Subject();
                        subject.setTitle(cell.getStringCellValue());
                        subject.setParentTitle(parentTitle);
                        secondLevelClassification.add(subject);
                    }
                }
            }
        }
        //批量添加一级分类
        boolean firstIsSuccess = saveBatch(firstLevelClassification, firstLevelClassification.size());
        Map<String, List<Subject>> groupByParentTitle = secondLevelClassification.stream().collect(Collectors.groupingBy(Subject::getParentTitle));
        firstLevelClassification.forEach(s->{
            groupByParentTitle.get(s.getTitle()).forEach(t->{
                t.setParentId(s.getId());
            });
        });
        //批量添加二级分类
        boolean secondIsSuccess = saveBatch(secondLevelClassification, secondLevelClassification.size());
        Assert.isTrue(firstIsSuccess && secondIsSuccess, "过上传excel文件获取课程分类信息失败：");
        return R.ok();
    }

    @Override
    public R querySubjectList() {
        //获取一级分类数据记录
        QueryWrapper<Subject> firstQueryWrapper = new QueryWrapper<>();
        firstQueryWrapper.eq("parent_id", 0);
        firstQueryWrapper.orderByAsc("sort","id");
        List<Subject> firstSubjects = baseMapper.selectList(firstQueryWrapper);
        //获取二级分类数据记录
        QueryWrapper<Subject> secondQueryWrapper = new QueryWrapper<>();
        secondQueryWrapper.ne("parent_id", 0);
        secondQueryWrapper.orderByAsc("sort","id");
        List<Subject> secondSubjects = baseMapper.selectList(secondQueryWrapper);
        //填充一级分类vo数据
        List<FirstSubjectVo> firstSubjectVos = new ArrayList<>();
        Optional.ofNullable(firstSubjects).orElse(Collections.emptyList()).forEach(f->{
            firstSubjectVos.add(FirstSubjectVo.newBuilder().id(f.getId()).title(f.getTitle()).build());
        });
        //把二级分类按照一级分类的ID分组
        Map<String, List<Subject>> groupByParentId = Optional.of(secondSubjects).orElse(Collections.emptyList()).stream().collect(Collectors.groupingBy(Subject::getParentId));
        firstSubjectVos.forEach(f->{
            List<SecondSubjectVo> secondSubjectVos = new ArrayList<>();
            //填充二级分类数据
            Optional.ofNullable(groupByParentId.get(f.getId())).orElse(Collections.emptyList()).forEach(s->{
                secondSubjectVos.add(SecondSubjectVo.newBuilder().id(s.getId()).title(s.getTitle()).build());
            });
            f.setChildren(secondSubjectVos);
        });
        return R.ok().data("items",firstSubjectVos);
    }

    @Override
    public R deleteSubjectById(String id) {
        //判断要删除的课程分类下是否有子分类
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        Assert.isTrue(count == 0,"不能删除拥有子分类的课程分类");
        int isDelete = baseMapper.deleteById(id);
        Assert.isTrue(isDelete > 0,"删除失败");
        return R.ok();
    }

    @Override
    public R addCategory(SubjectAddReq req) {
        req = Optional.ofNullable(req).orElse(new SubjectAddReq());
        Subject subject = Subject.newBuilder().parentId(req.getParentId()).title(req.getTitle()).build();
        //判断要添加的课程分类是否已存在
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(subject.getParentId())) {
            queryWrapper.eq("parent_id",subject.getParentId());
        }
        if (!StringUtils.isEmpty(subject.getTitle())) {
            queryWrapper.eq("title",subject.getTitle());
        }
        Integer count = baseMapper.selectCount(queryWrapper);
        Assert.isTrue(count == 0,"要添加的课程分类已存在");
        int isSuccess = baseMapper.insert(subject);
        Assert.isTrue(isSuccess > 0,"添加失败");
        return R.ok();
    }
}
