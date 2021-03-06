package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.ProjectTypeListBean;
import com.secretk.move.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：进度条
 */

public class DiaLogListAdapter extends RecyclerView.Adapter<DiaLogListAdapter.ListHolder> {
    private ItemClickListener mListener;
    private List<ProjectTypeListBean.DataBean.ProjectTypesBean> lists = new ArrayList<>();
    Context context;
    public DiaLogListAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        ListHolder holder = new ListHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, final int position) {
        holder.refresh(position, lists);
        holder.setItemListener(mListener);
    }
    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<ProjectTypeListBean.DataBean.ProjectTypesBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<ProjectTypeListBean.DataBean.ProjectTypesBean> getData() {
        return lists;
    }

    class ListHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        public ListHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<ProjectTypeListBean.DataBean.ProjectTypesBean> lists){
            tvItem.setText(lists.get(position).getProjectTypeName());
        }

    }
}
