package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class EvaluationSimplenessLabelAdapter extends RecyclerView.Adapter<EvaluationSimplenessLabelAdapter.ViewHolder> {
    private List<DiscussLabelListbean.TagList> list = new ArrayList<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_es_label, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiscussLabelListbean.TagList bean = list.get(position);
        holder.setItemListener(mListener);
        holder.tvlabel.setText(bean.getTagName());
        holder.tvlabel.setSelected(bean.getSelected());
        if (bean.getSelected()){
            holder.tvlabel.setTextColor(UiUtils.getColor(R.color.white));
        }else {
            holder.tvlabel.setTextColor(UiUtils.getColor(R.color.app_background));
        }

    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<DiscussLabelListbean.TagList> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addData(DiscussLabelListbean.TagList str) {
       list.add(1,str);
        notifyDataSetChanged();
    }
    public DiscussLabelListbean.TagList getDataIndex(int index){
        return list.get(index);
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
