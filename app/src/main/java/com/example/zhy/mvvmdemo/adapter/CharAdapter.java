package com.example.zhy.mvvmdemo.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.bean.ChartBean;
import com.example.zhy.mvvmdemo.databinding.ItemChartListBinding;

import java.util.List;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.CharViewHolder> {

    private Activity activity;
    private List<ChartBean> mList;

    public CharAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public CharViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemChartListBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_chart_list,viewGroup,false);
        return new CharViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharViewHolder charViewHolder, int i) {
        ItemChartListBinding dataBinding = charViewHolder.getItemView();
        ChartBean bean = mList.get(i);
        if(bean.isMy){//我的
            dataBinding.rightMsg.setText(bean.chartMesg);
            dataBinding.leftLayout.setVisibility(View.GONE);
        }else{
            dataBinding.leftMsg.setText(bean.chartMesg);
            dataBinding.rightLayout.setVisibility(View.GONE);
        }
        //立即刷新
        charViewHolder.getItemView().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    public void setData(List<ChartBean> mList) {
        this.mList = mList;
        this.notifyDataSetChanged();
    }

    class CharViewHolder extends RecyclerView.ViewHolder {

        private ItemChartListBinding itemView;

        public CharViewHolder(@NonNull ItemChartListBinding itemView) {
            super(itemView.getRoot());
            this.itemView = itemView;
        }

        public ItemChartListBinding getItemView() {
            return itemView;
        }

        public void setItemView(ItemChartListBinding itemView) {
            this.itemView = itemView;
        }
    }
}
