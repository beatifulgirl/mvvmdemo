package com.example.zhy.mvvmdemo.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.adapter.NewsAdapter;
import com.example.zhy.mvvmdemo.base.BaseFragment;
import com.example.zhy.mvvmdemo.databinding.FragmentHomeBinding;
import com.example.zhy.mvvmdemo.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements XRecyclerView.LoadingListener {

    private NewsVM mNewsVM;
    private NewsAdapter adapter;

    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        getDataBind().fragmentNewsRvs.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        getDataBind().fragmentNewsRvs.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        getDataBind().fragmentNewsRvs.setArrowImageView(R.mipmap.pull_down_arrow);
        getDataBind().fragmentNewsRvs.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false);
        getDataBind().fragmentNewsRvs.setLayoutManager(layoutManager);

        adapter = new NewsAdapter(mActivity);
        getDataBind().fragmentNewsRvs.setAdapter(adapter);

        mNewsVM = new NewsVM();
        mNewsVM.setData(adapter, getDataBind().fragmentNewsRvs);
    }

    @Override
    public void onRefresh() {
        mNewsVM.setRefresh();
    }

    @Override
    public void onLoadMore() {
        mNewsVM.setLoadMore();
    }


}
