package com.example.zhy.mvvmdemo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity{

    private T dataBing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBing = DataBindingUtil.setContentView(this,getLayoutId());
        init(savedInstanceState);
    }

    public abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);

    public T getDataBind(){
        return dataBing;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataBing = null;
    }
}
