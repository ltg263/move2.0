package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainFollowFragmentRecyclerHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.tvscore)
    public TextView tvscore;
    @BindView(R.id.img_organization)
    public ImageView img_organization;
    @BindView(R.id.img_head)
    public ImageView img_head;
    public MainFollowFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
