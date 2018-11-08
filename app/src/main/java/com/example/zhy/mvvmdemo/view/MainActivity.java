package com.example.zhy.mvvmdemo.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.adapter.NewsAdapter;
import com.example.zhy.mvvmdemo.base.BaseActivity;
import com.example.zhy.mvvmdemo.databinding.ActivityMainBinding;
import com.example.zhy.mvvmdemo.view.iview.INewsView;
import com.example.zhy.mvvmdemo.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements XRecyclerView.LoadingListener, INewsView {

    private NewsVM newsVM;
    private NewsAdapter newsAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getDataBind().newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        getDataBind().newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        getDataBind().newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        getDataBind().newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        getDataBind().newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        getDataBind().newsRv.setAdapter(newsAdapter);

        newsVM = new NewsVM();
        newsVM.setData(newsAdapter,getDataBind().newsRv);
    }

    @Override
    public void onRefresh() {
        newsVM.setRefresh();
    }

    @Override
    public void onLoadMore() {
        newsVM.setLoadMore();
    }

    @Override
    public void loadStart(int loadType) {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void loadFailure(String message) {

    }
}
