package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReleasePicAdapter extends RecyclerView.Adapter<ReleasePicAdapter.ViewHolder> {

    private List<String> list = new ArrayList<String>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_release_pic_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str = list.get(position);
        holder.setItemListener(mListener);
        GlideUtils.loadImage(holder.img, str);

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(String str) {
        list.add(str);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerViewBaseHolder {

        @BindView(R.id.img)
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
