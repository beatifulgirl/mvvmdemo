package com.example.zhy.mvvmdemo.viewmodel;


import com.example.zhy.mvvmdemo.adapter.NewsAdapter;
import com.example.zhy.mvvmdemo.base.BaseListListener;
import com.example.zhy.mvvmdemo.bean.SimpleNewsBean;
import com.example.zhy.mvvmdemo.http.MainConstant;
import com.example.zhy.mvvmdemo.model.INewsModel;
import com.example.zhy.mvvmdemo.model.impl.NewsModelImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class NewsVM implements BaseListListener<SimpleNewsBean> {

    private static final String TAG = "NewsVM";
    private INewsModel mNewsModel;
    private NewsAdapter mAdapter;
    private XRecyclerView newsRv;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public void setData(NewsAdapter newsAdapter, XRecyclerView newsRv) {
        mAdapter = newsAdapter;
        this.newsRv  = newsRv;
        mNewsModel = new NewsModelImpl();
        getNewsData();//获取数据
    }

    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currPage,this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if(currPage>1){//上拉加载
            mAdapter.loadMoreData(list);
        }else{
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
        hindheadfoot();
    }

    @Override
    public void loadStart() {
    }

    @Override
    public void loadComplete() {
        hindheadfoot();
    }

    public void hindheadfoot(){
        newsRv.loadMoreComplete();
        newsRv.refreshComplete();
    }

    public void setRefresh() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        currPage = 1;
        mNewsModel.loadNewsData(currPage,this);
    }

    public void setLoadMore() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        currPage++;
        mNewsModel.loadNewsData(currPage,this);
    }
}
