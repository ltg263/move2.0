package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.bean.TopicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MessageFragmentRecyclerHolder;
import com.secretk.move.ui.holder.TopicFragmentRecyclerHolder;
import com.secretk.move.utils.PatternUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MessageFragmentRecyclerAdapter extends RecyclerView.Adapter<MessageFragmentRecyclerHolder> {
    private List<MessageBean> list = new ArrayList<MessageBean>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public MessageFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_message_recycler_item, parent, false);
        MessageFragmentRecyclerHolder holder = new MessageFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageFragmentRecyclerHolder holder, int position) {
        MessageBean currenBean = list.get(position);
        holder.setItemListener(mListener);
        holder.tvName.setText(currenBean.getName());
        holder.tvLastContent.setText(currenBean.getLastContent());
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<MessageBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<MessageBean> getData(){
        return list;
    }
}
