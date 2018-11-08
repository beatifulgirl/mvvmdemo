package com.example.zhy.mvvmdemo.model.impl;

import com.example.zhy.mvvmdemo.base.BaseObjListener;
import com.example.zhy.mvvmdemo.bean.UserBean;
import com.example.zhy.mvvmdemo.http.service.UserService;
import com.example.zhy.mvvmdemo.model.ILoginModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginModelImpl implements ILoginModel {


    @Override
    public void login(String phone, String pwd, final BaseObjListener<UserBean> listener) {
        UserService.login(phone,pwd,"2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<UserBean>() {
                    @Override
                    public void onNext(UserBean userBean) {
                        listener.loadSuccess(userBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.loadFaile(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        listener.loadComplete();
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        listener.loadStart();
                    }

                });
    }
}
