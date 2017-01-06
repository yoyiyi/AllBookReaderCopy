package com.yoyiyi.bookreadercopy.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.BaseActivity;
import com.yoyiyi.bookreadercopy.bean.RankingList;
import com.yoyiyi.bookreadercopy.common.OnRvItemClickListener;
import com.yoyiyi.bookreadercopy.component.AppComponent;
import com.yoyiyi.bookreadercopy.component.DaggerFindComponent;
import com.yoyiyi.bookreadercopy.ui.adapter.TopRankAdapter;
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

    private TopRankAdapter mMaleAdapter;
    private TopRankAdapter mFemaleAdapter;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, TopRankActivity.class));
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
        mToolbar.setNavigationOnClickListener(v -> finish());
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
    public void initVariables() {
        mMaleAdapter = new TopRankAdapter(this, maleGroups, maleChilds);
        mFemaleAdapter = new TopRankAdapter(this, femaleGroups, femaleChilds);

        mElvMale.setAdapter(mMaleAdapter);
        mElvFemale.setAdapter(mFemaleAdapter);

        mMaleAdapter.setItemClickListener(new ClickListener());
        mFemaleAdapter.setItemClickListener(new ClickListener());
    }

    @Override
    public void showRankList(RankingList rankingList) {
        maleGroups.clear();
        femaleGroups.clear();
        updateMale(rankingList);
        updateFemale(rankingList);

    }

    private void updateFemale(RankingList rankingList) {
        List<RankingList.MaleBean> collapse = new ArrayList<>();
        for (RankingList.MaleBean bean : rankingList.female) {
            if (bean.collapse) {    //是否为折叠
                collapse.add(bean);
            } else {
                femaleGroups.add(bean);
                femaleChilds.add(new ArrayList<>());
            }
        }
        if (collapse.size() > 0) {
            femaleGroups.add(new RankingList.MaleBean("别人家的排行榜"));
            femaleChilds.add(collapse);
        }
        mMaleAdapter.notifyDataSetChanged();
    }

    private void updateMale(RankingList rankingList) {
        List<RankingList.MaleBean> collapse = new ArrayList<>();
        for (RankingList.MaleBean bean : rankingList.male) {
            if (bean.collapse) {    //是否为折叠
                collapse.add(bean);
            } else {
                maleGroups.add(bean);
                maleChilds.add(new ArrayList<>());
            }
        }
        if (collapse.size() > 0) {
            maleGroups.add(new RankingList.MaleBean("别人家的排行榜"));
            maleChilds.add(collapse);
        }
        mMaleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        hideLoading();
    }

    @Override
    public void complete() {
        hideLoading();
    }

    private class ClickListener implements OnRvItemClickListener<RankingList.MaleBean> {
        @Override
        public void onItemClick(View view, int position, RankingList.MaleBean data) {
            if (data.monthRank == null) {
                //没有月排行
                SubOtherRankActivity.startActivity(mContext, data._id, data.title);
            } else {
                //有月排行
                SubRankActivity.startActivity(mContext, data._id, data.monthRank, data.totalRank, data.title);
            }

        }
    }
}
