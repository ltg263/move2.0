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
import com.secretk.move.bean.InfoBean;
import com.secretk.move.ui.adapter.InfoFragmentAdapter;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.ShareView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class InfoFragmentHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.tv_top_time)
    TextView tvTopTime;
    @BindView(R.id.view_top)
    View viewTop;
    @BindView(R.id.iv_dot)
    ImageView ivDot;
    @BindView(R.id.tv_time)
    TextView tvTime;
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
    public InfoFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getInstance().show("跳转详情"+position);
            }
        });
        tvInfoZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                http://repay.free.ngrok.cc/rest/kff/newsFlashImg/updateNewsFlashRiseAndFall
                LogUtil.w("rowsBean.isRise():"+rowsBean.isRise());
                if(rowsBean.isRise()){
                    return;
                }
                rowsBean.setRise(true);
                rowsBean.setRise(rowsBean.getRise()-1);
                adapter.notifyDataSetChanged();
                updateNewsFlashRiseAndFall("rise",1,rowsBean.getId());

            }
        });
        tvInfoJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rowsBean.isFall()){
                    return;
                }
                rowsBean.setFall(true);
                rowsBean.setFall(rowsBean.getFall()+1);
                adapter.notifyDataSetChanged();
                updateNewsFlashRiseAndFall("fall",0,rowsBean.getId());
            }
        });
        tvInfoShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareView.showShare("skipRrl", "title","content","imgUrl");
            }
        });
    }
    InfoBean.DataBeanX.DataBean.RowsBean rowsBean;
    InfoFragmentAdapter adapter;
    public void refresh(Context context, int position, List<InfoBean.DataBeanX.DataBean.RowsBean> list, InfoFragmentAdapter infoFragmentAdapter) {
        this.position = position;
        adapter = infoFragmentAdapter;
        rowsBean = list.get(position);
        String topTime = StringUtil.getTimeToE(rowsBean.getCreatedAt());
        viewTop.setVisibility(View.GONE);
        if(position > 0){
            if(topTime.equals(StringUtil.getTimeToE(list.get(position-1).getCreatedAt()))){
                tvTopTime.setVisibility(View.GONE);
                viewTop.setVisibility(View.VISIBLE);
            }else{
                tvTopTime.setVisibility(View.VISIBLE);
                viewTop.setVisibility(View.GONE);
            }
        }
        tvTopTime.setText(topTime);
        tvTime.setText(StringUtil.getTimeToHm(rowsBean.getUpdatedAt()));
        tvHeadTitle.setText(StringUtil.getBeanString(rowsBean.getTitle()));
        tvDetailDesc.setText(StringUtil.getBeanString(rowsBean.getContent()));
        tvInfoZ.setSelected(!rowsBean.isRise());
        tvInfoJ.setSelected(!rowsBean.isFall());

        tvInfoZ.setText("看涨"+rowsBean.getRise());
        tvInfoJ.setText("看跌"+rowsBean.getFall());
        //0-是，1-否
        if(rowsBean.getIsProminent()==0){
            tvTime.setTextColor(context.getResources().getColor(R.color.app_background));
            tvHeadTitle.setTextColor(context.getResources().getColor(R.color.app_background));
            tvDetailDesc.setTextColor(context.getResources().getColor(R.color.app_background));
//            tvDetailBtn.setTextColor(context.getResources().getColor(R.color.app_background));
        }else{
            tvTime.setTextColor(context.getResources().getColor(R.color.title_gray_8c));
            tvHeadTitle.setTextColor(context.getResources().getColor(R.color.title_gray));
            tvDetailDesc.setTextColor(context.getResources().getColor(R.color.title_gray_7e));
//            tvDetailBtn.setTextColor(context.getResources().getColor(R.color.title_gray_7e));
        }
        //0-是，1-否
        if(rowsBean.getIsCheckDetails()==0){
            tvDetailBtn.setVisibility(View.VISIBLE);
        }else{
            tvDetailBtn.setVisibility(View.GONE);
        }
    }
    private void updateNewsFlashRiseAndFall(String type,int state,int id){
        JSONObject node = new JSONObject();
        try {
            node.put("id", id);
            node.put(type, state);
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
                    if( obj.getJSONObject("data")!=null){

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
