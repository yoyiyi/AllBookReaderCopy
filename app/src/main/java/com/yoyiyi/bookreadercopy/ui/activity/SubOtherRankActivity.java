package com.yoyiyi.bookreadercopy.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.yoyiyi.bookreadercopy.base.BaseActivity;


/**
 * Created by zzq on 2017/1/6.
 */
public class SubOtherRankActivity extends BaseActivity {

    public final static String BUNDLE_ID = "_id";
    public static final String INTENT_TITLE = "title";

    public static void startActivity(Context context, String id, String title) {
        context.startActivity(new Intent(context, SubOtherRankActivity.class)
                .putExtra(INTENT_TITLE, title)
                .putExtra(BUNDLE_ID, id));
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initWidget() {

    }
}
