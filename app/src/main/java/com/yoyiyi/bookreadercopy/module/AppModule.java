package com.yoyiyi.bookreadercopy.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * App模型
 * Created by zzq on 2016/12/5.
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context getContext() {
        return mContext;
    }
}
