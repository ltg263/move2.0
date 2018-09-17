package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.SearchProjectBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.SearchProjectHolder;

import java.util.ArrayList;
import java.util.List;
/**

 * Created by zc on 2018/4/14.
 */

public class SearchProjectAdapter extends RecyclerView.Adapter<SearchProjectHolder> {
    private ItemClickListener mListener;
    private List<SearchProjectBean.DataBean.RowsBean> list= new ArrayList<>();

    private final Context context;

    public SearchProjectAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public SearchProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_find_recycler_item, parent, false);
        SearchProjectHolder holder = new SearchProjectHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchProjectHolder holder, int position) {
        holder.setItemListener(mListener);
        holder.setData(list, position, context);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    //1-按关注数量倒序；2-按名称排序
    public void setData(List<SearchProjectBean.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void setAddData(List<SearchProjectBean.DataBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public List<SearchProjectBean.DataBean.RowsBean> getData() {
        return list;
    }
}
