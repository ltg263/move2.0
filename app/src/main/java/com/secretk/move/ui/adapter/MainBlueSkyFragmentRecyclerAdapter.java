package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.BlueSkyBean;
import com.secretk.move.bean.MainRfBean;
import com.secretk.move.listener.ItemClickListener;

import com.secretk.move.ui.holder.MainBlueSkyFragmentHolder;
import com.secretk.move.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MainBlueSkyFragmentRecyclerAdapter extends RecyclerView.Adapter<MainBlueSkyFragmentHolder> {
    private List<BlueSkyBean.RankList> list = new ArrayList<BlueSkyBean.RankList>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MainBlueSkyFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_bluesky_recycler_item, parent, false);
        MainBlueSkyFragmentHolder holder = new MainBlueSkyFragmentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainBlueSkyFragmentHolder holder, int position) {
        holder.setItemListener(mListener);
        BlueSkyBean.RankList  bean=list.get(position);
        holder.setData(bean,position);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<BlueSkyBean.RankList> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public BlueSkyBean.RankList getDataIndex(int position){
        return list.get(position);
    }
}
