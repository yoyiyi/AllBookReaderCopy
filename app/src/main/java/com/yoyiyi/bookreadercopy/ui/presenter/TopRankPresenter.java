package com.yoyiyi.bookreadercopy.ui.presenter;

import com.orhanobut.logger.Logger;
import com.yoyiyi.bookreadercopy.api.ApiManager;
import com.yoyiyi.bookreadercopy.base.RxPresenter;
import com.yoyiyi.bookreadercopy.entities.RankingList;
import com.yoyiyi.bookreadercopy.ui.contract.TopRankContract;
import com.yoyiyi.bookreadercopy.utils.RxUtil;
import com.yoyiyi.bookreadercopy.utils.StringUtils;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

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
        String key = StringUtils.creatAcacheKey("book-ranking-list");
        Observable<RankingList> observable = mApiManager.getRanking()
                .compose(RxUtil.<RankingList>rxCacheBeanHelper(key));
        Subscription subscription = Observable
                .concat(RxUtil.rxCreateDiskObservable(key, RankingList.class), observable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RankingList>() {
                    @Override
                    public void onCompleted() {
                        mView.complete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d(e.toString());
                        mView.complete();
                    }

                    @Override
                    public void onNext(RankingList data) {
                        if (data != null && mView != null) {
                            mView.showRankList(data);
                        }
                    }
                });
        addSubscribe(subscription);
    }
}
