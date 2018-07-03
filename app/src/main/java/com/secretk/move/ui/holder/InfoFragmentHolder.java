package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;

import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.ui.adapter.InfoFragmentAdapter;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class InfoFragmentHolder extends RecyclerViewBaseHolder {


    public InfoFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final Context context, final int position, final List<MessageBean.DataBean.MessagesBean.RowsBean> lists,
                        final InfoFragmentAdapter adapter) {

    }

}
