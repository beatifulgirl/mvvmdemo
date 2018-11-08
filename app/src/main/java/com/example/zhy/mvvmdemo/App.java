package com.example.zhy.mvvmdemo;

import android.app.Application;

import com.example.zhy.mvvmdemo.utils.Utils;


public class App extends Application {

    private static App mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mInstance = this;
        Utils.nuke();
    }

    public static App getApp(){
        return mInstance;
    }
}
