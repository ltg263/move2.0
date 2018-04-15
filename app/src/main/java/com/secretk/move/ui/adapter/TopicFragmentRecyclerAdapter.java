package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.TopicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.TopicFragmentRecyclerHolder;
import com.secretk.move.utils.PatternUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class TopicFragmentRecyclerAdapter extends RecyclerView.Adapter<TopicFragmentRecyclerHolder> {
    private List<TopicBean> list = new ArrayList<TopicBean>();
    private ItemClickListener mListener;

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public TopicFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_topic_recycler_item, parent, false);
        TopicFragmentRecyclerHolder holder = new TopicFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TopicFragmentRecyclerHolder holder, int position) {
        TopicBean currenBean = list.get(position);
        holder.setItemListener(mListener);
        holder.tvName.setText(currenBean.getName());



        if (position == 0) {
            if (PatternUtils.isLetter(currenBean.getSpell()
                    .subSequence(0, 1).toString())) {
                holder.tvSpell.setText( currenBean.getSpell()
                        .subSequence(0, 1).toString().toUpperCase());
            } else {
                holder.tvSpell.setText( "#");
            }
        } else {
            TopicBean lastBean=list.get(position-1);
            boolean b =lastBean.getSpell().subSequence(0, 1)
                    .equals(currenBean.getSpell().subSequence(0, 1));
            if (b) {
                holder.tvSpell.setVisibility(View.GONE);
            } else if (PatternUtils.isLetter(currenBean.getSpell()
                    .subSequence(0, 1).toString())
                    && b == false) {
                holder.tvSpell.setVisibility(View.VISIBLE);
                holder.tvSpell.setText( currenBean.getSpell()
                        .subSequence(0, 1).toString().toUpperCase());
            } else if (PatternUtils.isLetter(currenBean.getSpell()
                    .subSequence(0, 1).toString()) == false
                    && b == false) {
                holder.tvSpell.setVisibility(View.VISIBLE);
            }
        }


    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<TopicBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public List<TopicBean> getData(){
        return list;
    }
}
