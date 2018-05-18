package com.secretk.move.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.TopicFragmentRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class TopicFragmentRecyclerAdapter extends RecyclerView.Adapter<TopicFragmentRecyclerHolder> {
    private ItemClickListener mListener;
    private List<SearchedBean.Projects> listNum = new ArrayList<SearchedBean.Projects>();
    private List<SearchedBean.Projects> listName = new ArrayList<SearchedBean.Projects>();

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
        holder.setItemListener(mListener);
        switch (type) {
            case Constants.TOPIC_SORT_BY_NUM:
                holder.setData(listNum, position,type);
                break;
            case Constants.TOPIC_SORT_BY_NAME:
                holder.setData(listName, position,type);
                break;
        }


    }

    @Override
    public int getItemCount() {
        if (type == Constants.TOPIC_SORT_BY_NUM) {
            if (listNum != null) {
                return listNum.size();
            }
            return 0;
        } else {
            if (listName != null) {
                return listName.size();
            }
            return 0;
        }
    }

    int type;

    public void setListType(int type) {
        this.type = type;
    }

    //1-按关注数量倒序；2-按名称排序
    public void setData(List<SearchedBean.Projects> list, int type) {
        this.type=type;
        if (type == Constants.TOPIC_SORT_BY_NUM) {
            this.listNum = list;
        } else if (type == Constants.TOPIC_SORT_BY_NAME) {
            this.listName = list;
        }
        notifyDataSetChanged();
    }

    public List<SearchedBean.Projects> getData() {
        if (type == Constants.TOPIC_SORT_BY_NUM) {
            return listNum;
        }
        return listName;
    }
    public List<SearchedBean.Projects> getDataByType(int dataType) {
        if (dataType == Constants.TOPIC_SORT_BY_NUM) {
            return listNum;
        }
        return listName;
    }
    public void swithData(int type){
        this.type=type;
        notifyDataSetChanged();
    }
}
