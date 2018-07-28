package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：發現首頁 item
 */

public class FindNewAdapter extends RecyclerView.Adapter<FindNewAdapter.ImagesHolder> {

    private List<String> lists = new ArrayList<>();
    Context context;
    private int type;
    public FindNewAdapter(Context context,int type) {
        this.context=context;
        this.type=type;
    }

    @Override
    public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_new, parent, false);
        ImagesHolder holder = new ImagesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImagesHolder holder, final int position) {
//        GlideUtils.loadSideMaxImage(context,holder.iv_icon, Constants.BASE_IMG_URL+lists.get(position).getUrl());
//        holder.tv_name.setText("哈哈");
        if(type==1){
            holder.iv_icon_p.setVisibility(View.VISIBLE);
            holder.iv_icon_u.setVisibility(View.GONE);
        }else{
            holder.iv_icon_p.setVisibility(View.GONE);
            holder.iv_icon_u.setVisibility(View.VISIBLE);
        }
        holder.home_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance().show("你惦記了用戶");
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<String> list) {
        this.lists = list;
        notifyDataSetChanged();
    }

    public List<String> getData() {
        return lists;
    }

    class ImagesHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.iv_icon_p)
        ImageView iv_icon_p;
        @BindView(R.id.iv_icon_u)
        ImageView iv_icon_u;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.home_find)
        LinearLayout home_find;
        public ImagesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
