package com.yoyiyi.bookreadercopy.ui.activity;

import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.BaseActivity;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.component.DaggerFindComponent;
import com.yoyiyi.bookreadercopy.entities.RankingList;
import com.yoyiyi.bookreadercopy.ui.contract.TopRankContract;
import com.yoyiyi.bookreadercopy.ui.presenter.TopRankPresenter;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by zzq on 2016/12/19.
 */

public class TopRankActivity extends BaseActivity implements TopRankContract.View {

    @Inject
    TopRankPresenter mPresenter;

    @BindView(R.id.ceshi)
    TextView mCeshi;


    @Override
    public int getLayoutId() {
        return R.layout.activity_top_rank;
    }

    @Override
    public void initWidget() {
        //showProgress();

        mPresenter.attachView(this);
        mPresenter.getRankList();

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initToolbar() {
        mToolbar.setTitle("排行榜");
        mToolbar.setNavigationIcon(R.drawable.ab_back);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerFindComponent
                .builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    public void showRankList(RankingList rankingList) {
        Logger.d(rankingList);
    }

    @Override
    public void showError() {
        //   hideProgress();
    }

    @Override
    public void complete() {
        //  hideProgress();
    }

}
