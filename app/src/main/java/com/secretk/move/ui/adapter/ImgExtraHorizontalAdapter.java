package com.secretk.move.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MainRfBean;
import com.secretk.move.ui.holder.ImgExtraHorizontalHolder;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.UiUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21 0021.
 * <p>
 * <p>
 * 横向的 多个图片的适配器
 * <p>
 * 在新闻布局里面也许有多个图片
 * 这些图片是横向的 所以写了这个横向的适配器
 */

public class ImgExtraHorizontalAdapter extends RecyclerView.Adapter {
  private   List<MainRfBean.PostSmallImagesList> list;


    private ImgExtraHorizontalHolder horizontalHolder;
    private Context mContext;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.imgextra_horizontal_item, parent, false);
        horizontalHolder = new ImgExtraHorizontalHolder(view);
        return horizontalHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ImgExtraHorizontalHolder horizontalHolder = (ImgExtraHorizontalHolder) holder;
        String str = Constants.BASE_IMG_URL + list.get(position).getFileUrl();
        ImageView img = horizontalHolder.img_extra;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = UiUtils.getNewsPicSize()[0];
        params.height = UiUtils.getNewsPicSize()[1];
        img.setLayoutParams(params);
        img.setPadding(0, 0, UiUtils.dip2px(5), 0);
        GlideUtils.loadImage(img,str);

    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public void setData(MainRfBean.Rows data) {
        this.list =  data.getPostSmallImagesList();
        notifyDataSetChanged();

    }
}
