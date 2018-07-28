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
    public void refresh(Context context, List<String> list, int position) {
//         <!--共1000ETH 约 ¥6389.97-->
        tvPrice.setText(Html.fromHtml("共<font color=\"#ff4b4b\">"+"1000ETH"+"</font>约 ¥6389.97"));
        if(position==1){//已挖礦
            tvGo.setSelected(true);
            tvSurplus.setText("已挖完");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }else if(position==2){//据開始
            tvGo.setVisibility(View.GONE);
            tvSurplus.setText("距开始");
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_red));

            tvTime.setText(StringUtil.getTimeToHms(Long.valueOf(list.get(position))));
        }else{//去挖礦
            tvGo.setSelected(false);
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setText("剩余");
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_code));
            tvTime.setText(StringUtil.getTimeToHms(Long.valueOf(list.get(position))));
        }

        CountDownTimer countDownTimer = countDownCounters.get(tvTime.hashCode());
        if (countDownTimer != null) {
            //将复用的倒计时清除
            countDownTimer.cancel();
        }
        long timer = Long.valueOf(list.get(position));
//        timer = timer - System.currentTimeMillis();
        //expirationTime 与系统时间做比较，timer 小于零，则此时倒计时已经结束。
        if (timer > 0) {
            countDownTimer = new CountDownTimer(timer, 1000) {
                public void onTick(long millisUntilFinished) {
                    tvTime.setText(StringUtil.getTimeToHms(millisUntilFinished));
                }
                public void onFinish() {
                    tvTime.setText("00:00:00");
                    tvTime.setText( "结束");
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(tvTime.hashCode(), countDownTimer);
        } else {
            tvTime.setText("00:00:00");
            tvTime.setText("结束");
        }


    }

}
