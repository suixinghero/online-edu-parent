package com.online.edu.common;

import com.online.edu.enums.ResultEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xujin
 * @createtime 2020-04-15 12:13
 * @description
 */

public class R {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    private R(){}

    //操作成功，调用这个方法，返回成功的数据
    public static R ok(){
        return R.build(true,ResultEnum.COMMON.SUCCESS.getCode()
        ,ResultEnum.COMMON.SUCCESS.getInfo());
    }

    //操作失败，调用这个方法，返回失败的数据
    public static R error(){
        return R.build(false,ResultEnum.COMMON.ERROR.getCode()
                ,ResultEnum.COMMON.ERROR.getInfo());
    }

    public static R error(String message){
        return R.build(false,ResultEnum.COMMON.ERROR.getCode()
                ,message);
    }
    public static R build(Boolean success,Integer code,String message){
        R r = new R();
        r.setSuccess(success);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }
    //使用链式编程
    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}

