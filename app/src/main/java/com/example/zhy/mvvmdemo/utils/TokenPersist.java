package com.example.zhy.mvvmdemo.utils;

import android.content.SharedPreferences;

import com.example.zhy.mvvmdemo.App;

/**
 * Created by admin on 2016/9/6.
 */
public class TokenPersist {

    private static final String TOKEN_STORE_FILE = "Token";

    private static SharedPreferences getSp() {
        return App.getApp().getSharedPreferences(TOKEN_STORE_FILE, 0);
    }

    /**
     * 保存token
     */
    public static void storeToken(String Id) {
        getSp().edit().putString("token_id", Id).commit();
    }

    /**
     * 得到token
     */
    public static String getToken() {
        return getSp().getString("token_id", "");
    }


    /**
     * 删除token
     */
    public static void deleToken() {
        getSp().edit().clear().commit();
    }
}
