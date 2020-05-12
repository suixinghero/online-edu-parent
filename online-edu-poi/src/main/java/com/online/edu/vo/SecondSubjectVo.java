package com.online.edu.vo;

/**
 * @author xujin
 * @createtime 2020-04-29 17:52
 * @description
 */

public class SecondSubjectVo {
    private String id;
    private String title;
    public SecondSubjectVo(){}
    public SecondSubjectVo(Builder builder){
        this.setId(builder.id);
        this.setTitle(builder.title);
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

    public static Builder newBuilder() {
        return new Builder();
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
        public SecondSubjectVo build() {
            return new SecondSubjectVo(this);
        }
    }
}
