package com.yoyiyi.bookreadercopy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by zzq on 2016/12/5.
 */

public class NetworkUtils
{
    private static final String TAG = NetworkUtils.class.getSimpleName();
    public static final int NETWORK_WIFI = 1;    // wifi network
    public static final int NETWORK_4G = 4;    // "4G" networks
    public static final int NETWORK_3G = 3;    // "3G" networks
    public static final int NETWORK_2G = 2;    // "2G" networks
    public static final int NETWORK_UNKNOWN = 5;    // unknown network
    public static final int NETWORK_NO = -1;   // no network

    private static final int NETWORK_TYPE_GSM = 16;
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    private static final int NETWORK_TYPE_IWLAN = 18;
    /**
     * 获取网络环境
     *
     * @return
     */
    private static NetworkInfo getActiveNetworkInfo()
    {
        ConnectivityManager cm = (ConnectivityManager) AppUtils.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }


    /**
     * 判断网络是否可用
     *
     * @return
     */
    public static boolean isAvailable()
    {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }


    /**
     * 判断网络是否连接
     *
     * @return
     */
    public static boolean isConnected()
    {
        NetworkInfo info = getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
