package com.yoyiyi.bookreadercopy.api;


import com.yoyiyi.bookreadercopy.base.Constant;
import com.yoyiyi.bookreadercopy.bean.RankingList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * AppManager管理类
 * Created by zzq on 2016/12/5.
 */

public class ApiManager {
    private ApiService service;
    private static ApiManager instance;

    public ApiManager(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.API_BASE_URL)
                //RxJava适配器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //Gson适配器
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static ApiManager getInstance(OkHttpClient okHttpClient) {
        if (instance == null)
            synchronized (ApiManager.class) {
                if (instance == null) {
                    instance = new ApiManager(okHttpClient);
                }
            }
        return instance;
    }

    /**
     * 获取排行
     *
     * @return
     */
    public Observable<RankingList> getRanking() {
        return service.getRanking();
    }
}
