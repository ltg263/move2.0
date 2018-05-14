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

public class MainRfFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_organization)
    public ImageView img_organization;
    @BindView(R.id.tvName)
    public TextView tvName;
    @BindView(R.id.tvIsFollw)
    public TextView tvIsFollw;
    @BindView(R.id.tvTime)
    public TextView tvTime;
    @BindView(R.id.tvTitle)
    public TextView tvTitle;
    @BindView(R.id.tvScore)
    public TextView tvScore;
    @BindView(R.id.tvDesc)
    public TextView tvDesc;
    @BindView(R.id.img_user_head)
    public ImageView img_user_head;
    @BindView(R.id.tvUser)
    public TextView tvUser;
    @BindView(R.id.tvPraise)
    public TextView tvPraise;
    @BindView(R.id.tvComments)
    public TextView tvComments;
    public MainRfFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
