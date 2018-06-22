package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.MyFansList;
import com.secretk.move.ui.holder.MineFansHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：我的收藏 列表 item
 */

public class HomeFansAdapter extends RecyclerView.Adapter<MineFansHolder> {


    private List<MyFansList.DataBean.MyFansBean.RowsBean> lists = new ArrayList<>();
    private Context context;

    public HomeFansAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MineFansHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mine_attention, parent, false);
        MineFansHolder holder = new MineFansHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MineFansHolder holder, int position) {
        holder.refresh(lists.get(position), context);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<MyFansList.DataBean.MyFansBean.RowsBean> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public void setAddData(List<MyFansList.DataBean.MyFansBean.RowsBean> list) {
        lists.addAll(list);
        notifyDataSetChanged();
    }

    public List<MyFansList.DataBean.MyFansBean.RowsBean> getData() {
        return lists;
    }
}
