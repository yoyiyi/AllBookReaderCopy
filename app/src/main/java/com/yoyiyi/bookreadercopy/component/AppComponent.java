package com.yoyiyi.bookreadercopy.component;

import android.content.Context;

import com.yoyiyi.bookreadercopy.api.ApiManager;
import com.yoyiyi.bookreadercopy.module.ApiModule;
import com.yoyiyi.bookreadercopy.module.AppModule;

import dagger.Component;

/**
 * Created by zzq on 2016/12/19.
 */
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent {
    ApiManager getApiManger();

    Context getContext() ;
}
