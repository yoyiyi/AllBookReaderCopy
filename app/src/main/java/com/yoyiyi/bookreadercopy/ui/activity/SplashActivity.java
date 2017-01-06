package com.yoyiyi.bookreadercopy.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.widget.ImageView;

import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by zzq on 2017/1/5.
 */

public class SplashActivity extends BaseActivity {

    private static final float SCALE_END = 1.15f;
    private static final long ANIMATION_TIME = 2000;

    @BindView(R.id.iv_splash)
    ImageView mIvSplash;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initWidget() {
        hideStatusBar();
        Observable
                .timer(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(time -> startAnim());

    }

    /**
     * 跳转到主页面
     */
    private void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * 开始动画
     */
    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvSplash, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvSplash, "scaleY", 1f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME)
                .play(animatorX)
                .with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                gotoMainActivity();
            }

        });
    }

}
