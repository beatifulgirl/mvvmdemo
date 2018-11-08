package com.example.zhy.mvvmdemo.utils;

import android.widget.Toast;

import com.example.zhy.mvvmdemo.App;


public class MyToast {

    public static void toast(String str){
        Toast.makeText(App.getApp(),str,Toast.LENGTH_SHORT).show();
    }
}
