package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.TopicFragmentRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class TopicFragmentRecyclerAdapter extends RecyclerView.Adapter<TopicFragmentRecyclerHolder> {
    private List<String> list = new ArrayList<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }


    @Override
    public TopicFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_topic_recycler_item, parent, false);
        TopicFragmentRecyclerHolder holder = new TopicFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicFragmentRecyclerHolder holder, int position) {
        String str = list.get(position);
        holder.tv.setText(str);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
