package com.example.zhy.mvvmdemo.base;

import android.app.Activity;
import android.content.Intent;

public abstract class BaseModel {

    private Activity activity;

    public void setData(Activity activity){
        this.activity = activity;
    }

    public Activity getActivity(){
        return activity;
    }

    public void startActivityFinish(Class mClass){
        activity.startActivity(new Intent(activity,mClass));
        activity.finish();
    }

    public void startActivity(Class mClass){
        activity.startActivity(new Intent(activity,mClass));
    }

}
