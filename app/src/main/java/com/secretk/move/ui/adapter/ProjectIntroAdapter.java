package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.ProjectHomeBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.ProjectIntroHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的主页 列表 item
 */

public class ProjectIntroAdapter extends RecyclerView.Adapter<ProjectIntroHolder> {

    private List<ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean> lists = new ArrayList<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public ProjectIntroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_project_intro_item, parent, false);
        ProjectIntroHolder holder = new ProjectIntroHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectIntroHolder holder, int position) {
        holder.refresh(position, lists);
        holder.setItemListener(mListener);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<ProjectHomeBean.DataBean.ProjectBean.ActiveUsersBean> getData() {
        return lists;
    }
}
