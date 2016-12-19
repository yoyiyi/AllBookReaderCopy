package com.yoyiyi.bookreadercopy.utils;

import android.content.Context;

/**
 * AppUtils 工具类
 * Created by zzq on 2016/12/5.
 */

public class AppUtils {
    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static Context getContext() {
        return mContext;
    }
}
