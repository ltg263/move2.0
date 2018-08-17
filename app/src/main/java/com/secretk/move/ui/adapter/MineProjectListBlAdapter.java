package com.secretk.move.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MineProjectListBlHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 *
 */

public class MineProjectListBlAdapter extends RecyclerView.Adapter<MineProjectListBlHolder> {

    private List<RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;
    Context context;

    public MineProjectListBlAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MineProjectListBlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_list_unify_bl, parent, false);
        MineProjectListBlHolder holder = new MineProjectListBlHolder(view,context);
        return holder;
    }

    @Override
    public void onBindViewHolder(MineProjectListBlHolder holder, int position) {
        RowsBean bean = list.get(position);
        holder.setData(bean, context);
        holder.setItemListener(mListener);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    public RowsBean getDataIndex(int position) {
        return list.get(position);
    }

}
