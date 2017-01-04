package com.yoyiyi.bookreadercopy.api;

import com.yoyiyi.bookreadercopy.bean.RankingList;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Api服务接口
 * Created by zzq on 2016/12/5.
 */

public interface ApiService {
    /**
     * 获取所有排行榜
     *
     * @return
     */
    @GET("/ranking/gender")
    Observable<RankingList> getRanking();
}
