package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.FindFragmentRecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class FindFragmentRecyclerAdapter extends RecyclerView.Adapter<FindFragmentRecyclerHolder> {
    private ItemClickListener mListener;
    private List<SearchedBean.Projects> listNum = new ArrayList<>();
    private List<SearchedBean.Projects> listName = new ArrayList<>();

    private final Context context;
    public FindFragmentRecyclerAdapter(Context context) {
        this.context= context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public FindFragmentRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_find_recycler_item, parent, false);
        FindFragmentRecyclerHolder holder = new FindFragmentRecyclerHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FindFragmentRecyclerHolder holder, int position) {
        holder.setItemListener(mListener);
        switch (type) {
            case Constants.TOPIC_SORT_BY_NUM:
                holder.setData(listNum, position,type,context);
                break;
            case Constants.TOPIC_SORT_BY_NAME:
                holder.setData(listName, position,type,context);
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
