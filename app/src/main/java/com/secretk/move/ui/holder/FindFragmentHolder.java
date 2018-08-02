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
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindKwBean;
import com.secretk.move.ui.activity.FindWkDetailsActivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.FindFragmentAdapter;
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
    public void refresh(final Context context, List<FindKwBean.DataBeanX.DataBean.RowsBean> list, int position, final FindFragmentAdapter findFragmentAdapter) {
//         <!--共1000ETH 约 ¥6389.97-->
        final FindKwBean.DataBeanX.DataBean.RowsBean rowsBean = list.get(position);
        long surplusTime = 0;
        rowsBean.getBeginDt();//开始时间
        rowsBean.getEndDt();//结束时间

        GlideUtils.loadCircleProjectUrl(context,ivIcon, Constants.BASE_IMG_URL+StringUtil.getBeanString(rowsBean.getProjectIcon()));
        tvCode.setText(rowsBean.getProjectCode());
        tvPrice.setText(Html.fromHtml("共<font color=\"#ff4b4b\">"+rowsBean.getTokenCount()+rowsBean.getTokenUnclaimed()+"</font>约 ¥"+rowsBean.getTokenCash()));
        tvName.setText("/"+StringUtil.getBeanString(rowsBean.getProjectChineseName()));
        tvLimit.setText("限量"+StringUtil.getBeanString(String.valueOf(rowsBean.getTokenCount()))+"份");
        // status:0,//活动状态：0-未开始，1-进行中，2-已结束，3-已终止,4-已挖完
        if(rowsBean.getStatus()==0){
            surplusTime = StringUtil.getSurplusTime(rowsBean.getBeginDt(),"0");
            tvGo.setVisibility(View.GONE);
            tvSurplus.setText("距开始");
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_red));
            tvTime.setText(StringUtil.getTimeToHms(surplusTime));
        }else if(rowsBean.getStatus()==1){
            surplusTime = StringUtil.getSurplusTime("0",rowsBean.getEndDt());
            tvGo.setSelected(false);
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setText("剩余");
            tvSurplus.setTextColor(context.getResources().getColor(R.color.theme_title_code));
            tvTime.setText(StringUtil.getTimeToHms(surplusTime));
        }else if(rowsBean.getStatus()==2){
            tvGo.setSelected(true);
            tvGo.setText("已结束");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }else if(rowsBean.getStatus()==3){
            tvGo.setSelected(true);
            tvGo.setText("已终止");
            tvGo.setVisibility(View.VISIBLE);
            tvSurplus.setVisibility(View.GONE);
            tvTime.setVisibility(View.GONE);
        }else if(rowsBean.getStatus()==4){
            tvGo.setSelected(true);
            tvGo.setText("已挖完");
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
                    // status:0,//活动状态：0-未开始，1-进行中，2-已结束，3-已终止,4-已挖完
                    if(rowsBean.getStatus()==0){
                        rowsBean.setStatus(1);
                    }else if(rowsBean.getStatus()==1){
                        rowsBean.setStatus(2);
                    }
                    findFragmentAdapter.notifyDataSetChanged();
                }
            }.start();
            //将此 countDownTimer 放入list.
            countDownCounters.put(tvTime.hashCode(), countDownTimer);
        }
        tvGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt() || StringUtil.isBlank(SharedUtils.getToken())){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                String key[] = {"id"};
                String values[] = {String.valueOf(rowsBean.getId())};
                IntentUtil.startActivity(FindWkDetailsActivity.class,key,values);
            }
        });
    }
//    public String getSytime(long date) {
//        long day = date / (1000 * 60 * 60 * 24);
//        long hour = (date / (1000 * 60 * 60) - day * 24);
//        long min = ((date / (60 * 1000)) - day * 24 * 60 - hour * 60);
//        long s = (date/1000 - day*24*60*60 - hour*60*60 - min*60);
//        return day+"天"+hour+"小时"+min+"分"+s+"秒";
//    }

}
