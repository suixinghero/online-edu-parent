package com.online.edu.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xujin
 * @createtime 2020-04-29 17:52
 * @description
 */

public class FirstSubjectVo {
    private String id;
    private String title;
    private List<SecondSubjectVo> children = new ArrayList<>();

    public FirstSubjectVo(){}
    private FirstSubjectVo(Builder builder){
        this.setId(builder.id);
        this.setTitle(builder.title);
    }
    public static Builder newBuilder() {
        return new Builder();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SecondSubjectVo> getChildren() {
        return children;
    }

    public void setChildren(List<SecondSubjectVo> children) {
        this.children = children;
    }
    public static final class Builder{
        private String id;
        private String title;
        public Builder id(String id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public FirstSubjectVo build() {
            return new FirstSubjectVo(this);
        }
    }
}
