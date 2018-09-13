package com.secretk.move.ui.holder;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindKwBean;
import com.secretk.move.bean.FindXhBean;
import com.secretk.move.ui.activity.FindWkDetailsActivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.FindFragmentAdapter;
import com.secretk.move.ui.adapter.FindFragmentXhAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class FindFragmentXhHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.ll_go)
    LinearLayout llGo;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_go)
    TextView tvGo;
    @BindView(R.id.tv_surplus)
    TextView tvSurplus;
    @BindView(R.id.tv_time)
    TextView tvTime;
    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<CountDownTimer> countDownCounters;
    public FindFragmentXhHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.countDownCounters = new SparseArray<>();
    }
    Context mContext;
    public void refresh(final Context context, List<FindXhBean.DataBean.RowsBean> list, int position, final FindFragmentXhAdapter findFragmentAdapter) {
//         <!--共1000ETH 约 ¥6389.97-->
        final FindXhBean.DataBean.RowsBean rowsBean = list.get(position);
        long surplusTime = 0;
        GlideUtils.loadCircleProjectUrl(context,ivIcon, Constants.BASE_IMG_URL+StringUtil.getBeanString(rowsBean.getProjectIcon()));
        tvTitle.setText(StringUtil.getBeanString(rowsBean.getPostTitle()));
        tvPrice.setText(Html.fromHtml("共<font color=\"#ff4b4b\">"+rowsBean.getRewardMoney()+"</font>"));
        // status:/悬赏的状态：0-进行中，1-已结束，2-已撤销
        if(rowsBean.getState()==0){
            surplusTime = StringUtil.getSurplusTime("0",StringUtil.getTimeToYMDHms(rowsBean.getEndTime()));
            tvGo.setSelected(false);
            tvGo.setVisibility(View.VISIBLE);
            tvGo.setText("去挖矿");
            tvSurplus.setText("剩余");
            tvSurplus.setVisibility(View.VISIBLE);
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_code));
            tvTime.setVisibility(View.VISIBLE);
            tvTime.setText(StringUtil.getTimeToHms(surplusTime));
        }else if(rowsBean.getState()==2){
            tvGo.setSelected(true);
            tvGo.setText("已撤销");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }else if(rowsBean.getState()==1){
            tvGo.setSelected(true);
            tvGo.setText("已结束");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }

        CountDownTimer countDownTimer = countDownCounters.get(tvTime.hashCode());
        if (countDownTimer != null) {
            //将复用的倒计时清除
            countDownTimer.cancel();
        }
//        timer = timer - System.currentTimeMillis();
        //expirationTime 与系统时间做比较，timer 小于零，则此时倒计时已经结束。
        if (surplusTime > 0) {
            countDownTimer = new CountDownTimer(surplusTime, 1000) {
                public void onTick(long millisUntilFinished) {
//                    tvTime.setText(StringUtil.getTimeToHms(millisUntilFinished));
                    tvTime.setText(StringUtil.getSytime(millisUntilFinished));
                }
                public void onFinish() {
                    // status:/悬赏的状态：0-进行中，1-已结束，2-已撤销
                    if(rowsBean.getState()==0){
                        rowsBean.setState(1);
                        tvGo.setSelected(true);
                        tvGo.setText("已结束");
                        tvGo.setVisibility(View.VISIBLE);
                        tvSurplus.setVisibility(View.GONE);
                        tvTime.setVisibility(View.GONE);
                    }
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(tvTime.hashCode(), countDownTimer);
        }
        llGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt() || StringUtil.isBlank(SharedUtils.getToken())){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.go2DetailsByType(10,String.valueOf(rowsBean.getPostId()));

            }
        });
    }

}
