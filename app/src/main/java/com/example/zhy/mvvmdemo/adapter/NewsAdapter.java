package com.example.zhy.mvvmdemo.adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.example.zhy.mvvmdemo.BR;
import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.base.BaseAdapter;
import com.example.zhy.mvvmdemo.base.BaseViewHolder;
import com.example.zhy.mvvmdemo.bean.SimpleNewsBean;
import com.example.zhy.mvvmdemo.utils.MyToast;


public class NewsAdapter extends BaseAdapter<SimpleNewsBean,BaseViewHolder> {


    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);

    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simpleNewsBean, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

    /**
     * 点赞
     * @param simpleNewsBean
     * @param position
     */
    public void clickDianZan(SimpleNewsBean simpleNewsBean, int position) {
        if(simpleNewsBean.isGood.get()){
            MyToast.toast("取消成功");
        }else{
            MyToast.toast("点赞成功");
        }
        mList.get(position).isGood.set(!mList.get(position).isGood.get());
    }





}
