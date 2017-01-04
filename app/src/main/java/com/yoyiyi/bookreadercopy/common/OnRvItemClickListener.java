package com.yoyiyi.bookreadercopy.common;

import android.view.View;

/**
 * RecyclerView Item点击接口
 * Created by zzq on 2016/12/28.
 */

public interface OnRvItemClickListener<T> {
    void onItemClick(View view, int position, T data);
}
