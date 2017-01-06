package com.yoyiyi.bookreadercopy.ui.adapter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.yoyiyi.bookreadercopy.bean.support.FragmentInfo;
import com.yoyiyi.bookreadercopy.ui.fragment.FindFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzq on 2017/1/5.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private List<FragmentInfo> mInfos;
    private TabLayout mTabLayout;

    public MainAdapter(FragmentManager fm, ViewPager viewPager, TabLayout tabLayout) {
        super(fm);
        this.mContext = viewPager.getContext();
        this.mTabLayout = tabLayout;
        initFragmentInfo();
        initTabLayout();
        viewPager.setAdapter(this);
        mTabLayout.setupWithViewPager(viewPager);
    }

    private void initTabLayout() {
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (FragmentInfo info : mInfos) {
            mTabLayout.addTab(mTabLayout.newTab().setText(info.title));
        }
    }

    private void initFragmentInfo() {
        mInfos = new ArrayList<>();
        mInfos.add(new FragmentInfo("书架", new FindFragment()));
        mInfos.add(new FragmentInfo("社区", new FindFragment()));
        mInfos.add(new FragmentInfo("发现", new FindFragment()));
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mInfos.get(position).fragment;
        if (fragment == null) {
            fragment = Fragment.instantiate(mContext, fragment.getClass().getName());
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mInfos.get(position).title;
    }
}
