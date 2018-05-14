package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.ImageView;


import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/9/21 0021.
 */

public class ImgExtraHorizontalHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_extra)
    public ImageView img_extra;
    public ImgExtraHorizontalHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
