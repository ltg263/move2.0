package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MessageFragmentRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MessageFragmentRecyclerAdapter extends RecyclerView.Adapter<MessageFragmentRecyclerHolder> {
    private List<MessageBean.DataBean.MessagesBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;
    private Context context;
    public MessageFragmentRecyclerAdapter(Context context) {
        this.context=context;
    }

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
        holder.refresh(context,position,list,this);
        holder.setItemListener(mListener);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<MessageBean.DataBean.MessagesBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<MessageBean.DataBean.MessagesBean.RowsBean> getData(){
        return list;
    }

    public void addData(List<MessageBean.DataBean.MessagesBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
