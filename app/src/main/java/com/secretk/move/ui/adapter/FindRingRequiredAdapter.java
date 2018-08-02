package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.FindRingRequiredBean;
import com.secretk.move.ui.holder.FindRingRequiredHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：币圈必读 列表 item
 */

public class FindRingRequiredAdapter extends RecyclerView.Adapter<FindRingRequiredHolder> {

    private List<FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean> lists = new ArrayList<>();
    private Context context;

    public FindRingRequiredAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FindRingRequiredHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_ring_required, parent, false);
        FindRingRequiredHolder holder = new FindRingRequiredHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindRingRequiredHolder holder, int position) {
        holder.refresh(position, lists, context);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean> list) {
        lists.addAll(list);
        notifyDataSetChanged();
    }

    public List<FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean> getData() {
        return lists;
    }
}
