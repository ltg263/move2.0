package com.secretk.move.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.BlueSkyBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainBlueSkyFragmentHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.img_head)
    public ImageView img_head;
    @BindView(R.id.tv_name)
    public   TextView  tv_name;
    @BindView(R.id.tv_content)
    public   TextView  tv_content;
    @BindView(R.id.tv_score)
    public   TextView  tv_score;
    @BindView(R.id.tv_follow)
    public   TextView  tv_follow;
    @BindView(R.id.tvIsFollw)
    public   TextView  tvIsFollw;
    String token = SharedUtils.singleton().get("token", "");
    public MainBlueSkyFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvIsFollw.setOnClickListener(this);
    }
    public  void  setData(final BlueSkyBean.RankList bean){
        GlideUtils.loadCircleUrl(img_head, Constants.BASE_IMG_URL+bean.getProjectIcon());
        tv_name.setText(bean.getProjectChineseName());
        tv_content.setText(bean.getProjectSignature());
        tv_score.setText(bean.getTotalScore());
        tv_follow.setText(bean.getFollowerNum());
        if (bean.getFollowStatus()==0){
            tvIsFollw.setText("+ 关注");
        }else {
            tvIsFollw.setText("已关注");
        }
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString().equals("已关注")) {
                    http(Constants.CANCEL_FOLLOW,bean.getProjectId());
                } else {
                    http(Constants.SAVE_FOLLOW,bean.getProjectId());
                }
            }
        });
    }
    public void http(String url,int id) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("followType", 1);
            node.put("followedId", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, BaseRes.class, new HttpCallBackImpl<BaseRes>() {
            @Override
            public void onCompleted(BaseRes bean) {
                if (bean.getCode()==0){
                    if (getString().equals("已关注")) {
                        tvIsFollw.setText("+ 关注");
                    } else {
                        tvIsFollw.setText("已关注");
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
    public String getString(){
        return tvIsFollw.getText().toString();
    }
}
