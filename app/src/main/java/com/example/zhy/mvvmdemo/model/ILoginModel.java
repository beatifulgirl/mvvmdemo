package com.example.zhy.mvvmdemo.model;

import com.example.zhy.mvvmdemo.base.BaseObjListener;
import com.example.zhy.mvvmdemo.bean.UserBean;

public interface ILoginModel {

    void login(String phone, String pwd, BaseObjListener<UserBean> listener);
}
