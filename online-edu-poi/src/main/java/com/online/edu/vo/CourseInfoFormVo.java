package com.online.edu.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author xujin
 * @createtime 2020-04-30 18:00
 * @description
 */
@Data
@ApiModel(value = "课程基本信息", description = "课程基本信息的表单回显对象")
public class CourseInfoFormVo implements Serializable {

    private static final long serialVersionUID = 3388959726578954519L;
    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;
    public CourseInfoFormVo(){}
    private CourseInfoFormVo(Builder builder){
        this.setId(builder.id);
        this.setTeacherId(builder.teacherId);
        this.setSubjectParentId(builder.subjectParentId);
        this.setSubjectId(builder.subjectId);
        this.setTitle(builder.title);
        this.setPrice(builder.price);
        this.setLessonNum(builder.lessonNum);
        this.setCover(builder.cover);
        this.setDescription(builder.description);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String teacherId;
        private String subjectParentId;
        private String subjectId;
        private String title;
        private BigDecimal price;
        private Integer lessonNum;
        private String cover;
        private String description;
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder teacherId(String teacherId){
            this.teacherId = teacherId;
            return this;
        }
        public Builder subjectParentId(String subjectParentId){
            this.subjectParentId = subjectParentId;
            return this;
        }
        public Builder subjectId(String subjectId){
            this.subjectId = subjectId;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder price(BigDecimal price){
            this.price = price;
            return this;
        }
        public Builder lessonNum(Integer lessonNum){
            this.lessonNum = lessonNum;
            return this;
        }
        public Builder cover(String cover){
            this.cover = cover;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public CourseInfoFormVo build() {
            return new CourseInfoFormVo(this);
        }
    }
}