package com.secretk.move.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;

import com.secretk.move.bean.MainRfBean;

import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MainRfFragmentRecyclerHolder;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MainRfFragmentRecyclerAdapter extends RecyclerView.Adapter<MainRfFragmentRecyclerHolder> {
    private List<MainRfBean.Rows> list = new ArrayList<MainRfBean.Rows>();
    private ItemClickListener mListener;
    private int type;
    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MainRfFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_rf_recycler_item, parent, false);
        MainRfFragmentRecyclerHolder holder = new MainRfFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainRfFragmentRecyclerHolder holder, int position) {
        holder.setItemListener(mListener);
        MainRfBean.Rows bean = list.get(position);
        holder.setAdapterType(type);
        holder.setData(bean);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    public void loadHead(List<MainRfBean.Rows> data) {
        if (data == null) return;
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<MainRfBean.Rows> data) {
        if (list == null) return;
        list.addAll(data);
        notifyDataSetChanged();
    }

    public MainRfBean.Rows getDataIndex(int position) {
        return list.get(position);
    }

    public void setAdapterType(int type) {
        this.type=type;
    }

}
