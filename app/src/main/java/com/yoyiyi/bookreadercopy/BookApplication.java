/*

                       ::
                      :;J7, :,                        ::;7:
                      ,ivYi, ,                       ;LLLFS:
                      :iv7Yi        狗头保佑         :7ri;j5PL
                     ,:ivYLvr       永无BUG       ,ivrrirrY2X,
                     :;r@Wwz.7r:                :ivu@kexianli.
                    :iL7::,:::iiirii:ii;::::,,irvF7rvvLujL7ur
                   ri::,:,::i:iiiiiii:i:irrv177JX7rYXqZEkvv17
                ;i:, , ::::iirrririi:i:::iiir2XXvii;L8OGJr71i
              :,, ,,:   ,::ir@mingyi.irii:i:::j1jri7ZBOS7ivv,
                 ,::,    ::rv77iiiriii:iii:i::,rvLq@huhao.Li
             ,,      ,, ,:ir7ir::,:::i;ir:::i:i::rSGGYri712:
           :::  ,v7r:: ::rrv77:, ,, ,:i7rrii:::::, ir7ri7Lri
          ,     2OBBOi,iiir;r::        ,irriiii::,, ,iv7Luur:
        ,,     i78MBBi,:,:::,:,  :7FSL: ,iriii:::i::,,:rLqXv::
        :      iuMMP: :,:::,:ii;2GY7OBB0viiii:i:iii:i:::iJqL;::
       ,     ::::i   ,,,,, ::LuBBu BBBBBErii:i:i:i:i:i:i:r77ii
      ,       :       , ,,:::rruBZ1MBBqi, :,,,:::,::::::iiriri:
     ,               ,,,,::::i:  @arqiao.       ,:,, ,:::ii;i7:
    :,       rjujLYLi   ,,:::::,:::::::::,,   ,:i,:,,,,,::i:iii
    ::      BBBBBBBBB0,    ,,::: , ,:::::: ,      ,,,, ,,:::::::
    i,  ,  ,8BMMBBBBBBi     ,,:,,     ,,, , ,   , , , :,::ii::i::
    :      iZMOMOMBBM2::::::::::,,,,     ,,,,,,:,,,::::i:irr:i:::,
    i   ,,:;u0MBMOG1L:::i::::::  ,,,::,   ,,, ::::::i:i:iirii:i:i:
    :    ,iuUuuXUkFu7i:iii:i:::, :,:,: ::::::::i:i:::::iirr7iiri::
    :     :rk@Yizero.i:::::, ,:ii:::::::i:::::i::,::::iirrriiiri::,
     :      5BMBBBBBBSr:,::rv2kuii:::iii::,:i:,, , ,,:,:i@petermu.,
          , :r50EZ8MBBBBGOBBBZP7::::i::,:::::,: :,:,::i;rrririiii::
              :jujYY7LS0ujJL7r::,::i::,::::::::::::::iirirrrrrrr:ii:
           ,:  :@kevensun.:,:,,,::::i:i:::::,,::::::iir;ii;7v77;ii;i,
           ,,,     ,,:,::::::i:iiiii:i::::,, ::::iiiir@xingjief.r;7:i,
        , , ,,,:,,::::::::iiiiiiiiii:,:,:::::::::iiir;ri7vL77rrirri::
         :,, , ::::::::i:::i:::i:i::,,,,,:,::i:i:::iir;@Secbone.ii:::

* */

package com.yoyiyi.bookreadercopy;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.yoyiyi.bookreadercopy.base.CrashHandler;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.component.DaggerAppComponent;
import com.yoyiyi.bookreadercopy.module.ApiModule;
import com.yoyiyi.bookreadercopy.module.AppModule;
import com.yoyiyi.bookreadercopy.utils.AppUtils;
import com.yoyiyi.bookreadercopy.utils.LogUtils;
import com.yoyiyi.bookreadercopy.utils.SharedPreferencesUtils;

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

        mContext = this;
        //App帮助类
        AppUtils.init(this);
        //Component
        initComponent();
        //Log
        initLog();
        //Stetho调试类
        initStetho();
        //sp
        initPrefs();
        //CrashHandler
        initCrashHandler();

    }

    /**
     * 初始化CrashHandler
     */
    private void initCrashHandler() {
        CrashHandler.getInstance().init(this);
    }

    /**
     * 初始化SharedPreferences
     */
    private void initPrefs() {
        SharedPreferencesUtils.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);

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
        //Stetho.initializeWithDefaults(this);
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
