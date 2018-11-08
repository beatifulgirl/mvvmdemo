package com.example.zhy.mvvmdemo.view.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.zhy.mvvmdemo.R;
import com.example.zhy.mvvmdemo.adapter.TabPagerAdapter;
import com.example.zhy.mvvmdemo.base.BaseFragment;
import com.example.zhy.mvvmdemo.databinding.WidgetCustomBottomTabBinding;

import java.util.List;

public class CustomBottomTabWidget extends LinearLayout {


    private WidgetCustomBottomTabBinding dataBinding;

    private FragmentManager mFragmentManager;
    private List<BaseFragment> mFragmentList;
    private TabPagerAdapter mAdapter;


    public CustomBottomTabWidget(Context context) {
        super(context);
        initView(context);
    }

    public CustomBottomTabWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomBottomTabWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.widget_custom_bottom_tab,this,true);
        dataBinding.setCusrom(new Custom());
        //设置默认的选中项
        selectTab(MenuTab.HOME);
    }

    /**
     * 外部调用初始化，传入必要的参数
     *
     * @param fm
     */
    public void init(FragmentManager fm, List<BaseFragment> fragmentList) {
        mFragmentManager = fm;
        mFragmentList = fragmentList;
        initViewPager();
    }

    /**
     * 初始化 ViewPager
     */
    private void initViewPager() {
        mAdapter = new TabPagerAdapter(mFragmentManager, mFragmentList);
        dataBinding.vpTabWidget.setAdapter(mAdapter);
        dataBinding.vpTabWidget.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将ViewPager与下面的tab关联起来
                switch (position) {
                    case 0:
                        selectTab(MenuTab.HOME);
                        break;
                    case 1:
                        selectTab(MenuTab.NEARBY);
                        break;
                    case 2:
                        selectTab(MenuTab.MINE);
                        break;
                    case 3:
                        selectTab(MenuTab.MORE);
                        break;
                    default:
                        selectTab(MenuTab.HOME);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 设置 Tab 的选中状态
     * @param tab 要选中的标签
     */
    public void selectTab(MenuTab tab) {
        //先将所有tab取消选中，再单独设置要选中的tab
        unCheckedAll();
        switch (tab) {
            case HOME:
                dataBinding.llMenuHomePage.setActivated(true);
                break;
            case NEARBY:
                dataBinding.llMenuNearby.setActivated(true);
                break;
            case MINE:
                dataBinding.llMenuMine.setActivated(true);
                break;
            case MORE:
                dataBinding.llMenuMore.setActivated(true);
                break;
        }
    }

    //让所有tab都取消选中
    private void unCheckedAll() {
        dataBinding.llMenuHomePage.setActivated(false);
        dataBinding.llMenuNearby.setActivated(false);
        dataBinding.llMenuMine.setActivated(false);
        dataBinding.llMenuMore.setActivated(false);
    }

    /**
     * tab的枚举类型
     */
    public enum MenuTab {
        HOME,
        NEARBY,
        MINE,
        MORE
    }

    public class Custom{
        public void onClickView(int i){
            dataBinding.vpTabWidget.setCurrentItem(i);
        }
    }
}
