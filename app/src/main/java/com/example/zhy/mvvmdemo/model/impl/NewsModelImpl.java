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
                        Log.i(TAG, "onNext: "+ newsBean.getLimit());
                        List<NewsBean.OthersBean> othersBeanList = new ArrayList<>();
                        NewsBean.OthersBean bean =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学1");
                        NewsBean.OthersBean bean1 =  new NewsBean.OthersBean(15007,"http://pic2.zhimg.com/98d7b4f8169c596efb6ee8487a30c8ee.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学2");
                        NewsBean.OthersBean bean2 =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/bcf7d594f126e5ceb22691be32b2650a.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学3");
                        NewsBean.OthersBean bean3 =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/98d7b4f8169c596efb6ee8487a30c8ee.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学4");
                        NewsBean.OthersBean bean4 =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学4");
                        NewsBean.OthersBean bean5 =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/bcf7d594f126e5ceb22691be32b2650a.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学4");
                        NewsBean.OthersBean bean6 =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学4");
                        NewsBean.OthersBean bean7 =  new NewsBean.OthersBean(15007,"http://pic3.zhimg.com/0e71e90fd6be47630399d63c58beebfc.jpg","了解自己和别人，了解彼此的欲望和局限。",13,"日常心理学4");
                        othersBeanList.add(bean) ;
                        othersBeanList.add(bean1) ;
                        othersBeanList.add(bean2) ;
                        othersBeanList.add(bean3) ;
                        othersBeanList.add(bean4) ;
                        othersBeanList.add(bean5) ;
                        othersBeanList.add(bean6) ;
                        othersBeanList.add(bean7) ;

                        if(page==1){
                            simpleNewsBeanList.clear();
                        }
                        if (othersBeanList != null && othersBeanList.size() > 0) {
                            for (NewsBean.OthersBean othersBean : othersBeanList) {
                                String thumbnail = othersBean.getThumbnail();
                                String name = othersBean.getName();
                                String description = othersBean.getDescription();

                                //构造Adapter所需的数据源
                                SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBean.name.set(name);
                                simpleNewsBean.description.set(description);
                                simpleNewsBeanList.add(simpleNewsBean);
                            }
                        }

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
