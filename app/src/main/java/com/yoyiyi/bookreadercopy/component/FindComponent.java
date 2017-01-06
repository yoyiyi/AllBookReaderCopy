package com.yoyiyi.bookreadercopy.component;

import com.yoyiyi.bookreadercopy.ui.activity.SubOtherRankActivity;
import com.yoyiyi.bookreadercopy.ui.activity.SubRankActivity;
import com.yoyiyi.bookreadercopy.ui.activity.TopRankActivity;

import dagger.Component;

/**
 * Created by zzq on 2016/12/19.
 */
@Component(dependencies = AppComponent.class)
public interface FindComponent {

    TopRankActivity inject(TopRankActivity activity);

    SubRankActivity inject(SubRankActivity activity);

    SubOtherRankActivity inject(SubOtherRankActivity activity);
}
