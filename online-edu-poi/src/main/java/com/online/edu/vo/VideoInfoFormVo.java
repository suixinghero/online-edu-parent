package com.online.edu.vo;

/**
 * @author xujin
 * @createtime 2020-05-03 14:56
 * @description
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value = "VideoInfoFormVo", description = "页面课程小节信息回显对象")
@Data
public class VideoInfoFormVo implements Serializable {

    private static final long serialVersionUID = 1192935523239660797L;
    @ApiModelProperty(value = "小节ID")
    private String id;

    @ApiModelProperty(value = "小节名称")
    private String title;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节ID")
    private String chapterId;

    @ApiModelProperty(value = "视频资源")
    private String videoSourceId;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;

    @ApiModelProperty(value = "是否可以试听：0默认 1免费")
    private Boolean free;

    @ApiModelProperty(value = "云服务器上存储的视频文件名称")
    private String videoOriginalName;
    public VideoInfoFormVo(){}
    public VideoInfoFormVo(Builder builder) {
      this.setId(builder.id);
      this.setTitle(builder.title);
      this.setCourseId(builder.courseId);
      this.setChapterId(builder.chapterId);
      this.setVideoSourceId(builder.videoSourceId);
      this.setSort(builder.sort);
      this.setFree(builder.free);
      this.setVideoOriginalName(builder.videoOriginalName);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String title;
        private String courseId;
        private String chapterId;
        private String videoSourceId;
        private Integer sort;
        private Boolean free;
        private String videoOriginalName;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder courseId(String courseId){
            this.courseId = courseId;
            return this;
        }
        public Builder chapterId(String chapterId){
            this.chapterId = chapterId;
            return this;
        }
        public Builder videoSourceId(String videoSourceId){
            this.videoSourceId = videoSourceId;
            return this;
        }
        public Builder sort(Integer sort){
            this.sort = sort;
            return this;
        }
        public Builder free(Boolean free){
            this.free = free;
            return this;
        }
        public Builder videoOriginalName(String videoOriginalName){
            this.videoOriginalName = videoOriginalName;
            return this;
        }
        public VideoInfoFormVo build() {
            return new VideoInfoFormVo(this);
        }
    }
}