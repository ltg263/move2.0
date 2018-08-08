package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.FindKwBean;
import com.secretk.move.ui.holder.FindFragmentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class FindFragmentAdapter extends RecyclerView.Adapter<FindFragmentHolder> {

    private List<FindKwBean.DataBeanX.DataBean.RowsBean> list = new ArrayList<>();
    private Context context;

    public FindFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FindFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_find_item, parent, false);
        FindFragmentHolder holder = new FindFragmentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindFragmentHolder holder, int position) {
        holder.refresh(context,list,position,this);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<FindKwBean.DataBeanX.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void clearData() {
        list.clear();
        notifyDataSetChanged();
    }

    public List<FindKwBean.DataBeanX.DataBean.RowsBean> getData() {
        return list;
    }

    public void addData(List<FindKwBean.DataBeanX.DataBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
