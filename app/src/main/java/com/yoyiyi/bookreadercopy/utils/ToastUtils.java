package com.yoyiyi.bookreadercopy.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Toast工具类，解决多个Toast同时出现的问题
 * Created by zzq on 2016/12/5.
 */
public class ToastUtils {

    private static Toast mToast;
    private static Context context = AppUtils.getAppContext();

    /************************* 非连续弹出的Toast*******************************************/
    public static void showSingleShortToast(@StringRes int resId) {
        getSingleToast(resId, Toast.LENGTH_SHORT).show();
    }

    public static void showSingleShortToast(String text) {
        getSingleToast(text, Toast.LENGTH_SHORT).show();
    }

    public static void showSingleLongToast(@StringRes int resId) {
        getSingleToast(resId, Toast.LENGTH_LONG).show();
    }

    public static void showSingleLongToast(String text) {
        getSingleToast(text, Toast.LENGTH_LONG).show();
    }

    // 连续调用不会连续弹出，只是替换文本
    public static Toast getSingleToast(@StringRes int resId, int duration) {
        return getSingleToast(context.getResources().getText(resId).toString(), duration);
    }

    /**
     * 获取非连续弹得吐司
     *
     * @param text
     * @param duration
     * @return
     */
    public static Toast getSingleToast(String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
        }
        return mToast;
    }
    /*********************** 非连续弹出的Toast*******************************************/

    /************************** 连续弹出的Toast******************************************/

     public static void showShortToast(@StringRes int resId) {
        getToast(resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShortToast(String text) {
        getToast(text, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(@StringRes int resId) {
        getToast(resId, Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(String text) {
        getToast(text, Toast.LENGTH_LONG).show();
    }
    // 连续调用会连续弹出
    public static Toast getToast(@StringRes int resId, int duration) {
        return getToast(context.getResources().getText(resId).toString(), duration);
    }

    public static Toast getToast(String text, int duration) {
        return Toast.makeText(context, text, duration);
    }

    /*******************************连续弹得吐司****************************************/

}
