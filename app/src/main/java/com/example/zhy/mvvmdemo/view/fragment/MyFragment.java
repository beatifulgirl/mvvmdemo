package com.example.zhy.mvvmdemo.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.base.BaseFragment;
import com.example.zhy.mvvmdemo.databinding.FragmentMyBinding;
import com.example.zhy.mvvmdemo.viewmodel.MyVM;


public class MyFragment extends BaseFragment<FragmentMyBinding> {

    @Override
    protected int setView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
         MyVM vm = new MyVM();
         vm.setData(mActivity);
         getDataBind().setViewModel(vm);
    }

}
