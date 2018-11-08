package com.example.zhy.mvvmdemo.base;

public interface BaseObjListener<T> {

    void loadSuccess(T data);


    void loadFaile(String result);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();

}
