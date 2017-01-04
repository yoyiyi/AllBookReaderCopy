package com.yoyiyi.bookreadercopy.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.yoyiyi.bookreadercopy.api.ApiManager;
import com.yoyiyi.bookreadercopy.api.support.HeaderInterceptor;
import com.yoyiyi.bookreadercopy.api.support.LoggingInterceptor;
import com.yoyiyi.bookreadercopy.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Api模型
 * Created by zzq on 2016/12/5.
 */
@Module
public class ApiModule {

    @Provides
    public OkHttpClient provideOkHttpClient() {
        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
        logging.setLevel(LoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor()) //设置请求头
                .addNetworkInterceptor(new StethoInterceptor())//设置Stetho 调试工具
                .addInterceptor(logging);//设置logging过滤器
        //.addNetworkInterceptor(new CacheInterceptor())
        //.addInterceptor(new CacheInterceptor())
        // .addInterceptor(new LoggerInterceptor());
        return builder.build();
    }

    @Provides
    public ApiManager provideApiManager(OkHttpClient okHttpClient) {
        return ApiManager.getInstance(okHttpClient);
    }

    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("oklog: " + message);
        }
    }
}
