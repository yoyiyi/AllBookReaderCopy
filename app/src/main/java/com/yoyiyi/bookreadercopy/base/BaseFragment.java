package com.yoyiyi.bookreadercopy.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoyiyi.bookreadercopy.BookApplication;
import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.widget.ProgressWheel;

import butterknife.ButterKnife;

/**
 * Created by zzq on 2016/12/27.
 */

public abstract class BaseFragment extends Fragment {
    protected View parentView;
    protected FragmentActivity activity;
    protected LayoutInflater inflater;

    protected Context mContext;
    protected ProgressWheel mLoading;

    public abstract
    @LayoutRes
    int getLayoutResId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        mContext = activity;
        this.inflater = inflater;
        return parentView;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        mLoading = ButterKnife.findById(getParentView(), R.id.common_progress);
        if (mLoading != null) {
            //显示加载框
            showLoading();
        }
        setupActivityComponent(BookApplication.getInstance().getAppComponent());
        attachView();
        initDatas();
        initWidget();
    }

    /**
     * 粘贴View
     */
    public abstract void attachView();

    /**
     * 初始化数据
     */
    public abstract void initDatas();

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    public abstract void initWidget();

    /**
     * 初始化AppComponent组件
     *
     * @param appComponent component
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 粘贴
     *
     * @param activity activity
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    /**
     * 分离
     */
    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }


    /**
     * 获取Activity
     *
     * @return
     */
    public FragmentActivity getSupportActivity() {
        return (FragmentActivity) super.getActivity();
    }

    /**
     * 获取ApplicationContext 信息
     *
     * @return
     */
    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ? null : getActivity()
                .getApplicationContext()) : this.activity.getApplicationContext();
    }

    /**
     * getLayoutInflate
     *
     * @return
     */
    protected LayoutInflater getLayoutInflater() {
        return inflater;
    }

    /**
     * 获取父View
     *
     * @return
     */
    protected View getParentView() {
        return parentView;
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
