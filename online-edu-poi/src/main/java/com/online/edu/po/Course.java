package com.online.edu.po;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author xujin
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_course")
@ApiModel(value="Course对象", description="课程")
public class Course implements Serializable {


    private static final long serialVersionUID = 2019511161179022135L;
    @ApiModelProperty(value = "课程ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

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

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Long version;

    @ApiModelProperty(value = "视频状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public Course(){}
    private Course(Builder builder){
        this.setId(builder.id);
        this.setTeacherId(builder.teacherId);
        this.setSubjectId(builder.subjectId);
        this.setTitle(builder.title);
        this.setPrice(builder.price);
        this.setLessonNum(builder.lessonNum);
        this.setCover(builder.cover);
        this.setBuyCount(builder.buyCount);
        this.setViewCount(builder.viewCount);
        this.setVersion(builder.version);
        this.setStatus(builder.status);
        this.setGmtCreate(builder.gmtCreate);
        this.setGmtModified(builder.gmtModified);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String teacherId;
        private String subjectId;
        private String title;
        private BigDecimal price;
        private Integer lessonNum;
        private String cover;
        private Long buyCount;
        private Long viewCount;
        private Long version;
        private String status;
        private Date gmtCreate;
        private Date gmtModified;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder teacherId(String teacherId){
            this.teacherId = teacherId;
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
        public Builder viewCount(Long viewCount){
            this.viewCount = viewCount;
            return this;
        }
        public Builder buyCount(Long buyCount){
            this.buyCount = buyCount;
            return this;
        }
        public Builder version(Long version){
            this.version = version;
            return this;
        }
        public Builder status(String status){
            this.status = status;
            return this;
        }
        public Builder gmtCreate(Date gmtCreate){
            this.gmtCreate = gmtCreate;
            return this;
        }
        public Builder gmtModified(Date gmtModified){
            this.gmtModified = gmtModified;
            return this;
        }
        public Course build() {
            return new Course(this);
        }
    }
}
