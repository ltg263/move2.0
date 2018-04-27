package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.utils.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：我的主页 评分、讨论、文章列表
 */
public class HomeRecommendHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.rl_discuss)
    RelativeLayout rlDiscuss;
    @BindView(R.id.img_organization)
    ImageView imgOrganization;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tvFollws)
    TextView tvFollws;
    @BindView(R.id.tvIsFollw)
    TextView tvIsFollw;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.iv_assist)
    ImageView ivAssist;
    @BindView(R.id.tv_assist)
    TextView tvAssist;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    public HomeRecommendHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<HomeReviewBase> lists) {
        GlideUtils.loadCircle(imgHead, R.drawable.account_portrait);
        GlideUtils.loadCircle(imgOrganization, R.drawable.account_portrait);
        HomeReviewBase base = lists.get(position);
        switch (base.getIndex()){
            case 0:
                tvScore.setVisibility(View.VISIBLE);
                tvIsFollw.setVisibility(View.GONE);
                rlDiscuss.setVisibility(View.GONE);
                break;
            case 1:
                tvScore.setVisibility(View.GONE);
                tvIsFollw.setVisibility(View.VISIBLE);
                rlDiscuss.setVisibility(View.VISIBLE);
                break;
            case 2:
                tvScore.setVisibility(View.GONE);
                tvIsFollw.setVisibility(View.GONE);
                rlDiscuss.setVisibility(View.GONE);
                break;
        }
        if(position==1){
            //tvName.setText(lists.get(position).getDiyi());
            tvHeadTitle.setText(lists.get(position).getEr());
            tvAssist.setSelected(true);
            ivAssist.setSelected(true);
        }else{
            tvName.setText(lists.get(position).getEr());
            tvHeadTitle.setText(lists.get(position).getDiyi());
            tvAssist.setSelected(false);
            ivAssist.setSelected(false);
        }
    }
}
