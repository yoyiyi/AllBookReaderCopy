package com.yoyiyi.bookreadercopy;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.component.DaggerAppComponent;
import com.yoyiyi.bookreadercopy.module.ApiModule;
import com.yoyiyi.bookreadercopy.module.AppModule;
import com.yoyiyi.bookreadercopy.utils.AppUtils;


/**
 * 全局Application
 * Created by zzq on 2016/12/5.
 */

public class BookApplication extends Application {
    private AppComponent mAppComponent;
    private static BookApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        mContext = this;
        initComponent();
        initLogger();

    }

    private void initLogger() {
        Logger
                .init()
                .methodCount(3)
                //.hideThreadInfo()//隐藏线程信息
                .logLevel(LogLevel.FULL);//打印全部
    }

    /**
     * @return BookApplication
     */
    public static BookApplication getInstance() {
        return mContext;
    }

    /**
     * 初始化Component
     * ApiModule和AppModule
     */
    private void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
