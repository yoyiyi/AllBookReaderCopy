package com.yoyiyi.bookreadercopy.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by zzq on 2016/12/19.
 */

public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;
    protected CompositeSubscription mCompositeSubscription;

    /**
     * 解绑
     */
    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 绑定
     * @param subscription 订阅
     */
    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    @Override
    public void attachView(T view) {
        this.mView = view;
    }


    @Override
    public void detchView() {
        this.mView = null;
        unSubscribe();
    }
}
