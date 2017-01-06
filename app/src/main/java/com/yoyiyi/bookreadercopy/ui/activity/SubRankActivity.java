package com.yoyiyi.bookreadercopy.ui.activity;

import android.content.Context;
import android.content.Intent;


/**
 * Created by zzq on 2017/1/6.
 */
public class SubRankActivity {

    public static final String INTENT_ID = "_id";
    public static final String INTENT_MONTH = "month";
    public static final String INTENT_ALL = "all";
    public static final String INTENT_TITLE = "title";

    public static void startActivity(Context context, String _id, String month, String all, String title) {

        context.startActivity(new Intent(context, SubRankActivity.class)
                        .putExtra(INTENT_ID, _id)
                        .putExtra(INTENT_MONTH, month)
                        .putExtra(INTENT_ALL, all)
                        .putExtra(INTENT_TITLE, title));
    }
}
