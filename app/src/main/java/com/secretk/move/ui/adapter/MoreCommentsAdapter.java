package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.listener.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/5/2 16:48
 * 邮箱；ltg263@126.com
 * 描述：讨论详情页 单个评论详情页Item
 */

public class MoreCommentsAdapter extends RecyclerView.Adapter<MoreCommentsAdapter.MoreCommentsHolder> {


    private List<HomeReviewBase> lists = new ArrayList<>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MoreCommentsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_discuss_single, parent, false);
        MoreCommentsHolder holder = new MoreCommentsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MoreCommentsHolder holder, int position) {
        holder.refresh(position, lists);
        holder.setItemListener(mListener);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<HomeReviewBase> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<HomeReviewBase> getData() {
        return lists;
    }

    class MoreCommentsHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.tv_eave_content)
        TextView tvEaveContent;
        public MoreCommentsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void refresh(int position, List<HomeReviewBase> lists) {
            tvEaveContent.setText(lists.get(position).getDiyi());
        }
    }
}
