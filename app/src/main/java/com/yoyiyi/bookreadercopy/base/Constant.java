package com.yoyiyi.bookreadercopy.base;

import com.yoyiyi.bookreadercopy.utils.AppUtils;
import com.yoyiyi.bookreadercopy.utils.FileUtils;

/**
 * Created by zzq on 2016/12/5.
 */

public class Constant {
    //base_url
    public static final String API_BASE_URL = "http://api.zhuishushenqi.com";
    public static final String IMG_BASE_URL = "http://statics.zhuishushenqi.com";
    //缓存数据目录
    public static String PATH_DATA = FileUtils.createRootPath(AppUtils.getAppContext()) + "/cache";
    //缓存数据目录
    public static String PATH_COLLECT = FileUtils.createRootPath(AppUtils.getAppContext()) + "/collect";
    //根目录
    public static String BASE_PATH = FileUtils.createRootPath(AppUtils.getAppContext()) + "/book/";
}
