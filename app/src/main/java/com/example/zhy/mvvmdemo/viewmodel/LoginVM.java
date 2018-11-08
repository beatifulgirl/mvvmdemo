package com.example.zhy.mvvmdemo.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;

import com.example.zhy.mvvmdemo.base.BaseModel;
import com.example.zhy.mvvmdemo.base.BaseObjListener;
import com.example.zhy.mvvmdemo.bean.UserBean;
import com.example.zhy.mvvmdemo.model.impl.LoginModelImpl;
import com.example.zhy.mvvmdemo.utils.MyToast;
import com.example.zhy.mvvmdemo.utils.TokenPersist;
import com.example.zhy.mvvmdemo.utils.UserPersist;
import com.example.zhy.mvvmdemo.view.HomeActivity;

public class LoginVM extends BaseModel implements BaseObjListener<UserBean> {

    public final ObservableField<String> name = new ObservableField<>("18231077226");
    public final ObservableField<String> pwd = new ObservableField<>("lishuai123");

    private LoginModelImpl viewModel;

    @Override
    public void setData(Activity activity) {
        super.setData(activity);
        viewModel = new LoginModelImpl();
    }

    public void onLoginClick(){
        viewModel.login(name.get(), pwd.get(),this);
    }

    public void onRegisthClick(){
        MyToast.toast("註冊");
    }

    @Override
    public void loadSuccess(UserBean data) {
        if(data.resultCode.equals("0")){
            MyToast.toast("登录成功");
            UserPersist.storeUserID(data.data.memberId);//保存用户id\
            TokenPersist.storeToken(data.data.token);
            startActivityFinish(HomeActivity.class);
        }else{
            MyToast.toast(data.resultMsg);
        }
    }

    @Override
    public void loadFaile(String result) {

    }

    @Override
    public void loadStart() {

    }

    @Override
    public void loadComplete() {

    }
}
