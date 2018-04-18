package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchActivityRecyclerHolder2 extends RecyclerViewBaseHolder {

    @BindView(R.id.tvClean)
    public TextView tvClean;

    public SearchActivityRecyclerHolder2(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
