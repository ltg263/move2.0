package com.secretk.move.ui.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.SearchCleanHistroyHolder;
import com.secretk.move.ui.holder.SearchHistoryHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchHistoryAdapter extends RecyclerView.Adapter {
    private List<SearchBean> list = new ArrayList<SearchBean>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerViewBaseHolder holder = null;
        switch (viewType) {
            case 0:
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_history_recycler_item, parent, false);
                holder = new SearchHistoryHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_history_recycler_item2, parent, false);
                holder = new SearchCleanHistroyHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchHistoryHolder historyHolderr;
        SearchCleanHistroyHolder cleanHistroyHolder;
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 0:
            case 1:
                historyHolderr = (SearchHistoryHolder) holder;
                historyHolderr.tvType.setText(list.get(position).getSearchTxt());
                historyHolderr = (SearchHistoryHolder) holder;
                historyHolderr.tvType.setText(list.get(position).getSearchTxt());
                if (viewType==0){
                    historyHolderr.tvType.setTextColor(Color.GRAY);
                }else {
                    historyHolderr.tvType.setTextColor(Color.BLACK);
                }
                historyHolderr.setItemListener(mListener);
                break;
            case 2:
                cleanHistroyHolder = (SearchCleanHistroyHolder) holder;
                cleanHistroyHolder.setItemListener(mListener);
                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<SearchBean> xlist) {
        this.list = xlist;
        notifyDataSetChanged();
    }
    public SearchBean getDataInPosition(int position){
        return list.get(position);
    }
}
