package com.yoyiyi.bookreadercopy.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.Constant;
import com.yoyiyi.bookreadercopy.bean.RankingList;
import com.yoyiyi.bookreadercopy.common.OnRvItemClickListener;
import com.yoyiyi.bookreadercopy.widget.glide.help.GlideCircleTransform;

import java.util.List;

/**
 * Created by zzq on 2017/1/5.
 */

public class TopRankAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<RankingList.MaleBean> mGroupArray;
    private List<List<RankingList.MaleBean>> mChildArray;

    private OnRvItemClickListener<RankingList.MaleBean> listener;

    public TopRankAdapter(Context context, List<RankingList.MaleBean> groupArray, List<List<RankingList.MaleBean>> childArray) {
        this.mChildArray = childArray;
        this.mGroupArray = groupArray;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return mGroupArray.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mChildArray.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return mGroupArray.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mChildArray.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        final View group = mInflater.inflate(R.layout.item_top_rank_group, null);
        ImageView ivCover = (ImageView) group.findViewById(R.id.iv_rank_cover);
        TextView tvName = (TextView) group.findViewById(R.id.tv_rank_group_name);
        ImageView ivArrow = (ImageView) group.findViewById(R.id.iv_rank_arrow);
        //collapse
        if (!TextUtils.isEmpty(mGroupArray.get(i).cover)) {
            //Glide 下载图片
            Glide.with(mContext)
                    .load(Constant.IMG_BASE_URL + mGroupArray.get(i).cover)
                    .placeholder(R.drawable.avatar_default)
                    //设置圆形图片
                    .transform(new GlideCircleTransform(mContext))
                    .into(ivCover);
            group.setOnClickListener(v -> {
                //监听点击事件
                if (listener != null)
                    listener.onItemClick(group, i, mGroupArray.get(i));
            });
        } else {
            ivCover.setImageResource(R.drawable.ic_rank_collapse);
        }
        tvName.setText(mGroupArray.get(i).title);

        if (mChildArray.get(i).size() > 0) {
            if (b) {
                ivArrow.setImageResource(R.drawable.rank_arrow_up);
            } else {
                ivArrow.setImageResource(R.drawable.rank_arrow_down);
            }
        } else {
            ivArrow.setVisibility(View.GONE);
        }
        return group;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final View child = mInflater.inflate(R.layout.item_top_rank_child, null);
        TextView tvName = (TextView) child.findViewById(R.id.tv_rank_child_name);
        tvName.setText(mChildArray.get(i).get(i1).title);
        child.setOnClickListener(v -> listener.onItemClick(child, i1, mChildArray.get(i).get(i1)));
        return child;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void setItemClickListener(OnRvItemClickListener<RankingList.MaleBean> listener) {
        this.listener = listener;
    }
}
