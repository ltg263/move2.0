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

public class MainBlueSkyFragmentHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.img_head)
    public ImageView img_head;

    public MainBlueSkyFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
