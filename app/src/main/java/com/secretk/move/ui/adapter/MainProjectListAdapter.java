package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.secretk.move.R;
import com.secretk.move.bean.ProjectByTabBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.holder.MainProjectOneFragmentHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/14.
 */

public class MainProjectListAdapter extends RecyclerView.Adapter<MainProjectOneFragmentHolder> {

    private List<ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean> list = new ArrayList<>();
    private ItemClickListener mListener;
    Context context;

    public MainProjectListAdapter(Context context) {
        this.context = context;
    }

    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public MainProjectOneFragmentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_project_one_item, parent, false);
        MainProjectOneFragmentHolder holder = new MainProjectOneFragmentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MainProjectOneFragmentHolder holder, int position) {
        holder.setItemListener(mListener);
        ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean bean = list.get(position);
        holder.setData(bean, position, context);
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
    public void setAddData(List<ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }
    public void setData(List<ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean getDataIndex(int position) {
        return list.get(position);
    }
}
