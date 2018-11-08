package com.example.zhy.mvvmdemo.utils;

import android.content.SharedPreferences;

import com.example.zhy.mvvmdemo.App;

/**
 * Created by admin  
 */
public class UserPersist {

	private static final String USER_STORE_FILE = "user";

	private static SharedPreferences getSp() {
		return App.getApp().getSharedPreferences(USER_STORE_FILE, 0);
	}

	/**
	 * 删除用户
	 */
	public static void deleUser() {
		getSp().edit().clear().commit();
	}
	/**
	 * 保存用户名
	 */
	public static void storeUserID(String Id) {
		getSp().edit().putString("user_id", Id).commit();
	}
	/**
	 * 得到当前用户名
	 */
	public static String getUserID() {
		return getSp().getString("user_id", "");
	}


}
