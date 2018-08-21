package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.PicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReleasePicAdapter extends RecyclerView.Adapter<ReleasePicAdapter.ViewHolder> {

    private List<String> list = null;

    private ItemClickListener mListener;
    private Context mContext;

    public ReleasePicAdapter() {
        this.list = new ArrayList<>();
    }


    public void setItemListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_release_pic_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String str = list.get(position);
        holder.setItemListener(mListener);
        if("move".equals(str)){
            GlideUtils.loadUrlDd(mContext, holder.img, R.drawable.ic_poho_bj);
            holder.img_delect.setVisibility(View.GONE);
        }else{
            GlideUtils.loadSideMaxImage(mContext, holder.img, str);
            holder.img_delect.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();

    }

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private LongSparseArray<PicBean> picArray;

    public void addSparseData(LongSparseArray<PicBean> xPicArray) {
        if (xPicArray == null) {
            return;
        }
        this.picArray = xPicArray;
        for (int i = 0; i < picArray.size(); i++) {
            long key = picArray.keyAt(i);
            PicBean bean = picArray.get(key);
            list.add(0,bean.getPath());
        }
        if(list.size()==10){
            list.remove(9);
        }
        notifyDataSetChanged();

    }

    public void addData(String str) {
        list.add(0,str);
        if(list.size()==10){
            list.remove(9);
        }
        notifyDataSetChanged();
    }

    public void removeIndex(int index) {
        list.remove(index);
        if(!list.contains("move")){
            list.add("move");
        }
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return list;
    }

    public class ViewHolder extends RecyclerViewBaseHolder {

        @BindView(R.id.img)
        public ImageView img;
        @BindView(R.id.img_delect)
        public ImageView img_delect;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            img_delect.setOnClickListener(this);
        }
    }

}
