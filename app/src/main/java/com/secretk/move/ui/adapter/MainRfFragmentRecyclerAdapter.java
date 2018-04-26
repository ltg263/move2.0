package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.returnbean.MainRfBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MainFollowFragmentRecyclerHolder;
import com.secretk.move.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MainRfFragmentRecyclerAdapter extends RecyclerView.Adapter<MainFollowFragmentRecyclerHolder> {
    private List<MainRfBean.Rows> list = new ArrayList<MainRfBean.Rows>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MainFollowFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_rf_recycler_item, parent, false);
        MainFollowFragmentRecyclerHolder holder = new MainFollowFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainFollowFragmentRecyclerHolder holder, int position) {

        String currenBean = "你好";
        holder.setItemListener(mListener);
        holder.tvscore.setText(currenBean);
        GlideUtils.loadCircle(holder.img_head, R.drawable.account_portrait);
        GlideUtils.loadCircle(holder.img_organization, R.drawable.account_portrait);

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }


    public void loadHead(List<MainRfBean.Rows> data) {
        if (data == null) return;
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<MainRfBean.Rows> data) {
        if (list == null) return;
        list.addAll(data);
        notifyDataSetChanged();
    }

}
