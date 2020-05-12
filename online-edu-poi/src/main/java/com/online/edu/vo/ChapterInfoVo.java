package com.online.edu.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xujin
 * @createtime 2020-05-02 17:11
 * @description
 */
@ApiModel(value = "ChapterInfoVo对象",description = "页面章节小节返回对象")
@Data
public class ChapterInfoVo implements Serializable {
    private static final long serialVersionUID = 1287561498517538048L;
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
    public ChapterInfoVo(){}
    public ChapterInfoVo(Builder builder){
        this.setId(builder.id);
        this.setTitle(builder.title);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public static final class Builder{
        private String id;
        private String title;
        private Builder(){}
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public ChapterInfoVo build() {
            return new ChapterInfoVo(this);
        }
    }
}
