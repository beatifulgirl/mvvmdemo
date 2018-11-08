package com.example.zhy.mvvmdemo.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageUtils {

    /**
     * 加载图片
     * @param iv
     * @param url
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv, String url){
        Glide.with(iv.getContext()).load(url).into(iv);
    }

    @BindingAdapter({"resId"})
    public static void loadMipmapResource(ImageView v,int resId){
        v.setBackgroundResource(resId);
    }
}
