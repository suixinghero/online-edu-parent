package com.online.edu.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujin
 * @createtime 2020-05-02 17:12
 * @description
 */
@ApiModel(value = "小节节信息")
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 3550981058481183848L;
    private String id;
    private String title;
    private String videoSourceId;
    private Boolean free;
    public VideoVo(){}
    public VideoVo(Builder builder) {
        this.setId(builder.id);
        this.setTitle(builder.title);
        this.setVideoSourceId(builder.videoSourceId);
        this.setFree(builder.free);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String title;
        private String videoSourceId;
        private Boolean free;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder videoSourceId(String videoSourceId){
            this.videoSourceId = videoSourceId;
            return this;
        }
        public Builder free(Boolean free){
            this.free = free;
            return this;
        }
        public VideoVo build() {
            return new VideoVo(this);
        }
    }
}
