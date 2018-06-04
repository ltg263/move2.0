package com.secretk.move.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.MineAttentionBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MineAttentionHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MineAttentionAdapter extends RecyclerView.Adapter<MineAttentionHolder> {

    private List<MineAttentionBean.DataBean.MyFollowsBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;

    private int type;
    private Context context;
    public MineAttentionAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MineAttentionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_attention, parent, false);
        MineAttentionHolder holder = new MineAttentionHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MineAttentionHolder holder, int position) {
        holder.setItemListener(mListener);
        holder.refresh(position, list.get(position),context);
        holder.setAdapterType(type);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<MineAttentionBean.DataBean.MyFollowsBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<MineAttentionBean.DataBean.MyFollowsBean.RowsBean> getData(){
        return list;
    }

    public void addData(List<MineAttentionBean.DataBean.MyFollowsBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
