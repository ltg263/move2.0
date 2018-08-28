package com.secretk.move.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.activity.ImageViewVpAcivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.SharedUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 19:51
 * 邮箱；ltg263@126.com
 * 描述：图片九宫格的ITEM
 */

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesHolder> {

    private List<PostDataInfo> lists = new ArrayList<>();
    Context context;
    private boolean isNull = false;
    private int postId = 0;
    private int postType = 0;
    public ImagesAdapter(Context context) {
        this.context=context;
    }

    @Override
    public ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_item, parent, false);
        ImagesHolder holder = new ImagesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImagesHolder holder, final int position) {
        if(lists.get(position)==null){
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(postId == 0){
                        return;
                    }
                    if (SharedUtils.getLoginZt()) {
                        IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
                    } else {
                        IntentUtil.startActivity(LoginHomeActivity.class);
                    }
                }
            });
            return;
        }
        GlideUtils.loadSideMinImage(context,holder.img, Constants.BASE_IMG_URL+lists.get(position).getUrl());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNull){
                    lists.remove(2);
                }
                Intent intent = new Intent(context,ImageViewVpAcivity.class);
                intent.putParcelableArrayListExtra("lists", (ArrayList<? extends Parcelable>) lists);
                intent.putExtra("position",position);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public void setData(List<PostDataInfo> list) {
        if(list.size()==4){
            isNull=true;
            list.add(2,null);
        }
        this.lists = list;
        notifyDataSetChanged();
    }
    public void setBean(int postId,int postType){
        this.postId = postId;
        this.postType = postType;
    }

    public List<PostDataInfo> getData() {
        return lists;
    }

    class ImagesHolder extends RecyclerViewBaseHolder {
        @BindView(R.id.img)
        ImageView img;
        public ImagesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
