package com.yoyiyi.bookreadercopy.ui.presenter;

import com.yoyiyi.bookreadercopy.api.ApiManager;
import com.yoyiyi.bookreadercopy.base.RxPresenter;
import com.yoyiyi.bookreadercopy.ui.contract.TopRankContract;

import javax.inject.Inject;

/**
 * Created by zzq on 2016/12/19.
 */

public class TopRankPresenter extends RxPresenter<TopRankContract.View> implements TopRankContract.Presenter<TopRankContract.View> {
    private ApiManager mApiManager;

    @Inject
    public TopRankPresenter(ApiManager apiManager) {
        this.mApiManager = apiManager;
    }

    @Override
    public void getRankList() {

    }
}
