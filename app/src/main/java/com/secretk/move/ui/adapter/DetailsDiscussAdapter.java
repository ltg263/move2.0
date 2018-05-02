package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.DetailsDiscussHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： litongge
 * 时间： 2018/5/2 16:48
 * 邮箱；ltg263@126.com
 * 描述：评测详情——评价Item
 */

public class DetailsDiscussAdapter extends RecyclerView.Adapter<DetailsDiscussHolder> {

    private List<HomeReviewBase> lists = new ArrayList<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public DetailsDiscussHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss, parent, false);
        DetailsDiscussHolder holder = new DetailsDiscussHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DetailsDiscussHolder holder, int position) {
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
