package com.secretk.move.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.SearchTopicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.SearchTopicHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchTopicAdapter extends RecyclerView.Adapter<SearchTopicHolder> {

    private List<SearchTopicBean.DataBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;

    private Context context;

    public SearchTopicAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public SearchTopicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_topic, parent, false);
        SearchTopicHolder holder = new SearchTopicHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchTopicHolder holder, int position) {
        holder.setItemListener(mListener);
        holder.refresh(list.get(position), context);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<SearchTopicBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<SearchTopicBean.DataBean.RowsBean> getData() {
        return list;
    }

    public void addData(List<SearchTopicBean.DataBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
