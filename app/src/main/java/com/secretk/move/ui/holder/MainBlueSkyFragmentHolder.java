package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.BlueSkyBean;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainBlueSkyFragmentHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.img_head)
    ImageView img_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_code)
    TextView tv_code;

    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.tv_follow)
    TextView tv_follow;
    @BindView(R.id.tv_project_folly)
    TextView tvProjectFolly;

    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.img_order)
    ImageView img_order;

    public MainBlueSkyFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvProjectFolly.setOnClickListener(this);
    }
    public void setData(final BlueSkyBean.RankList bean, int position, Context context) {
        GlideUtils.loadCircleUserUrl(context,img_head, Constants.BASE_IMG_URL + bean.getProjectIcon());
        tv_code.setText(bean.getProjectCode());
        tv_name.setText(" /" + bean.getProjectChineseName());
        tv_content.setText(bean.getProjectSignature());
        tv_score.setText(bean.getTotalScore());
        tv_follow.setText(bean.getFollowerNum());
        tv_order.setVisibility(View.VISIBLE);
        bean.setPosition(position + 1 + "");
        tv_order.setText(bean.getPosition());
        if (!SharedUtils.getLoginZt()) {
            tvProjectFolly.setSelected(false);
            tvProjectFolly.setText(context.getResources().getString(R.string.follow_status_0));
        } else {
            if(bean.getFollowStatus() == 1){
                tvProjectFolly.setSelected(true);
                tvProjectFolly.setText(context.getResources().getString(R.string.follow_status_1));
            }else{
                tvProjectFolly.setSelected(false);
                tvProjectFolly.setText(context.getResources().getString(R.string.follow_status_0));
            }
        }
        tvProjectFolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SharedUtils.getLoginZt()) {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                } else {
                    tvProjectFolly.setEnabled(false);
                    NetUtil.addSaveFollow(tvProjectFolly,
                            Constants.SaveFollow.PROJECT, bean.getProjectId(), new NetUtil.SaveFollowImp() {
                                @Override
                                public void finishFollow(String str) {
                                    tvProjectFolly.setEnabled(true);
                                    if(!str.equals(Constants.FOLLOW_ERROR)){
                                        tvProjectFolly.setText(str);
                                    }
                                }
                            });
                }
            }
        });
        switch (position) {
            case 0:
                tv_order.setVisibility(View.INVISIBLE);
                img_order.setVisibility(View.VISIBLE);
                img_order.setBackgroundResource(R.drawable.topic_one);
                break;
            case 1:
                tv_order.setVisibility(View.INVISIBLE);
                img_order.setVisibility(View.VISIBLE);
                img_order.setBackgroundResource(R.drawable.topic_two);
                break;
            case 2:
                tv_order.setVisibility(View.INVISIBLE);
                img_order.setVisibility(View.VISIBLE);
                img_order.setBackgroundResource(R.drawable.topic_three);
                break;
            default:
                img_order.setVisibility(View.INVISIBLE);
                tv_order.setVisibility(View.VISIBLE);
                break;
        }
    }
}
