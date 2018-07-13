package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.ui.holder.MineCollectHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的收藏 列表 item
 */

public class MineCollectAdapter extends RecyclerView.Adapter<MineCollectHolder> {


    private List<RowsBean> lists = new ArrayList<>();
    private Context context;

    public MineCollectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MineCollectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list_unify, parent, false);
        MineCollectHolder holder = new MineCollectHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MineCollectHolder holder, int position) {
        holder.refresh(position, lists, context);
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
