package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：项目主页---简介ListViewItem
 */
public class ProjectIntroHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tvIsFollw)
    TextView tvIsFollw;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_content)
    TextView tvContent;
    public ProjectIntroHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<HomeReviewBase> lists) {
        GlideUtils.loadCircle(imgHead, R.drawable.account_portrait);
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BaseManager.app, "关注", Toast.LENGTH_SHORT).show();
            }
        });
        HomeReviewBase base = lists.get(position);
        tvName.setText(base.getDiyi());
    }
}
