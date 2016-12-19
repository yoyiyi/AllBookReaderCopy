package com.yoyiyi.bookreadercopy;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.component.DaggerAppComponent;
import com.yoyiyi.bookreadercopy.module.ApiModule;
import com.yoyiyi.bookreadercopy.module.AppModule;
import com.yoyiyi.bookreadercopy.utils.AppUtils;
import com.yoyiyi.bookreadercopy.utils.LogUtils;
import com.yoyiyi.bookreadercopy.utils.SharedPreferencesUtil;


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
        initLog();
        initStetho();
        initPrefs();

    }

    /**
     * 初始化SharedPreferences
     */
    private void initPrefs() {
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);

    }

    /**
     * 初始化Log打印信息
     */
    private void initLog() {
        Logger
                .init()
                .methodCount(3)
                //.hideThreadInfo()//隐藏线程信息
                .logLevel(LogLevel.FULL);//打印全部
        LogUtils.init(this);
    }

    /**
     * 获取BookApplication实例
     *
     * @return BookApplication
     */
    public static BookApplication getInstance() {
        return mContext;
    }

    /**
     * 初始化Stetho调试工具
     */
    private void initStetho() {
        //初始化Stetho调试工具
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    /**
     * 初始化Component
     * ApiModule和AppModule
     */

    private void initComponent() {
        mAppComponent = DaggerAppComponent
                .builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();

    }

    /**
     * 获取Appcomponent实例
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
