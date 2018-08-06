package com.secretk.move.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.ProjectMarketBase;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：项目主页---行情
 */
public class ProjectMarketHolder extends RecyclerViewBaseHolder {
    private final double CNY;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.tv_market_new)
    TextView tvMarketNew;
    @BindView(R.id.tv_market_current)
    TextView tvMarketCurrent;
    @BindView(R.id.tv_market_change)
    TextView tvMarketChange;
    @BindView(R.id.ll_market_not)
    LinearLayout llMarketNot;
    @BindView(R.id.iv_market_change)
    ImageView ivMarketChange;
    public ProjectMarketHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        CNY = Double.valueOf(SharedUtils.singleton().get("EXCHANGE_RATE_CNY",""));
    }

    public void refresh(final int position, List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> lists, final Context context) {
        final ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean usersBean = lists.get(position);
        tvProjectCode.setText(StringUtil.getBeanString(usersBean.getExchangeDisplayName()));
        tvProjectName.setText(StringUtil.getBeanString(usersBean.getCoinpair()));
        //https://data.block.cc/api/v1/ticker?market=okex&symbol_pair=eth_usdt
        //最新价格： usd_rate*last*CNY
        //交易量：base_volume*usd_rate*CNY
        //涨跌幅：change_daily*100
        //2470 BTC：last BTC
        llMarketNot.setVisibility(View.INVISIBLE);
        tvFollowNum.setVisibility(View.GONE);
        tvMarketCurrent.setVisibility(View.GONE);
        if(usersBean.getLast()==0 && usersBean.getUsdRate()==0 && usersBean.getChangeDaily()==0){
            tvMarketNew.setText("暂无");
        }else{
            setBeanData(usersBean, context);
        }
    }

    private void setBeanData(ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean usersBean, Context context) {
        llMarketNot.setVisibility(View.VISIBLE);
        tvFollowNum.setVisibility(View.VISIBLE);
        tvMarketCurrent.setVisibility(View.VISIBLE);

        double jyl = usersBean.getBaseVolume()*usersBean.getUsdRate()*CNY;
        double zxjg = usersBean.getUsdRate()*usersBean.getLast()*CNY;
        String styJyl = "";
        if(jyl<10000){
            styJyl="量"+Math.round(jyl);
        }else if(jyl<100000000){
            styJyl="量"+String.format("%.2f", jyl/10000)+"万";
        }else{
            styJyl="量"+String.format("%.2f", jyl/100000000)+"亿";
        }

        String styZxjg ="￥"+ StringUtil.getYxNum(zxjg);

        if(usersBean.getChangeDaily()<0){
            tvMarketChange.setText(String.format("%.2f", usersBean.getChangeDaily()*100)+"%");
            tvMarketChange.setTextColor(Color.parseColor("#ff4b4b"));
            ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_fall));
        }else{
            tvMarketChange.setText("+"+String.format("%.2f", usersBean.getChangeDaily()*100)+"%");
            tvMarketChange.setTextColor(Color.parseColor("#23b25c"));
            ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_rise));
        }
        tvMarketCurrent.setText(StringUtil.getYxNum(usersBean.getLast())+"  "+usersBean.getCoinpair());
        tvFollowNum.setText(styJyl);
        tvMarketNew.setText(styZxjg);
    }
}
