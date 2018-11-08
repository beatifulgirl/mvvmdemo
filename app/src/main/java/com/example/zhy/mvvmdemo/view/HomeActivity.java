package com.example.zhy.mvvmdemo.view;

import android.os.Bundle;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.base.BaseActivity;
import com.example.zhy.mvvmdemo.base.BaseFragment;
import com.example.zhy.mvvmdemo.databinding.ActivityHomeBinding;
import com.example.zhy.mvvmdemo.view.fragment.HomeFragment;
import com.example.zhy.mvvmdemo.view.fragment.MoreFragment;
import com.example.zhy.mvvmdemo.view.fragment.MyFragment;
import com.example.zhy.mvvmdemo.view.fragment.OrderFragment;
import com.example.zhy.mvvmdemo.viewmodel.HomeVM;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity<ActivityHomeBinding> {

    private HomeVM homeVM;

    private List<BaseFragment> fragmentList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        homeVM = new HomeVM();
        homeVM.setData(this);

        //构造Fragment的集合
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new OrderFragment());
        fragmentList.add(new MyFragment());
        fragmentList.add(new MoreFragment());

        //初始化CustomBottomTabWidget
        getDataBind().tabWidget.init(getSupportFragmentManager(),fragmentList);
    }
}
