package com.secretk.move.ui.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MainGzBean;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainGzFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_organization)
    public ImageView img_organization;
    @BindView(R.id.tvName)
    public TextView tvName;
    @BindView(R.id.tv_english_name)
    public TextView tv_english_name;

    @BindView(R.id.tvIsFollw)
    public TextView tvIsFollw;
    @BindView(R.id.tvTime)
    public TextView tvTime;
    @BindView(R.id.tvTitle)
    public TextView tvTitle;
    @BindView(R.id.tvScore)
    public TextView tvScore;
    @BindView(R.id.tvDesc)
    public TextView tvDesc;
    @BindView(R.id.img_user_head)
    public ImageView img_user_head;
    @BindView(R.id.tvUser)
    public TextView tvUser;
    @BindView(R.id.tvPraise)
    public TextView tvPraise;
    @BindView(R.id.tvComments)
    public TextView tvComments;

    @BindView(R.id.rl_project)
    public RelativeLayout rl_project;
    @BindView(R.id.ll_user)
    LinearLayout ll_user;
    @BindView(R.id.tvUserDynamic)
    TextView tvUserDynamic;


    @BindView(R.id.rl_follow)
    RelativeLayout rl_follow;
    @BindView(R.id.ll_user2)
    LinearLayout ll_user2;
    @BindView(R.id.img_user_head2)
    public ImageView img_user_head2;
    @BindView(R.id.tvUser2)
    public TextView tvUser2;
    @BindView(R.id.tvUserDynamic2)
    public TextView tvUserDynamic2;
    @BindView(R.id.tv_release_time)
    public TextView tv_release_time;
    @BindView(R.id.img_comment)
    ImageView img_comment;
    @BindView(R.id.iv_file_name)
    ImageView ivFileName;
    @BindView(R.id.iv_ont)
    ImageView ivOnt;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.iv_three)
    ImageView ivThree;
    @BindView(R.id.ll_multi_img)
    LinearLayout llMultiImg;
    public MainGzFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public  void  setData(final MainGzBean.DataBean.FollowsBean.RowsBean bean, Context context){
        GlideUtils.loadCircleProjectUrl(context, img_organization,Constants.BASE_IMG_URL + bean.getProjectIcon());
        showFollow(context,bean);
        tvName.setText(bean.getProjectChineseName());
        tv_english_name.setText("/"+bean.getProjectEnglishName());
        String time= TimeUtils.getChatTime(bean.getCreateTime());
       tvTime.setText(time);
        tvIsFollw.setVisibility(View.VISIBLE);
        if (0 == bean.getFollowStatus()) {
            tvIsFollw.setText("+ 关注");
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
        } else if (1 == bean.getFollowStatus()) {
            tvIsFollw.setText("已关注");
            tvIsFollw.setSelected(true);
            tvIsFollw.setPressed(true);
            tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
        } else {
            tvIsFollw.setText("+ 关注");
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
        }
        tvTitle.setText(bean.getPostTitle());
        tvDesc.setText(bean.getPostShortDesc());
        tvPraise.setText(bean.getPraiseNum() + "");
        tvComments.setText(bean.getCommentsNum() + "");
        List<MainGzBean.DataBean.FollowsBean.RowsBean.PostSmallImagesListBean> imgs = bean.getPostSmallImagesList();
        if (imgs != null && imgs.size()>0) {
            if (imgs.size() > 2) {
                llMultiImg.setVisibility(View.VISIBLE);
                ivFileName.setVisibility(View.GONE);
                GlideUtils.loadSideMinImage(context, ivOnt, Constants.BASE_IMG_URL + imgs.get(0).getFileUrl());
                GlideUtils.loadSideMinImage(context, ivTwo, Constants.BASE_IMG_URL + imgs.get(1).getFileUrl());
                GlideUtils.loadSideMinImage(context, ivThree, Constants.BASE_IMG_URL + imgs.get(2).getFileUrl());
            } else {
                llMultiImg.setVisibility(View.GONE);
                ivFileName.setVisibility(View.VISIBLE);
                GlideUtils.loadSideMaxImage(context, ivFileName, Constants.BASE_IMG_URL + imgs.get(0).getFileUrl());
            }
        }else{
            llMultiImg.setVisibility(View.GONE);
            ivFileName.setVisibility(View.GONE);
        }
        //关注
        tvIsFollw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                if (getString().equals("已关注")) {
                    http(Constants.CANCEL_FOLLOW,bean.getProjectId());
                } else {
                    http(Constants.SAVE_FOLLOW,bean.getProjectId());
                }
            }
        });
        //项目
        rl_project.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startProjectActivity(bean.getProjectId());
            }
        });
        //用户
        ll_user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startHomeActivity(bean.getCreateUserId());
            }
        });

    }
    public void http(String url,int id) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.getToken());
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
                        tvIsFollw.setSelected(false);
                        tvIsFollw.setPressed(false);
                        tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        tvIsFollw.setText("已关注");
                        tvIsFollw.setSelected(true);
                        tvIsFollw.setPressed(true);
                        tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
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

    public void showFollow(Context context, MainGzBean.DataBean.FollowsBean.RowsBean bean){
        rl_follow.setVisibility(View.VISIBLE);
        ll_user.setVisibility(View.GONE);
        GlideUtils.loadCircleUserUrl(context,img_user_head2, Constants.BASE_IMG_URL + bean.getCreateUserIcon());
        tvUser2.setText(bean.getCreateUserName());
        switch (bean.getPostType()){
            case 1:
                tvScore.setVisibility(View.VISIBLE);
                tvScore.setText(bean.getTotalScore()+"分");
                tvUserDynamic2.setText("发表了评测");
                break;
            case 2:
                tvScore.setVisibility(View.INVISIBLE);
                tvUserDynamic2.setText("发表了讨论");
                break;
            case 3:
                tvScore.setVisibility(View.INVISIBLE);
                tvUserDynamic2.setText("发表了文章");
                break;
        }
        String time= TimeUtils.getChatTime(bean.getCreateTime());
        tv_release_time.setText(time);
    }
}
