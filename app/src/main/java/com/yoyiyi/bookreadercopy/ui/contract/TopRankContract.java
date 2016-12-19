package com.yoyiyi.bookreadercopy.ui.contract;

import com.yoyiyi.bookreadercopy.base.BaseContract;
import com.yoyiyi.bookreadercopy.entities.RankingList;

/**
 * Created by zzq on 2016/12/19.
 */

public interface TopRankContract {
    interface View extends BaseContract.BaseView {
        void showRankList(RankingList rankingList);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getRankList();
    }
}
