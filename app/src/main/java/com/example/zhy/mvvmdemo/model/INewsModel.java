package com.example.zhy.mvvmdemo.model;

import com.example.zhy.mvvmdemo.base.BaseListListener;
import com.example.zhy.mvvmdemo.bean.SimpleNewsBean;

public interface  INewsModel {

    /**
     * 获取数据接口
     * @param page
     * @param loadListener
     */
    void loadNewsData(int page, BaseListListener<SimpleNewsBean> loadListener);

}
