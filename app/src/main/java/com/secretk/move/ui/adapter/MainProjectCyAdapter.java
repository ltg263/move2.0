package com.secretk.move.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.MainGzBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MainRfFragmentRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MainProjectCyAdapter extends RecyclerView.Adapter<MainRfFragmentRecyclerHolder> {

    private List<MainGzBean.DataBean.FollowsBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;
    Context context;

    public MainProjectCyAdapter(Context context) {
        this.context = context;
    }

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
        MainGzBean.DataBean.FollowsBean.RowsBean bean = list.get(position);
//        holder.setData(bean, context);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<MainGzBean.DataBean.FollowsBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<MainGzBean.DataBean.FollowsBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public MainGzBean.DataBean.FollowsBean.RowsBean getDataIndex(int position) {
        return list.get(position);
    }
}
