package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.InfoNewsBean;
import com.secretk.move.ui.holder.InfoNewsFragmentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class InfoNewsFragmentAdapter extends RecyclerView.Adapter<InfoNewsFragmentHolder> {
    private List<InfoNewsBean.DataBeanX.DataBean.RowsBean> list = new ArrayList<>();
    private Context context;

    public InfoNewsFragmentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public InfoNewsFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_info_item, parent, false);
        InfoNewsFragmentHolder holder = new InfoNewsFragmentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(InfoNewsFragmentHolder holder, int position) {
        holder.refresh(context, position, list,this);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<InfoNewsBean.DataBeanX.DataBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<InfoNewsBean.DataBeanX.DataBean.RowsBean> getData() {
        return list;
    }

    public void addData(List<InfoNewsBean.DataBeanX.DataBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
