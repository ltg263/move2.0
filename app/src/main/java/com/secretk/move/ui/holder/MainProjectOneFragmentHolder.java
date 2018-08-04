package com.secretk.move.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.ProjectByTabBean;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainProjectOneFragmentHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.ll_market_yes)
    LinearLayout llMarketYes;
    @BindView(R.id.tv_market_current)
    TextView tvMarketCurrent;
    @BindView(R.id.tv_market_change)
    TextView tvMarketChange;
    @BindView(R.id.tv_market_not)
    TextView tvMarketNot;
    @BindView(R.id.iv_market_change)
    ImageView ivMarketChange;
    @BindView(R.id.tv_total_score)
    TextView tvTotalScore;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;

    public MainProjectOneFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void setData(final ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean bean, int position, final Context context) {
        GlideUtils.loadCircleProjectUrl(context,ivHeadImg, Constants.BASE_IMG_URL+bean.getProjectIcon());
        tvProjectCode.setText(StringUtil.getBeanString(bean.getProjectCode()));
        tvProjectName.setText("/"+StringUtil.getBeanString(bean.getProjectChineseName()));
        tvFollowNum.setText(bean.getFollowerNum()+"关注");
        tvTotalScore.setText(String.valueOf(bean.getTotalScore()));
        //0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
        if(bean.getFollowStatus() == 1){
            tvFollowStatus.setSelected(true);
            tvFollowStatus.setText(context.getResources().getString(R.string.follow_status_1));
        }else{
            tvFollowStatus.setSelected(false);
            tvFollowStatus.setText(context.getResources().getString(R.string.follow_status_0));
        }
        double price = bean.getPrice();
        double percent_change_24h = bean.getPercent_change_24h();
        if(price<0.0){
            tvMarketNot.setText("暂无");
            tvMarketNot.setVisibility(View.VISIBLE);
            llMarketYes.setVisibility(View.GONE);
        }else {
            tvMarketNot.setVisibility(View.GONE);
            llMarketYes.setVisibility(View.VISIBLE);
            tvMarketCurrent.setText(StringUtil.getYxNum(price));
            if (percent_change_24h < 0) {
                tvMarketChange.setText(percent_change_24h + "%");
                tvMarketChange.setTextColor(Color.parseColor("#ff4b4b"));
                ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_fall));
            } else {
                tvMarketChange.setText("+" + percent_change_24h + "%");
                tvMarketChange.setTextColor(Color.parseColor("#23b25c"));
                ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_rise));
            }
        }




//     collect.finishCommendation("￥"+StringUtil.getYxNum(price),String.format("%.2f", percent_change_24h),true);

//        NetUtil.getCoinmarketcapTicker(String.valueOf(bean.getCmcId()), "CNY", new NetUtil.SaveCommendationImp() {
//            @Override
//            public void finishCommendation(String commendationNum, String donateNum, boolean status) {
//                if(status){
//                    tvMarketCurrent.setText(commendationNum);
//                    if(!donateNum.contains("-")){
//                        tvMarketChange.setText("+"+donateNum+"%");
//                        tvMarketChange.setTextColor(Color.parseColor("#23b25c"));
//                        ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_rise));
//                    }else{
//                        tvMarketChange.setText(donateNum+"%");
//                        tvMarketChange.setTextColor(Color.parseColor("#ff4b4b"));
//                        ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_fall));
//                    }
//                }else{
//                    tvMarketChange.setText("暂无");
//                    llMarketNot.setVisibility(View.GONE);
//                }
//            }
//        });

        tvFollowStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.PROJECT, bean.getProjectId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
            }
        });
    }
}
