package com.online.edu.vo;

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
 * 课程章节
 * </p>
 *
 * @author xujin
 * @since 2020-05-02
 */
@Data
@ApiModel(value="ChapterVo对象", description="页面查询章节返回对象")
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 5315475880389452819L;
    @ApiModelProperty(value = "章节ID")
    private String id;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    public ChapterVo(){}
    public ChapterVo(Builder builder){
        this.setId(builder.id);
        this.setCourseId(builder.courseId);
        this.setTitle(builder.title);
        this.setSort(builder.sort);
    }
    public static Builder newBuilder(){
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String courseId;
        private String title;
        private Integer sort;
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
        public ChapterVo build() {
            return new ChapterVo(this);
        }
    }
}
