package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.SelectProjectHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class SelectProjectAdapter extends RecyclerView.Adapter<SelectProjectHolder> {
    private List<SearchedBean.Projects> listNum = new ArrayList<>();
    private int projectId;
    @Override
    public SelectProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_project, parent, false);
        SelectProjectHolder holder = new SelectProjectHolder(view);
        return holder;
    }

    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public void onBindViewHolder(SelectProjectHolder holder, int position) {
        holder.refresh(position, listNum ,projectId);
        holder.setItemListener(mListener);
    }

    @Override
    public int getItemCount() {
        return listNum.size();
    }

    public void setData(List<SearchedBean.Projects> list,int projectId) {
        this.listNum = list;
        this.projectId = projectId;
        notifyDataSetChanged();
    }

    public List<SearchedBean.Projects> getData() {
        return listNum;
    }
}
