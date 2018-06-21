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
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.bean.base.BaseRes;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MainGzFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.tvUser)
    TextView tvUser;
    @BindView(R.id.tvUserDynamic)
    TextView tvUserDynamic;
    @BindView(R.id.view_center)
    View viewCenter;
    @BindView(R.id.img_organization)
    ImageView imgOrganization;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tv_english_name)
    TextView tvEnglishName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvIsFollw)
    TextView tvIsFollw;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
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
    @BindView(R.id.tvPraise)
    TextView tvPraise;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.tvComments)
    TextView tvComments;
    @BindView(R.id.tv_crack_down)
    TextView tvDrackDown;
    @BindView(R.id.ll_below)
    LinearLayout llBelow;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    public MainGzFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public  void  setData(final MainGzBean.DataBean.FollowsBean.RowsBean bean, final Context context){
        //actionType  //1 关注的用户 点赞帖子  2关注的用户 发表帖子  3关注的用户 关注项目 4关注的项目下发表的帖子
        GlideUtils.loadCircleUserUrl(context,imgUserHead, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getCreateUserIcon()));
        tvUser.setText(bean.getCreateUserName());
        GlideUtils.loadCircleProjectUrl(context, imgOrganization,Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getProjectIcon()));
        tvName.setText(StringUtil.getBeanString(bean.getProjectCode()));
        tvEnglishName.setText("/"+StringUtil.getBeanString(bean.getProjectChineseName()));
        tvTime.setText(TimeToolUtils.convertTimeToFormat(bean.getCreateTime()));
        if (StringUtil.isNotBlank(bean.getTagInfos())) {
            String tagName = "";
            try {
                JSONArray array = new JSONArray(bean.getTagInfos());
                if (array.length() > 0) {
                    tagName = array.getJSONObject(0).getString("tagName");
                }
                tvDrackDown.setText("#"+tagName+"#");
                tvDrackDown.setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            tvDrackDown.setVisibility(View.GONE);
        }
        tvIsFollw.setVisibility(View.VISIBLE);
        if (0 == bean.getFollowStatus()) {
            tvIsFollw.setText(context.getString(R.string.follow_status_0));
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
        } else if (1 == bean.getFollowStatus()) {
            tvIsFollw.setText(context.getString(R.string.follow_status_1));
            tvIsFollw.setSelected(true);
            tvIsFollw.setPressed(true);
            tvIsFollw.setTextColor(Color.parseColor("#3b88f6"));
        } else {
            tvIsFollw.setText(context.getString(R.string.follow_status_0));
            tvIsFollw.setSelected(false);
            tvIsFollw.setPressed(false);
            tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
        }
        if(bean.getActionType()==3){//项目
            llBelow.setVisibility(View.GONE);
            rlContext.setVisibility(View.GONE);
            showFollow(0,0);
        }else{//帖子
            showFollow(bean.getPostType(),bean.getTotalScore());
            llBelow.setVisibility(View.VISIBLE);
            rlContext.setVisibility(View.VISIBLE);
            showPostDesc(bean,context);
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
                    http(context,Constants.CANCEL_FOLLOW,bean.getProjectId());
                } else {
                    http(context,Constants.SAVE_FOLLOW,bean.getProjectId());
                }
            }
        });
        //项目
        rlProject.setOnClickListener(new View.OnClickListener() {
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
        llUser.setOnClickListener(new View.OnClickListener() {
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
    public void http(final Context context, String url, int id) {
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
                    if (getString().equals(context.getString(R.string.follow_status_1))) {
                        tvIsFollw.setText(context.getString(R.string.follow_status_0));
                        tvIsFollw.setSelected(false);
                        tvIsFollw.setPressed(false);
                        tvIsFollw.setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        tvIsFollw.setText(context.getString(R.string.follow_status_1));
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
    //头部
    public void showFollow(int postType,float totalScore){
        switch (postType){
            case 0:
                tvScore.setVisibility(View.INVISIBLE);
                tvUserDynamic.setText("关注了项目");
                break;
            case 1:
                tvScore.setText(totalScore+"分");
                tvUserDynamic.setText("发表了评测");
                break;
            case 2:
                tvScore.setVisibility(View.INVISIBLE);
                tvUserDynamic.setText("发表了打假");
                break;
            case 3:
                tvScore.setVisibility(View.INVISIBLE);
                tvUserDynamic.setText("发表了文章");
                break;
        }
    }

    public void showPostDesc(MainGzBean.DataBean.FollowsBean.RowsBean bean,Context context) {
        tvTitle.setText(StringUtil.getBeanString(bean.getPostTitle()));
        tvDesc.setText(StringUtil.getBeanString(bean.getPostShortDesc()));
        tvPraise.setText(bean.getPraiseNum() + "");
        tvComments.setText(bean.getCommentsNum() + "");

        if(StringUtil.isNotBlank(bean.getPostSmallImages())){
            try {
                JSONArray images = new JSONArray(bean.getPostSmallImages());
                List<PostDataInfo> lists = new ArrayList<>();
                for (int i = 0; i < images.length(); i++) {
                    JSONObject strObj = images.getJSONObject(i);
                    PostDataInfo info = new PostDataInfo();
                    info.setUrl(StringUtil.getBeanString(strObj.getString("fileUrl")));
                    info.setName(StringUtil.getBeanString(strObj.getString("fileName")));
                    info.setTitle(StringUtil.getBeanString(strObj.getString("extension")));
                    lists.add(info);
                }
                if (lists != null && lists.size()>0) {
                    if (lists.size() > 2) {
                        llMultiImg.setVisibility(View.VISIBLE);
                        ivFileName.setVisibility(View.GONE);
                        GlideUtils.loadSideMinImage(context, ivOnt, Constants.BASE_IMG_URL + lists.get(0).getUrl());
                        GlideUtils.loadSideMinImage(context, ivTwo, Constants.BASE_IMG_URL + lists.get(1).getUrl());
                        GlideUtils.loadSideMinImage(context, ivThree, Constants.BASE_IMG_URL + lists.get(2).getUrl());
                    } else {
                        llMultiImg.setVisibility(View.GONE);
                        ivFileName.setVisibility(View.VISIBLE);
                        GlideUtils.loadSideMaxImage(context, ivFileName, Constants.BASE_IMG_URL + lists.get(0).getUrl());
                    }
                }else{
                    llMultiImg.setVisibility(View.GONE);
                    ivFileName.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
