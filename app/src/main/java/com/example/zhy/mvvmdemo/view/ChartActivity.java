package com.example.zhy.mvvmdemo.view;

import android.content.Intent;
import android.os.Bundle;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.base.BaseActivity;
import com.example.zhy.mvvmdemo.databinding.ActivityChartBinding;
import com.example.zhy.mvvmdemo.service.TCPServiceService;
import com.example.zhy.mvvmdemo.viewmodel.ChartVM;

public class ChartActivity extends BaseActivity<ActivityChartBinding> {

    private ChartVM vm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        vm = new ChartVM();
        vm.setData(this,getDataBind());
    }


    @Override
    protected void onDestroy() {
        vm.destory();
        super.onDestroy();
    }
}
