package com.secretk.move.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.bean.ProjectMarketBase;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
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
        tvFollowNum.setVisibility(View.INVISIBLE);
        tvMarketCurrent.setVisibility(View.GONE);
        if(usersBean.isOk()){
            if(StringUtil.isNotBlank(usersBean.getStrError())){
                tvMarketNew.setText(usersBean.getStrError());
            }else {
                setBeanData(usersBean, context);
            }
        }else{
            getHttpsData(usersBean,context);
        }
    }

    private void setBeanData(ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean usersBean, Context context) {
        llMarketNot.setVisibility(View.VISIBLE);
        tvFollowNum.setVisibility(View.VISIBLE);
        tvMarketCurrent.setVisibility(View.VISIBLE);

        double jyl = usersBean.getBase_volume()*usersBean.getUsd_rate()*CNY;
        double zxjg = usersBean.getUsd_rate()*usersBean.getLast()*CNY;
        String styJyl = "";
        String styZxjg = "";
        if(jyl<10000){
            styJyl="量"+Math.round(jyl);
        }else if(jyl<100000000){
            styJyl="量"+String.format("%.2f", jyl/10000)+"万";
        }else{
            styJyl="量"+String.format("%.2f", jyl/100000000)+"亿";
        }

        if(zxjg>=1000){
            styZxjg="￥"+String.format("%.2f", zxjg);
        }else if(zxjg>=1){
            styZxjg="￥"+String.format("%.3f", zxjg);
        }else{
            styZxjg="￥"+String.valueOf(BigDecimal.valueOf(zxjg));
        }
        if(usersBean.getChange_daily()<0){
            tvMarketChange.setText(String.format("%.2f", usersBean.getChange_daily()*100)+"%");
            tvMarketChange.setTextColor(Color.parseColor("#ff4b4b"));
            ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_fall));
        }else{
            tvMarketChange.setText("+"+String.format("%.2f", usersBean.getChange_daily()*100)+"%");
            tvMarketChange.setTextColor(Color.parseColor("#23b25c"));
            ivMarketChange.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_price_rise));
        }
        tvMarketCurrent.setText(usersBean.getLast()+"  "+usersBean.getMainCode());
        tvFollowNum.setText(styJyl);
        tvMarketNew.setText(styZxjg);
    }

    public void getHttpsData(final ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean usersBean, final Context context) {
        RxHttpParams params = new RxHttpParams.Build()
                .url("https://data.block.cc/api/v1/ticker")
                .addQuery("market", usersBean.getExchangeName())
                .addQuery("symbol_pair", usersBean.getMainCode()+"_"+usersBean.getCoinpair())
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onError(String message) {
                usersBean.setStrError("暂无");
                tvMarketNew.setText("暂无");
            }

            @Override
            public void onFinish() {
                usersBean.setOk(true);
            }
            @Override
            public void onCompleted(String bean) {
                try {
                    JSONObject data = new JSONObject(bean).getJSONObject("data");
                    usersBean.setTimestamps(data.getLong("timestamps"));
                    usersBean.setLast(data.getDouble("last"));
                    usersBean.setHigh(data.getDouble("high"));
                    usersBean.setLow(data.getDouble("low"));
                    usersBean.setBid(data.getDouble("bid"));
                    usersBean.setAsk(data.getDouble("ask"));
                    usersBean.setVol(data.getDouble("vol"));
                    usersBean.setBase_volume(data.getDouble("base_volume"));
                    usersBean.setChange_daily(data.getDouble("change_daily"));
                    usersBean.setMarket(data.getString("market"));
                    usersBean.setSymbol_name(data.getString("symbol_name"));
                    usersBean.setSymbol_pair(data.getString("symbol_pair"));
                    usersBean.setRating(data.getInt("rating"));
                    usersBean.setHas_kline(data.getBoolean("has_kline"));
                    usersBean.setUsd_rate(data.getDouble("usd_rate"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setBeanData(usersBean,context);
            }
        });
    }
}
