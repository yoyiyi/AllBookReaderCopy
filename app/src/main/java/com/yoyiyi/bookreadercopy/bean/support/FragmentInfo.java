package com.yoyiyi.bookreadercopy.bean.support;

import android.support.v4.app.Fragment;

/**
 * Created by zzq on 2017/1/5.
 */

public class FragmentInfo {
    public String title;
    public Fragment fragment;
    public FragmentInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
