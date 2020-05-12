package com.online.edu.po;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程章节
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_chapter")
@ApiModel(value="Chapter对象", description="课程章节")
public class Chapter implements Serializable {


    private static final long serialVersionUID = -8065448174785186842L;
    @ApiModelProperty(value = "章节ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public Chapter(){}
    public Chapter(Builder builder){
        this.setId(builder.id);
        this.setCourseId(builder.courseId);
        this.setTitle(builder.title);
        this.setSort(builder.sort);
        this.setGmtCreate(builder.gmtCreate);
        this.setGmtModified(builder.gmtModified);
    }
    public static Builder newBuilder(){
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String courseId;
        private String title;
        private Integer sort;
        private Date gmtCreate;
        private Date gmtModified;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder courseId(String courseId){
            this.courseId = courseId;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder sort(Integer sort){
            this.sort = sort;
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
        public Chapter build() {
            return new Chapter(this);
        }
    }
}
