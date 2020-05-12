package com.online.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


/**
 * @author xujin
 * @createtime 2020-05-03 17:19
 * @description
 */
@Data
@ApiModel(value = "CoursePublishVo", description = "课程发布页面显示对象")
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 978497730488721487L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
    public CoursePublishVo(){}
    private CoursePublishVo(Builder builder){
        this.setTitle(builder.title);
        this.setLessonNum(builder.lessonNum);
        this.setCover(builder.cover);
        this.setSubjectLevelOne(builder.subjectLevelOne);
        this.setSubjectLevelTwo(builder.subjectLevelTwo);
        this.setTeacherName(builder.teacherName);
        this.setPrice(builder.price);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String title;
        private String cover;
        private Integer lessonNum;
        private String subjectLevelOne;
        private String subjectLevelTwo;
        private String teacherName;
        private String price;//只用于显示
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder cover(String cover){
            this.cover = cover;
            return this;
        }
        public Builder lessonNum(Integer lessonNum){
            this.lessonNum = lessonNum;
            return this;
        }
        public Builder subjectLevelOne(String subjectLevelOne){
            this.subjectLevelOne = subjectLevelOne;
            return this;
        }
        public Builder subjectLevelTwo(String subjectLevelTwo){
            this.subjectLevelTwo = subjectLevelTwo;
            return this;
        }
        public Builder teacherName(String teacherName){
            this.teacherName = teacherName;
            return this;
        }
        public Builder price(String price){
            this.price = price;
            return this;
        }
        public CoursePublishVo build() {
            return new CoursePublishVo(this);
        }
    }
}
