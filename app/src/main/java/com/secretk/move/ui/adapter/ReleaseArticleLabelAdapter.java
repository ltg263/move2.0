package com.secretk.move.ui.adapter;


import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.listener.ItemClickListener;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class ReleaseArticleLabelAdapter extends RecyclerView.Adapter<ReleaseArticleLabelAdapter.ViewHolder> {
    private SparseArray<DiscussLabelListbean.TagList> list = new SparseArray<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_label_item2, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int key=list.keyAt(position);
        DiscussLabelListbean.TagList bean = list.get(key);
        holder.setItemListener(mListener);
        holder.tvlabel.setText(bean.getTagName());

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(SparseArray<DiscussLabelListbean.TagList> list) {
        this.list = list;
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
