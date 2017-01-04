package com.yoyiyi.bookreadercopy.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yoyiyi.bookreadercopy.R;
import com.yoyiyi.bookreadercopy.base.BaseFragment;
import com.yoyiyi.bookreadercopy.bean.support.FindBean;
import com.yoyiyi.bookreadercopy.common.OnRvItemClickListener;
import com.yoyiyi.bookreadercopy.ui.activity.TopRankActivity;
import com.yoyiyi.bookreadercopy.ui.adapter.FindAdapter;
import com.yoyiyi.bookreadercopy.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzq on 2017/1/4.
 */

public class FindFragment extends BaseFragment implements OnRvItemClickListener<FindBean> {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<FindBean> mList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_find;
    }


    @Override
    public void initWidget() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL, true));
        FindAdapter mAdapter = new FindAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position, FindBean data) {
        switch (position) {
            case 0:
                TopRankActivity.startActivity(activity);
                break;
            case 1:
                TopRankActivity.startActivity(activity);
                break;
            case 2:
                TopRankActivity.startActivity(activity);
                break;
            case 3:
                TopRankActivity.startActivity(activity);
                break;
        }
    }

    @Override
    public void initVariables() {
        mList.clear();
        mList.add(new FindBean("排行榜", R.drawable.home_find_rank));
        mList.add(new FindBean("主题书单", R.drawable.home_find_topic));
        mList.add(new FindBean("分类", R.drawable.home_find_category));
        mList.add(new FindBean("有声小说", R.drawable.home_find_listen));
    }
}
