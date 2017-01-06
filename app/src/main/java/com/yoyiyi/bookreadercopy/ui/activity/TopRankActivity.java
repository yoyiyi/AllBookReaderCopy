package com.yoyiyi.bookreadercopy.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.BaseActivity;
import com.yoyiyi.bookreadercopy.bean.RankingList;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.component.DaggerFindComponent;
import com.yoyiyi.bookreadercopy.ui.contract.TopRankContract;
import com.yoyiyi.bookreadercopy.ui.presenter.TopRankPresenter;
import com.yoyiyi.bookreadercopy.widget.CustomExpandableListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


/**
 * Created by zzq on 2016/12/19.
 */

public class TopRankActivity extends BaseActivity implements TopRankContract.View {


    @BindView(R.id.elv_male)
    CustomExpandableListView mElvMale;
    @BindView(R.id.elv_female)
    CustomExpandableListView mElvFemale;

    @Inject
    TopRankPresenter mPresenter;

    private List<RankingList.MaleBean> maleGroups = new ArrayList<>();

    private List<List<RankingList.MaleBean>> maleChilds = new ArrayList<>();

    private List<RankingList.MaleBean> femaleGroups = new ArrayList<>();
    private List<List<RankingList.MaleBean>> femaleChilds = new ArrayList<>();


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TopRankActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_top_rank;
    }

    @Override
    public void initWidget() {
        mPresenter.attachView(this);
        mPresenter.getRankList();
    }


    @Override
    public void initToolbar() {
        mToolbar.setTitle("排行榜");
        mToolbar.setNavigationIcon(R.drawable.ic_action_back);
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
        maleGroups.clear();
        femaleGroups.clear();
        updateMale(rankingList);
        updateFemale(rankingList);

    }

    private void updateFemale(RankingList rankingList) {

    }

    private void updateMale(RankingList rankingList) {

    }

    @Override
    public void showError() {
        // hideLoading();
    }

    @Override
    public void complete() {
        // hideLoading();
    }

}
