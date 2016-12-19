package com.yoyiyi.bookreadercopy.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;

import com.orhanobut.logger.Logger;

/**
 * 设备工具类
 * Created by zzq on 2016/12/5.
 */
public class DeviceUtils {

    private static final String TAG = DeviceUtils.class.getSimpleName();
    //中国移动
    private static final String CMCC_ISP = "46000";
    //中国移动
    private static final String CMCC2_ISP = "46002";
    //中国联通
    private static final String CU_ISP = "46001";
    //中国电信
    private static final String CT_ISP = "46003";

    /**
     * 获取设备的系统版本号
     */
    public static int getDeviceSDK() {
        return Build.VERSION.SDK_INT;

    }

    /**
     * 获取设备的型号
     */
    public static String getDeviceName() {
        return Build.MODEL;
    }

    /**
     * 获取手机SIM卡的序列号
     *
     * @param context
     * @return
     */
    public static String getIMSI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSubscriberId();
    }

    /**
     * 手机串号:GSM手机的IMEI 和 CDMA手机的 MEID.
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * 获取手机网络运营商类型
     *
     * @param context
     * @return
     */
    public static String getPhoneISP(Context context) {
        if (context == null) {
            return "";
        }
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String teleCompany = "";
        String np = manager.getNetworkOperator();

        if (np != null) {
            if (np.equals(CMCC_ISP) || np.equals(CMCC2_ISP)) {
                teleCompany = "中国移动";
            } else if (np.startsWith(CU_ISP)) {
                teleCompany = "中国联通";
            } else if (np.startsWith(CT_ISP)) {
                teleCompany = "中国电信";
            }
        }
        return teleCompany;
    }

    /**
     * 获取屏幕信息
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm;
    }

    /**
     * 获取打印屏幕信息
     *
     * @param context
     * @return
     */
    public static DisplayMetrics printDisplayInfo(Context context) {
        DisplayMetrics dm = getDisplayMetrics(context);
        StringBuilder sb = new StringBuilder();
        sb.append("\ndensity         :").append(dm.density);
        sb.append("\ndensityDpi      :").append(dm.densityDpi);
        sb.append("\nheightPixels    :").append(dm.heightPixels);
        sb.append("\nwidthPixels     :").append(dm.widthPixels);
        sb.append("\nscaledDensity   :").append(dm.scaledDensity);
        sb.append("\nxdpi            :").append(dm.xdpi);
        sb.append("\nydpi            :").append(dm.ydpi);
        Logger.i(TAG, sb.toString());
        return dm;
    }

    /**
     * 获取系统当前可用内存大小
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.CUPCAKE)
    public static String getAvailMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 将获取的内存大小规格化
        return Formatter.formatFileSize(context, mi.availMem);
    }


    /**
     * 获取 MAC 地址
     * 须配置android.permission.ACCESS_WIFI_STATE权限
     */
    public static String getMacAddress(Context context) {
        //wifi mac地址
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        Logger.i(TAG, " MAC：" + mac);
        return mac;
    }

    /**
     * 获取 开机时间
     */
    public static String getBootTimeString() {
        long time = SystemClock.elapsedRealtime() / 1000;
        int h = (int) ((time / 3600));
        int m = (int) ((time / 60) % 60);
        Logger.i(TAG, h + ":" + m);
        return h + ":" + m;
    }
}
