package com.example.zhy.mvvmdemo.viewmodel;


import android.app.Activity;
import android.content.Intent;

import com.example.zhy.mvvmdemo.base.BaseModel;
import com.example.zhy.mvvmdemo.view.ChartActivity;

public class MyVM extends BaseModel {

   private Activity activity;

    @Override
    public void setData(Activity activity) {
        super.setData(activity);
        this.activity = activity;
    }

    public void toChart(){
        activity.startActivity(new Intent(activity, ChartActivity.class));
    }


}
