package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.ProjectMarketBase;
import com.secretk.move.ui.holder.ProjectMarketHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的项目 行情列表 item
 */

public class ProjectMarketAdapter extends RecyclerView.Adapter<ProjectMarketHolder> {



    private List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> lists = new ArrayList<>();
    private Context context;

    public ProjectMarketAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ProjectMarketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project_market_f, parent, false);
        ProjectMarketHolder holder = new ProjectMarketHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectMarketHolder holder, int position) {
        holder.refresh(position, lists, context);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> list) {
        lists.addAll(list);
        notifyDataSetChanged();
    }

    public List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> getData() {
        return lists;
    }
}
