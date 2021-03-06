package com.yoyiyi.bookreadercopy.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.yoyiyi.bookreadercopy.BookApplication;
import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.utils.StatusBarCompat;
import com.yoyiyi.bookreadercopy.widget.ProgressWheel;

import butterknife.ButterKnife;


/**
 * Created by zzq on 2016/12/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected ProgressWheel mLoading;
    protected int statusBarColor = 0;//状态栏颜色
    protected View statusBarView = null;//状态栏View
    protected Context mContext;//上下文环境
    protected Toolbar mToolbar;//Toolbar

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (statusBarColor == 0) {
            statusBarView = StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimaryDark));
        } else {
            statusBarView = StatusBarCompat.compat(this, statusBarColor);
        }
        //Api 19 和 Api 20 设置不同 状态栏
        transparent19and20();
        mContext = this;

        ButterKnife.bind(this);

        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        mLoading = ButterKnife.findById(this, R.id.common_progress);

        setupActivityComponent(BookApplication.getInstance().getAppComponent());

        if (mToolbar != null) {
            //让组件支持Toolbar
            setSupportActionBar(mToolbar);
            //初始化Toolbar
            initToolbar();

        }
        if (mLoading != null) {
            //显示加载框
            showLoading();
        }
        initWidget();
        initVariables();
        initDatas();
    }

    private void transparent19and20() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT//4.4 和 5.0
                && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * 布局文件
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initWidget();

    /**
     * 初始化数据
     */
    public void initDatas() {
    }


    /**
     * 初始化Toolbar
     */
    public void initToolbar() {

    }

    /**
     * 初始化变量
     */
    public void initVariables() {
    }

    /**
     * 初始化Component
     *
     * @param appComponent
     */
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    /**
     * 显示加载框
     */
    public void showLoading() {
        mLoading.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏加载框
     */
    public void hideLoading() {
        if (mLoading != null) {
            mLoading.setVisibility(View.GONE);
        }
    }


    /**
     * 隐藏状态栏
     */
    protected void hideStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    /**
     * 显示状态栏
     */
    protected void showStatusBar() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        if (statusBarView != null) {
            statusBarView.setBackgroundColor(statusBarColor);
        }
    }


    /**
     * 隐藏View
     *
     * @param views
     */
    protected void gone(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 显示View
     *
     * @param views
     */
    protected void visible(final View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    /**
     * 判断View是否Visible
     *
     * @param view
     * @return
     */
    protected boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }


}
