package com.yoyiyi.bookreadercopy.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.bean.support.FindBean;
import com.yoyiyi.bookreadercopy.common.OnRvItemClickListener;

import java.util.List;

/**
 * Created by zzq on 2017/1/4.
 */

public class FindAdapter extends BaseQuickAdapter<FindBean, BaseViewHolder> {
    private OnRvItemClickListener mListener;

    public FindAdapter(List<FindBean> data, OnRvItemClickListener listener) {
        super(R.layout.item_find, data);
        this.mListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper, FindBean item) {
        helper.setText(R.id.tv_title, item.title)
                .setText(R.id.iv_icon, item.iconResId);
        helper.convertView.setOnClickListener(
                v -> mListener.onItemClick(helper.convertView, helper.getAdapterPosition(), item));
    }
}
