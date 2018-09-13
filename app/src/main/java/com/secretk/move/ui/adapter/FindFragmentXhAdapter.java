package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.FindXhBean;
import com.secretk.move.ui.holder.FindFragmentXhHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class FindFragmentXhAdapter extends RecyclerView.Adapter<FindFragmentXhHolder> {

    private List<FindXhBean.DataBean.RowsBean> list = new ArrayList<>();
    private Context context;

    public FindFragmentXhAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FindFragmentXhHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_find_xh_item, parent, false);
        FindFragmentXhHolder holder = new FindFragmentXhHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindFragmentXhHolder holder, int position) {
        holder.refresh(context,list,position,this);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<FindXhBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<FindXhBean.DataBean.RowsBean> getData() {
        return list;
    }

    public void addData(List<FindXhBean.DataBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
