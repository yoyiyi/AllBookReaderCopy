package com.yoyiyi.bookreadercopy;

import android.app.Application;

import com.yoyiyi.bookreadercopy.utils.AppUtils;


/**
 * 全局Application
 * Created by zzq on 2016/12/5.
 */

public class BookApplication extends Application
{
    //private AppComponent mAppComponent;
    private static BookApplication mContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        AppUtils.init(this);
        mContext = this;
        initComponent();

    }

    /**
     * @return BookApplication
     */
    public static BookApplication getInstance()
    {
        return mContext;
    }

    /**
     * 初始化Component
     * ApiModule和AppModule
     */
    private void initComponent()
    {
       /* mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
*/
    }

   /* public AppComponent getAppComponent()
    {
        return mAppComponent;
    }*/
}
