package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.HomeRecommendHolder;
import com.secretk.move.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendHolder> {
    private List<String> list = new ArrayList<String>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public HomeRecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_home_item, parent, false);
        HomeRecommendHolder holder = new HomeRecommendHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(HomeRecommendHolder holder, int position) {
        String currenBean = list.get(position);
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

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return list;
    }
}
