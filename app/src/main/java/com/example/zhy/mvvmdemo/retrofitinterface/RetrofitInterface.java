package com.example.zhy.mvvmdemo.retrofitinterface;

import com.example.zhy.mvvmdemo.bean.NewsBean;
import com.example.zhy.mvvmdemo.bean.UserBean;
import com.example.zhy.mvvmdemo.http.URLConstant;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    //获取“分类中搜索商品”的数据
    @GET(URLConstant.URL_PATH)
    Observable<NewsBean> getNewsData();

    //获取“分类中搜索商品”的数据
    @POST(URLConstant.URL_LOGIN)
    Observable<UserBean> login(@Body Map<String, String> map);
}
