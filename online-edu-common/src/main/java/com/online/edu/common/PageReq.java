package com.online.edu.common;


import java.io.Serializable;

/**
 * @author xujin
 * @package-name com.online.edu.common
 * @createtime 2020-04-04 17:55
 * @description: 分页请求封装对象
 */

public class PageReq<T> implements Serializable {
    private static final long serialVersionUID = -1817909645968389516L;
    private int page;
    private int limit;
    private T data;


    public PageReq() {
    }

    public PageReq(T data) {
        this.data = data;
    }

    public static <T> PageReq<T> buildPageReqNoData(int page, int limit) {
        return buildPageReqWithData(page, limit, null);
    }

    public static <T> PageReq<T> buildPageReqWithData(int page, int limit, T data) {
        PageReq<T> req = new PageReq<>(data);
        req.setLimit(limit);
        req.setPage(page);
        return req;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
