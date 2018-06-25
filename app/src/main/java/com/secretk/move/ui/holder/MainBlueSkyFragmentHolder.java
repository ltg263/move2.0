package com.secretk.move.ui.holder;

import android.content.Context;
import android.graphics.Color;
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
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
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
    ImageView img_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_code)
    TextView tv_code;

    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.tv_score)
    TextView tv_score;
    @BindView(R.id.tv_follow)
    TextView tv_follow;
    @BindView(R.id.tvIsFollw)
    TextView tvIsFollw;

    @BindView(R.id.tv_order)
    TextView tv_order;
    @BindView(R.id.img_order)
    ImageView img_order;

    public MainBlueSkyFragmentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvIsFollw.setOnClickListener(this);
    }
    String follow0;
    String follow1;
    public void setData(final BlueSkyBean.RankList bean, int position, Context context) {
        follow0 = context.getString(R.string.follow_status_0);
        follow1 = context.getString(R.string.follow_status_1);
        GlideUtils.loadCircleUserUrl(context,img_head, Constants.BASE_IMG_URL + bean.getProjectIcon());
        tv_code.setText(bean.getProjectCode());
        tv_name.setText(" /" + bean.getProjectChineseName());
        tv_content.setText(bean.getProjectSignature());
        tv_score.setText(bean.getTotalScore());
        tv_follow.setText(bean.getFollowerNum());
        tv_order.setVisibility(View.VISIBLE);
        bean.setPosition(position + 1 + "");
        tv_order.setText(bean.getPosition());
        if (!SharedUtils.getLoginZt()) {
            tvIsFollw.setText(follow0);
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
            tvIsFollw.setVisibility(View.VISIBLE);
        } else {
            if (0 == bean.getFollowStatus()) {
                tvIsFollw.setText(follow0);
                tvIsFollw.setSelected(false);
                tvIsFollw.setPressed(false);
                tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
            } else if (1 == bean.getFollowStatus()) {
                tvIsFollw.setText(follow1);
                tvIsFollw.setSelected(true);
                tvIsFollw.setPressed(true);
                tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
            } else {
                tvIsFollw.setVisibility(View.GONE);
            }
        }
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!SharedUtils.getLoginZt()) {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                } else {
                    if (getString().equals(follow1)) {
                        http(Constants.CANCEL_FOLLOW, bean.getProjectId(),SharedUtils.getToken());
                    } else {
                        http(Constants.SAVE_FOLLOW, bean.getProjectId(),SharedUtils.getToken());
                    }
                }
            }
        });
        switch (position) {
            case 0:
                tv_order.setVisibility(View.INVISIBLE);
                img_order.setVisibility(View.VISIBLE);
                img_order.setBackgroundResource(R.drawable.topic_one);
                break;
            case 1:
                tv_order.setVisibility(View.INVISIBLE);
                img_order.setVisibility(View.VISIBLE);
                img_order.setBackgroundResource(R.drawable.topic_two);
                break;
            case 2:
                tv_order.setVisibility(View.INVISIBLE);
                img_order.setVisibility(View.VISIBLE);
                img_order.setBackgroundResource(R.drawable.topic_three);
                break;
            default:
                img_order.setVisibility(View.INVISIBLE);
                tv_order.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void http(String url, int id,String token) {
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
                SharedUtils.singleton().put("isFollowerFx",true);
//                SharedUtils.singleton().put("isFollowerSky",true);
                if (bean.getCode() == 0) {
                    if (getString().equals(follow1)) {
                        tvIsFollw.setText(follow0);
                        tvIsFollw.setPressed(false);
                        tvIsFollw.setSelected(false);
                        tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        tvIsFollw.setText(follow1);
                        tvIsFollw.setPressed(true);
                        tvIsFollw.setSelected(true);
                        tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });

    }

    public String getString() {
        return tvIsFollw.getText().toString();
    }
}
