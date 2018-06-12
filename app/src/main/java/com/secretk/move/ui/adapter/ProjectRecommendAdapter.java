package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.ui.holder.ProjectListHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的项目 列表 item
 */

public class ProjectRecommendAdapter extends RecyclerView.Adapter<ProjectListHolder> {


    private List<RowsBean> lists = new ArrayList<>();
    private Context context;
    public ProjectRecommendAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ProjectListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item, parent, false);
        ProjectListHolder holder = new ProjectListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectListHolder holder, int position) {
        holder.refresh(position, lists,context);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }
    public void setAddData(List<RowsBean> list) {
        lists.addAll(list);
        notifyDataSetChanged();
    }

    public List<RowsBean> getData() {
        return lists;
    }
}
