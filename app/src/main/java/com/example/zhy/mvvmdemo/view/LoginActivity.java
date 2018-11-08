package com.example.zhy.mvvmdemo.view;

import android.os.Bundle;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.base.BaseActivity;
import com.example.zhy.mvvmdemo.databinding.LoginDataBinding;
import com.example.zhy.mvvmdemo.viewmodel.LoginVM;

public class LoginActivity extends BaseActivity<LoginDataBinding> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        LoginVM loginViewModel = new LoginVM();
        loginViewModel.setData(this);
        getDataBind().setViewModel(loginViewModel);
    }
}
