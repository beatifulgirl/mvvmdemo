package com.example.zhy.mvvmdemo.bean;

import android.databinding.BaseObservable;

import com.android.databinding.library.baseAdapters.BR;

public class ChartBean extends BaseObservable{

    public boolean isMy;

    public String chartMesg;

    public ChartBean(boolean isMy, String chartMesg) {
        this.isMy = isMy;
        this.chartMesg = chartMesg;
    }

}
