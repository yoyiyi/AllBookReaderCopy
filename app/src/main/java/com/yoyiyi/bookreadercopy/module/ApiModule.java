package com.yoyiyi.bookreadercopy.module;

import com.yoyiyi.bookreadercopy.api.ApiManager;
import com.yoyiyi.bookreadercopy.api.support.CacheInterceptor;
import com.yoyiyi.bookreadercopy.api.support.HeaderInterceptor;
import com.yoyiyi.bookreadercopy.api.support.LoggerInterceptor;

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
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .addInterceptor(new CacheInterceptor())
                .addInterceptor(new LoggerInterceptor());
        return builder.build();
    }

    @Provides
    public ApiManager provideApiManager(OkHttpClient okHttpClient) {
        return ApiManager.getInstance(okHttpClient);
    }
}
