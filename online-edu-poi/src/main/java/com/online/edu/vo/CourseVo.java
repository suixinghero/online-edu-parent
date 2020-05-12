package com.online.edu.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author xujin
 * @since 2020-04-30
 */
@Data
@ApiModel(value="CourseVo对象", description="课程列表信息返回对象")
public class CourseVo implements Serializable {

    private static final long serialVersionUID = 1339028690858997797L;
    @ApiModelProperty(value = "课程ID")
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

    @ApiModelProperty(value = "视频状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
    public CourseVo(){}
    private CourseVo(Builder builder){
        this.setId(builder.id);
        this.setTeacherId(builder.teacherId);
        this.setSubjectId(builder.subjectId);
        this.setTitle(builder.title);
        this.setPrice(builder.price);
        this.setLessonNum(builder.lessonNum);
        this.setCover(builder.cover);
        this.setBuyCount(builder.buyCount);
        this.setViewCount(builder.viewCount);
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
        public CourseVo build() {
            return new CourseVo(this);
        }
    }
}
