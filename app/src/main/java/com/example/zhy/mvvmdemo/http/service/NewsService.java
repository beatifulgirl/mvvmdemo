package com.example.zhy.mvvmdemo.http.service;

import com.example.zhy.mvvmdemo.bean.NewsBean;
import com.example.zhy.mvvmdemo.http.HttpUtils;

import io.reactivex.Observable;

public class NewsService {

    //获取新闻数据
    public static Observable<NewsBean> getNewsData() {
        return HttpUtils.getRetrofit().getNewsData();
    }
}
