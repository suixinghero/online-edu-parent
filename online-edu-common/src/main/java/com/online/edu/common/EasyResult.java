package com.online.edu.common;

import com.online.edu.enums.ResultEnum;

/**
 * @author xujin
 * @package-name com.online.edu.common
 * @createtime 2020-04-04 11:07
 * @description: 通用返回对象
 */

public class EasyResult<T> {
    private Boolean isSuccess;
    private Integer code;
    private String message;
    private Integer count;
    private T data;

    public EasyResult() {
    }

    public EasyResult(T data) {
        this.data = data;
    }

    public static <T> EasyResult<T> buildEasyResultSuccess(T data,Integer count) {
        return  EasyResult.buildResult(ResultEnum.COMMON.SUCCESS.getCode(),
                ResultEnum.COMMON.SUCCESS.getInfo(),
                data,count,true);
    }
    public static <T> EasyResult<T> buildEasyResultSuccess(T data) {
        return  EasyResult.buildResult(ResultEnum.COMMON.SUCCESS.getCode(),
                ResultEnum.COMMON.SUCCESS.getInfo(),
                data,null,true);
    }
    public static <T> EasyResult<T> buildEasyResultSuccess() {
        return  EasyResult.buildResult(ResultEnum.COMMON.SUCCESS.getCode(),
                ResultEnum.COMMON.SUCCESS.getInfo(),
                null,null,true);
    }
    public static <T> EasyResult<T> buildEasyResultError() {
        return  EasyResult.buildResult(ResultEnum.COMMON.ERROR.getCode(),
                ResultEnum.COMMON.ERROR.getInfo(),
                null,null,false);
    }

    public static <T> EasyResult<T> buildResult(Integer code, String message, T data, Integer count, Boolean isSuccess) {
        EasyResult<T> result = new EasyResult<T>(data);
        result.setCode(code);
        result.setMessage(message);
        result.setIsSuccess(isSuccess);
        result.setCount(count);
        return result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean success) {
        isSuccess = success;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}