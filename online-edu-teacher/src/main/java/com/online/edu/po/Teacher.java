package com.online.edu.po;

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
 * 讲师
 * </p>
 *
 * @author xujin
 * @since 2020-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_teacher")
@ApiModel(value="Teacher对象", description="讲师")
public class Teacher implements Serializable {

    private static final long serialVersionUID = -5574745700660617349L;
    @ApiModelProperty(value = "讲师ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String intro;

    @ApiModelProperty(value = "讲师简介")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    public Teacher(){}
    private Teacher(Builder builder){
        setId(builder.id);
        setName(builder.name);
        setIntro(builder.intro);
        setCareer(builder.career);
        setLevel(builder.level);
        setAvatar(builder.avatar);
        setSort(builder.sort);
        setIsDeleted(builder.isDeleted);
        setGmtCreate(builder.gmtCreate);
        setGmtModified(builder.gmtModified);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String name;
        private String intro;
        private String career;
        private Integer level;
        private String avatar;
        private Integer sort;
        private Boolean isDeleted;
        private Date gmtCreate;
        private Date gmtModified;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder intro(String intro){
            this.intro = intro;
            return this;
        }
        public Builder career(String career){
            this.career = career;
            return this;
        }
        public Builder level(Integer level){
            this.level = level;
            return this;
        }
        public Builder avatar(String avatar){
            this.avatar = avatar;
            return this;
        }
        public Builder sort(Integer sort){
            this.sort = sort;
            return this;
        }
        public Builder isDeleted(Boolean isDeleted){
            this.isDeleted = isDeleted;
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

        public Teacher build() {
            return new Teacher(this);
        }
    }
}
