package com.example.zhy.mvvmdemo.model.impl;

import android.util.Log;

import com.example.zhy.mvvmdemo.base.BaseListListener;
import com.example.zhy.mvvmdemo.bean.NewsBean;
import com.example.zhy.mvvmdemo.bean.SimpleNewsBean;
import com.example.zhy.mvvmdemo.http.service.NewsService;
import com.example.zhy.mvvmdemo.model.INewsModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class NewsModelImpl implements INewsModel {

    private static final String TAG = "NewsModelImpl";
    List<SimpleNewsBean> simpleNewsBeanList = new ArrayList<SimpleNewsBean>();

    @Override
    public void loadNewsData(final int page, final BaseListListener<SimpleNewsBean> loadListener) {
        NewsService.getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(NewsBean newsBean) {
                        if(page==1){
                            simpleNewsBeanList.clear();
                        }
                        //构造Adapter所需的数据源
                        SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                        simpleNewsBean.thumbnail.set("http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg");
                        simpleNewsBean.thumbnails.add("http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg");
                        simpleNewsBean.thumbnails.add("http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg");
                        simpleNewsBean.thumbnails.add("http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg");
                        simpleNewsBean.id.set(1);


                        SimpleNewsBean simpleNewsBean2 = new SimpleNewsBean();
                        simpleNewsBean2.thumbnail.set("http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg");
                        simpleNewsBean2.name.set("哈哈哈");
                        simpleNewsBean2.description.set("描述描述描述描述描述描述描述描述描述描述");
                        simpleNewsBean2.id.set(2);
                        simpleNewsBeanList.add(simpleNewsBean);
                        simpleNewsBeanList.add(simpleNewsBean2);
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        loadListener.loadStart();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: ");
                        loadListener.loadFailure(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        loadListener.loadSuccess(simpleNewsBeanList);
                        loadListener.loadComplete();
                    }
                });
    }
}
