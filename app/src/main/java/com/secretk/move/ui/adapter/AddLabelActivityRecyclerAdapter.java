package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;

import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MessageFragmentRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class AddLabelActivityRecyclerAdapter extends RecyclerView.Adapter<AddLabelActivityRecyclerAdapter.ViewHolder> {
    private List<String> list = new ArrayList<String>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_label_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String  str = list.get(position);
        holder.setItemListener(mListener);
        holder.tvlabel.setText(str);

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
    public void addData(String str) {
       list.add(1,str);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerViewBaseHolder {

        @BindView(R.id.tvlabel)
        public TextView tvlabel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
