package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.InfoNewsBean;
import com.secretk.move.ui.adapter.InfoNewsFragmentAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.CustomDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class InfoNewsFragmentHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.tv_top_time)
    TextView tvTopTime;
    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.iv_dot_b)
    ImageView ivDotB;
    @BindView(R.id.iv_dot_g)
    ImageView ivDotG;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_site)
    TextView tvSite;
    @BindView(R.id.tv_bzyhzc)
    TextView tvBzyhzc;
    @BindView(R.id.ll_top_time)
    LinearLayout llTopTime;
    @BindView(R.id.ll_html)
    LinearLayout llHtml;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.tv_head_title)
    TextView tvHeadTitle;
    @BindView(R.id.tv_detail_desc)
    TextView tvDetailDesc;
    @BindView(R.id.tv_detail_btn)
    TextView tvDetailBtn;
    @BindView(R.id.tv_info_z)
    TextView tvInfoZ;
    @BindView(R.id.tv_info_j)
    TextView tvInfoJ;
    @BindView(R.id.tv_info_share)
    TextView tvInfoShare;
    int position;
    public InfoNewsFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvInfoZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rowsBean.isRise() || rowsBean.isFall()){
                    return;
                }
                rowsBean.setRise(true);
                rowsBean.setRise(rowsBean.getRise()+1);
                adapter.notifyDataSetChanged();
                updateNewsFlashRiseAndFall("rise",rowsBean.getId());

            }
        });
        tvInfoJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rowsBean.isFall() || rowsBean.isRise()){
                    return;
                }
                rowsBean.setFall(true);
                rowsBean.setFall(rowsBean.getFall()+1);
                adapter.notifyDataSetChanged();
                updateNewsFlashRiseAndFall("fall",rowsBean.getId());
            }
        });
//
        tvInfoShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ScrollView scrollView = DialogUtils.showDialogImage(mContext, rowsBean.getCreatedAt(), rowsBean.getTitle(), rowsBean.getAbstractc());
//                ShareView.showShareImg(ll,scrollView,"share_info_news_"+rowsBean.getId());
                CustomDialog dialog = new CustomDialog(mContext,R.style.selectorDialog);
                dialog.setData(rowsBean.getCreatedAt(), rowsBean.getTitle(), rowsBean.getAbstractc(),"share_info_news_"+rowsBean.getId());
                dialog.show();
                dialog.shareUi();
            }
        });
        llHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Constants.CURRENCY+rowsBean.getId();
                IntentUtil.startWebViewActivity(url,mContext.getString(R.string.app_name));
            }
        });
        tvBzyhzc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = Constants.BI_ZHI_HUI;
                IntentUtil.startWebViewActivity(url,mContext.getString(R.string.app_name));
            }
        });
    }
    InfoNewsBean.DataBeanX.DataBean.RowsBean rowsBean;
    InfoNewsFragmentAdapter adapter;
    Context mContext;
    public void refresh(Context context, int position, List<InfoNewsBean.DataBeanX.DataBean.RowsBean> list, InfoNewsFragmentAdapter infoFragmentAdapter) {
//        tvInfoShare.setVisibility(View.GONE);
        tvDetailDesc.setMaxLines(3);
        tvSite.setVisibility(View.VISIBLE);
        this.position = position;
        this.mContext = context;
        adapter = infoFragmentAdapter;
        rowsBean = list.get(position);
        tvSite.setText(rowsBean.getSite());
        String topTime = StringUtil.getTimeToE(StringUtil.getMsToTime(rowsBean.getPublishTime()));
        if (position > 0) {
            if (topTime.equals(StringUtil.getTimeToE(StringUtil.getMsToTime(list.get(position - 1).getPublishTime())))) {
                llTopTime.setVisibility(View.GONE);
                viewTop.setVisibility(View.VISIBLE);
            } else {
                llTopTime.setVisibility(View.VISIBLE);
                viewTop.setVisibility(View.GONE);
            }
        } else {
            viewTop.setVisibility(View.GONE);
            llTopTime.setVisibility(View.VISIBLE);
        }
        tvTopTime.setText(topTime);
        tvBzyhzc.setText("币智慧支持");
        tvHeadTitle.setVisibility(View.GONE);
        if(StringUtil.isNotBlank(rowsBean.getTitle())){
            tvHeadTitle.setVisibility(View.VISIBLE);
            tvHeadTitle.setText(StringUtil.getBeanString(rowsBean.getTitle()));
        }
        tvDetailDesc.setVisibility(View.GONE);
        if(StringUtil.isNotBlank(rowsBean.getAbstractc())){
            tvDetailDesc.setVisibility(View.VISIBLE);
            tvDetailDesc.setText(StringUtil.getBeanString(rowsBean.getAbstractc()));
        }
        tvTime.setText(StringUtil.getTimeToHm(StringUtil.getMsToTime(rowsBean.getPublishTime())));
        tvInfoZ.setSelected(!rowsBean.isRise());
        tvInfoJ.setSelected(!rowsBean.isFall());

        tvInfoZ.setText("看涨" + rowsBean.getRise());
        tvInfoJ.setText("看跌" + rowsBean.getFall());
        //0-是，1-否
        if (false) {
//        if(rowsBean.getIsProminent()==0){
            ivDotB.setVisibility(View.VISIBLE);
            ivDotG.setVisibility(View.INVISIBLE);
            tvTime.setTextColor(context.getResources().getColor(R.color.app_background));
            tvHeadTitle.setTextColor(context.getResources().getColor(R.color.app_background));
            tvDetailDesc.setTextColor(context.getResources().getColor(R.color.app_background));
//            tvDetailBtn.setTextColor(context.getResources().getColor(R.color.app_background));
        } else {
            ivDotB.setVisibility(View.INVISIBLE);
            ivDotG.setVisibility(View.VISIBLE);
            tvTime.setTextColor(context.getResources().getColor(R.color.title_gray_8c));
            tvHeadTitle.setTextColor(context.getResources().getColor(R.color.title_gray));
            tvDetailDesc.setTextColor(context.getResources().getColor(R.color.title_gray_7e));
//            tvDetailBtn.setTextColor(context.getResources().getColor(R.color.title_gray_7e));
        }
//        if(rowsBean.getIsProminent()==0){
        if (false) {
            //0-是，1-否
//            if(rowsBean.getIsCheckDetails()==0){
//                tvDetailBtn.setVisibility(View.VISIBLE);
//            }else{
//                tvDetailBtn.setVisibility(View.GONE);
//            }
        }
    }
    private void updateNewsFlashRiseAndFall(String type,int id){
        JSONObject node = new JSONObject();
        try {
            node.put("id", id);
            node.put("type", 1);
            if(type.equals("rise")){
                node.put("rise", 1);
                node.put("fall", 0);
            }else{
                node.put("rise", 0);
                node.put("fall", 1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPDATE_NEWS_FLASH_RISE_AND_FALL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if(obj.getJSONObject("data")!=null){
                        int fall = obj.getJSONObject("data").getInt("fall");
                        int rise = obj.getJSONObject("data").getInt("rise");
                        if(rise!=0){
                            tvInfoZ.setText("看涨"+String.valueOf(rise));
                        }
                        if(fall!=0){
                            tvInfoJ.setText("看跌"+String.valueOf(fall));
                        }
                    }else{

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(String message) {
            }
        });
    }
}
