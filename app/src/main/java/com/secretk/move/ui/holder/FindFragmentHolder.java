package com.secretk.move.ui.holder;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.FindKwBean;
import com.secretk.move.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class FindFragmentHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_limit)
    TextView tvLimit;
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
    public FindFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.countDownCounters = new SparseArray<>();
    }
    Context mContext;
    public void refresh(Context context, List<FindKwBean.DataBeanX.DataBean.RowsBean> list, int position) {
//         <!--共1000ETH 约 ¥6389.97-->
        FindKwBean.DataBeanX.DataBean.RowsBean rowsBean = list.get(position);
        rowsBean.getBeginDt();//开始时间
        rowsBean.getEndDt();//结束时间
        long surplusTime = StringUtil.getSurplusTime(rowsBean.getBeginDt(),rowsBean.getEndDt());
        tvCode.setText(rowsBean.getProjectCode());
        tvPrice.setText(Html.fromHtml("共<font color=\"#ff4b4b\">"+rowsBean.getTokenCount()+rowsBean.getTokenName()+"</font>约 ¥"+rowsBean.getTokenCash()));
        tvLimit.setText(StringUtil.getBeanString(String.valueOf(rowsBean.getTokenUnclaimed())));
        // status:0,//活动状态：0-未开始，1-进行中，2-已结束，3-已终止,4-已挖完
        if(rowsBean.getStatus()==0){
            tvGo.setVisibility(View.GONE);
            tvSurplus.setText("距开始");
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_red));
            tvTime.setText(StringUtil.getTimeToHms(surplusTime));
        }else if(rowsBean.getStatus()==1){
            tvGo.setSelected(false);
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setText("剩余");
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_code));
            tvTime.setText(StringUtil.getTimeToHms(surplusTime));
        }else if(rowsBean.getStatus()==2){
            tvGo.setSelected(true);
            tvSurplus.setText("已结束");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }else if(rowsBean.getStatus()==3){
            tvGo.setSelected(true);
            tvSurplus.setText("已终止");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }else if(rowsBean.getStatus()==4){
            tvGo.setSelected(true);
            tvSurplus.setText("已挖完");
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
                    tvTime.setText(StringUtil.getTimeToHms(millisUntilFinished));
                }
                public void onFinish() {
                    tvTime.setText( "结束1");
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(tvTime.hashCode(), countDownTimer);
        } else {
            tvTime.setText("结束");
        }


    }

}
