package com.secretk.move.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.SearchUserBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.SearchUserHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserHolder> {

    private List<SearchUserBean.DataBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;

    private Context context;

    public SearchUserAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public SearchUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_user, parent, false);
        SearchUserHolder holder = new SearchUserHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchUserHolder holder, int position) {
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

    public void setData(List<SearchUserBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<SearchUserBean.DataBean.RowsBean> getData() {
        return list;
    }

    public void addData(List<SearchUserBean.DataBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
