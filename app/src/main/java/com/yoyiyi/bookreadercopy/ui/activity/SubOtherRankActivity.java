package com.yoyiyi.bookreadercopy.ui.activity;

import android.content.Context;
import android.content.Intent;


/**
 * Created by zzq on 2017/1/6.
 */
public class SubOtherRankActivity {

    public final static String BUNDLE_ID = "_id";
    public static final String INTENT_TITLE = "title";

    public static void startActivity(Context context, String id, String title) {
        context.startActivity(new Intent(context, SubOtherRankActivity.class)
                .putExtra(INTENT_TITLE, title)
                .putExtra(BUNDLE_ID, id));
    }
}
