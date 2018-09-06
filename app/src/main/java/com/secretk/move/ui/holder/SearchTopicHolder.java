package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchTopicBean;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class SearchTopicHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvLastContent)
    TextView tvLastContent;
    @BindView(R.id.ll_topic)
    LinearLayout llTopic;

    public SearchTopicHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void refresh(final SearchTopicBean.DataBean.RowsBean rowsBean, Context context) {
        GlideUtils.loadSideMinImage_76(context,img,Constants.BASE_IMG_URL+StringUtil.getBeanString(rowsBean.getImgPath()));
        tvName.setText(StringUtil.getBeanString(rowsBean.getTagName()));
        tvLastContent.setText(StringUtil.getBeanString(rowsBean.getMemo()));
        llTopic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startTopicActivity(rowsBean.getTagId());
            }
        });
    }
}
