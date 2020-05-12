package com.online.edu.po;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author xujin
 * @since 2020-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_subject")
@ApiModel(value="Subject对象", description="课程科目")
public class Subject implements Serializable {

    private static final long serialVersionUID = 8829649563626621963L;
    @ApiModelProperty(value = "课程类别ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "父类别名称")
    @TableField(exist = false)
    private String parentTitle;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public Subject(){}
    private Subject(Builder builder){
       this.setId(builder.id);
       this.setTitle(builder.title);
       this.setParentId(builder.parentId);
       this.setSort(builder.sort);
       this.setParentTitle(builder.parentTitle);
       this.setGmtCreate(builder.gmtCreate);
       this.setGmtModified(builder.gmtModified);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String title;
        private String parentId;
        private Integer sort;
        private String parentTitle;
        private Date gmtCreate;
        private Date gmtModified;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder parentId(String parentId){
            this.parentId = parentId;
            return this;
        }
        public Builder sort(Integer sort){
            this.sort = sort;
            return this;
        }
        public Builder parentTitle(String parentTitle){
            this.parentTitle = parentTitle;
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
        public Subject build() {
            return new Subject(this);
        }
    }
}
