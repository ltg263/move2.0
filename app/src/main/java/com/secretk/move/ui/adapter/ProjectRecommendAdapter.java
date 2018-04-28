package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.HomeRecommendHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的项目 列表 item
 */

public class ProjectRecommendAdapter extends RecyclerView.Adapter<HomeRecommendHolder> {


    private List<HomeReviewBase> lists = new ArrayList<>();
    private ItemClickListener mListener;
    public void setItemListener(ItemClickListener mListener){
        this.mListener = mListener;
    }

    @Override
    public HomeRecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item, parent, false);
        HomeRecommendHolder holder = new HomeRecommendHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommendHolder holder, int position) {
        holder.refresh(position, lists);
        holder.setItemListener(mListener);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<HomeReviewBase> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<HomeReviewBase> getData() {
        return lists;
    }
}
