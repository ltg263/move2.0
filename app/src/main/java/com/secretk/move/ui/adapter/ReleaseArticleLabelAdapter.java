package com.secretk.move.ui.adapter;


import android.content.Context;
import android.content.Intent;
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
import com.secretk.move.ui.activity.SelectProjectActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class ReleaseArticleLabelAdapter extends RecyclerView.Adapter<ReleaseArticleLabelAdapter.ViewHolder> {
    private SparseArray<DiscussLabelListbean.TagList> list = new SparseArray<>();
    private ItemClickListener mListener;
    private Context mContext;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_label_item2, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int key=list.keyAt(position);
        DiscussLabelListbean.TagList bean = list.get(key);
        holder.setItemListener(mListener);
        if(position==0){
            holder.tvCode.setVisibility(View.VISIBLE);
            holder.tvDown.setVisibility(View.GONE);
            holder.tvCode.setText(bean.getTagName());
        }else{
            holder.tvCode.setVisibility(View.GONE);
            holder.tvDown.setVisibility(View.VISIBLE);
            holder.tvDown.setText(bean.getTagName());
        }
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
    public void amendCode(int projectId,String code) {
        DiscussLabelListbean.TagList beans = new DiscussLabelListbean.TagList();
        beans.setTagId(String.valueOf(projectId));
        beans.setTagName(code);
        list.put(-1,beans);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_project_code)
        public TextView tvCode;
        @BindView(R.id.tv_crack_down)
        public TextView tvDown;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tvCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, SelectProjectActivity.class);
                    intent.putExtra("publication_type",4);
                    mContext.startActivity(intent);
                }
            });
        }
    }


}
