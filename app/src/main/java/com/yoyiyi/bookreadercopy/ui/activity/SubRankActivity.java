package com.yoyiyi.bookreadercopy.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.BaseActivity;


/**
 * Created by zzq on 2017/1/6.
 */
public class SubRankActivity extends BaseActivity {

    public static final String INTENT_ID = "_id";
    public static final String INTENT_MONTH = "month";
    public static final String INTENT_ALL = "all";
    public static final String INTENT_TITLE = "title";
    private String _id, month, all, title;

    public static void startActivity(Context context, String _id, String month, String all, String title) {

        context.startActivity(new Intent(context, SubRankActivity.class)
                .putExtra(INTENT_ID, _id)
                .putExtra(INTENT_MONTH, month)
                .putExtra(INTENT_ALL, all)
                .putExtra(INTENT_TITLE, title));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sub_rank;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void initToolbar() {
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void initVariables() {
        _id = getIntent().getStringExtra(INTENT_ID);
        month = getIntent().getStringExtra(INTENT_MONTH);
        all = getIntent().getStringExtra(INTENT_ALL);
        title = getIntent().getStringExtra(INTENT_TITLE);
    }
}
