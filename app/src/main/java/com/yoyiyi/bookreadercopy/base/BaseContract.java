package com.yoyiyi.bookreadercopy.base;

/**
 * Created by zzq on 2016/12/5.
 */

public interface BaseContract {
    /**
     * BasePresenter
     *
     * @param <T>
     */
    interface BasePresenter<T> {
        /**
         * 绑定
         *
         * @param view
         */
        void attachView(T view);

        /**
         * 解绑
         */
        void detchView();
    }

    /**
     * BaseView
     */
    interface BaseView {

        /**
         * 错误
         */
        void showError();

        /**
         * 完成
         */
        void complete();

    }
}
