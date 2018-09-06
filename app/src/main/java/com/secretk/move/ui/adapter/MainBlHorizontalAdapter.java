package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.TopicTagsBase;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：發現首頁 item
 */

public class MainBlHorizontalAdapter extends RecyclerView.Adapter<MainBlHorizontalAdapter.ImagesHolder> {

    private List<TopicTagsBase.DataBean> list;
    Context context;
    public MainBlHorizontalAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_bl_horizontal, parent, false);
        ImagesHolder holder = new ImagesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImagesHolder holder, final int position) {
        GlideUtils.loadSideMinImage_76(context,holder.iv,list.get(position).getImgPath());
        holder.view.setVisibility(View.GONE);
        if(position==0){
            holder.view.setVisibility(View.VISIBLE);
        }
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startTopicActivity(list.get(position).getTagId());
            }
        });
    }
    public  void setData(List<TopicTagsBase.DataBean> head_list){
        this.list=head_list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(list ==null){
            return 0;
        }
        return list.size();
    }




    class ImagesHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.view)
        View view;
        public ImagesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
