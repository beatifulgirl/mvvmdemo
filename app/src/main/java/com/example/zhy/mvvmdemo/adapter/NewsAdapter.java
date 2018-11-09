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
import com.example.zhy.mvvmdemo.databinding.ItemHomeListBinding;
import com.example.zhy.mvvmdemo.utils.GlideImageLoader;
import com.example.zhy.mvvmdemo.utils.MyToast;
import com.youth.banner.Transformer;


public class NewsAdapter extends BaseAdapter<SimpleNewsBean,BaseViewHolder> {

    private ItemHomeListBinding bannerDatabinding;

    public NewsAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).id.get();
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = null;
        if(viewType==1){
            dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_home_list, parent, false);
        }else if(viewType==2){
            dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_news, parent, false);
        }
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        int type = getItemViewType(position);
        if(type==1){
            bannerDatabinding = (ItemHomeListBinding) baseViewHolder.getBinding();
            bannerDatabinding.banners.setImageLoader(new GlideImageLoader());
            bannerDatabinding.banners.setBannerAnimation(Transformer.ScaleInOut);
            bannerDatabinding.banners.setImages(mList.get(position).thumbnails);
            bannerDatabinding.banners.start();
        } else if (type==2) {
            ViewDataBinding binding = baseViewHolder.getBinding();
            binding.setVariable(BR.simpleNewsBean, mList.get(position));
            binding.setVariable(BR.position,position);
            binding.setVariable(BR.adapter,this);
            binding.executePendingBindings(); //防止闪烁
        }
    }

    public ItemHomeListBinding getBannerDatabinding(){
        return  bannerDatabinding;
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
