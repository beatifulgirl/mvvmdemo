package com.example.zhy.mvvmdemo.http.service;

import com.example.zhy.mvvmdemo.bean.BaseMap;
import com.example.zhy.mvvmdemo.bean.UserBean;
import com.example.zhy.mvvmdemo.http.HttpUtils;

import io.reactivex.Observable;

/**
 * 用户业务
 */
public class UserService {

    //登录
    public static Observable<UserBean> login(String phone, String password, String postType) {
        BaseMap root = new BaseMap();
        root.put("phone", phone);
        root.put("password", password);
        root.put("postType", postType);
        return HttpUtils.getRetrofit().login(root);
    }
}
