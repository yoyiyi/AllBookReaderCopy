package com.yoyiyi.bookreadercopy.utils;


import android.text.TextUtils;

import com.google.gson.Gson;

import com.yoyiyi.bookreadercopy.BookApplication;

import java.lang.reflect.Field;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * 统一处理线程
 * Created by zzq on 2016/12/19.
 */

public class RxUtils {

    /**
     * 生命周期管理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 从本地获取数据
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Observable rxCreateDiskObservable(final String key, final Class<T> clazz) {
        return Observable.create(subscriber -> {
            LogUtils.d("Get data from Disk:");
            String json = ACache.get(BookApplication.getInstance()).getAsString(key);
            LogUtils.i(json);
            if (!TextUtils.isEmpty(json)) {
                subscriber.onNext(json);
            }
            subscriber.onCompleted();
        })
                .map(s -> new Gson().fromJson((String) s, clazz))
                .subscribeOn(Schedulers.io());

    }

    /**
     * 缓存List数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxCacheListHelper(final String key) {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                //在OnNext之前执行
                .doOnNext(data ->
                        //创建新线程 类似于传统 new Thread()
                        Schedulers.io().createWorker().schedule(() -> {
                            LogUtils.d("get data from network finish ,start cache...");
                            //通过反射获取List,再判空决定是否缓存
                            Class clazz = data.getClass();
                            Field[] fields = clazz.getFields();
                            for (Field field : fields) {
                                String className = field.getType().getSimpleName();
                                // 得到属性值
                                if (className.equalsIgnoreCase("List")) {
                                    try {
                                        List list = (List) field.get(data);
                                        LogUtils.d("list==" + list);
                                        if (!list.isEmpty()) {
                                            ACache.get(BookApplication.getInstance())
                                                    .put(key, new Gson().toJson(data, clazz));
                                            LogUtils.d("cache finish");
                                        }
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }))
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 缓存Bean数据
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> rxCacheBeanHelper(final String key) {
        return observable -> observable.subscribeOn(Schedulers.io())
                .doOnNext(data ->
                        Schedulers.io().createWorker().schedule(() -> {
                            LogUtils.d("get data from network finish ,start cache...");
                            ACache.get(BookApplication.getInstance())
                                    .put(key, new Gson().toJson(data, data.getClass()));
                            LogUtils.d("cache finish");
                        }))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
