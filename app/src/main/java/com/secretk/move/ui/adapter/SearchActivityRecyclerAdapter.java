package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.bean.TopicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.holder.SearchActivityRecyclerHolder;
import com.secretk.move.ui.holder.SearchActivityRecyclerHolder2;
import com.secretk.move.ui.holder.TopicFragmentRecyclerHolder;
import com.secretk.move.utils.PatternUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchActivityRecyclerAdapter extends RecyclerView.Adapter {
    private List<String> list = new ArrayList<String>();
    private ItemClickListener mListener;
    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerViewBaseHolder holder = null;
        switch (viewType){
            case 0:
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_recycler_item, parent, false);
                holder = new SearchActivityRecyclerHolder(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_search_recycler_item2, parent, false);
                holder = new SearchActivityRecyclerHolder2(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case 0:
            case 2:
                SearchActivityRecyclerHolder imgExtraHolder = (SearchActivityRecyclerHolder) holder;
                imgExtraHolder.setItemListener(mListener);
                if (position==0){
                    imgExtraHolder.tvType.setText("历史搜索");
                }else {
                    String str=list.get(position-2);
                    imgExtraHolder.tvType.setText(str);
                }
                break;
            case 1:
                SearchActivityRecyclerHolder2 imgExtraHolder2 = (SearchActivityRecyclerHolder2) holder;
                imgExtraHolder2.setItemListener(mListener);
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
       if (position==0){
           return 0;
       }else if (position==list.size()-1){
           return 1;
       }
        return 2;
    }
    @Override
    public int getItemCount() {
        if (list==null||list.size()==0){
            return 0;
        }
        return list.size()+2;
    }
    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
