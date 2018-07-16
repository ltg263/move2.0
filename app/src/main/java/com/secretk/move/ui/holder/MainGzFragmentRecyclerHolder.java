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
import com.secretk.move.view.Clickable;

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
    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.tv_user_dynamic)
    TextView tvUserDynamic;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.view_center)
    View viewCenter;
    @BindView(R.id.iv_project_icon)
    ImageView ivProjectIcon;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_project_folly)
    TextView tvProjectFolly;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sore)
    TextView tvSore;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.iv_img_max)
    ImageView ivImgMax;
    @BindView(R.id.iv_ont)
    ImageView ivOnt;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.iv_three)
    ImageView ivThree;
    @BindView(R.id.ll_multi_img)
    LinearLayout llMultiImg;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.tv_comments)
    TextView tvComments;
    @BindView(R.id.ll_below)
    LinearLayout llBelow;
    public MainGzFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public  void  setData(final MainGzBean.DataBean.FollowsBean.RowsBean bean, final Context context){
        //actionType  //1 关注的用户 点赞帖子  2关注的用户 发表帖子  3关注的用户 关注项目 4关注的项目下发表的帖子
        GlideUtils.loadCircleUserUrl(context,imgUserHead, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getCreateUserIcon()));
        tvUser.setText(bean.getCreateUserName());
        GlideUtils.loadCircleProjectUrl(context, ivProjectIcon,Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getProjectIcon()));
        tvProjectCode.setText(StringUtil.getBeanString(bean.getProjectCode()));
        tvProjectName.setText("/"+StringUtil.getBeanString(bean.getProjectChineseName()));
        tvTime.setText(TimeToolUtils.convertTimeToFormat(bean.getCreateTime()));
        tvProjectFolly.setVisibility(View.VISIBLE);
        if (0 == bean.getFollowStatus()) {
            tvProjectFolly.setText(context.getString(R.string.follow_status_0));
            tvProjectFolly.setSelected(false);
            tvProjectFolly.setPressed(false);
            tvProjectFolly.setTextColor(Color.parseColor("#ffffff"));
        } else if (1 == bean.getFollowStatus()) {
            tvProjectFolly.setText(context.getString(R.string.follow_status_1));
            tvProjectFolly.setSelected(true);
            tvProjectFolly.setPressed(true);
            tvProjectFolly.setTextColor(Color.parseColor("#3b88f6"));
        } else {
            tvProjectFolly.setText(context.getString(R.string.follow_status_0));
            tvProjectFolly.setSelected(false);
            tvProjectFolly.setPressed(false);
            tvProjectFolly.setTextColor(Color.parseColor("#ffffff"));
        }
        if(bean.getActionType()==3){//项目
            llBelow.setVisibility(View.GONE);
            rlContext.setVisibility(View.GONE);
            showFollow(0,0,bean);
        }else{//帖子
            showFollow(bean.getPostType(),bean.getTotalScore(),bean);
            llBelow.setVisibility(View.VISIBLE);
            rlContext.setVisibility(View.VISIBLE);
            showPostDesc(bean,context);
        }
        //关注
        tvProjectFolly.setOnClickListener(new View.OnClickListener() {
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
//            1-关注项目;2-关注帖子；3-关注用户
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
                        tvProjectFolly.setText(context.getString(R.string.follow_status_0));
                        tvProjectFolly.setSelected(false);
                        tvProjectFolly.setPressed(false);
                        tvProjectFolly.setTextColor(Color.parseColor("#ffffff"));
                    } else {
                        tvProjectFolly.setText(context.getString(R.string.follow_status_1));
                        tvProjectFolly.setSelected(true);
                        tvProjectFolly.setPressed(true);
                        tvProjectFolly.setTextColor(Color.parseColor("#3b88f6"));
                    }
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
    public String getString(){
        return tvProjectFolly.getText().toString();
    }
    //头部
    public void showFollow(int postType, float totalScore, MainGzBean.DataBean.FollowsBean.RowsBean bean){
        switch (postType){
            case 0:
                tvSore.setVisibility(View.INVISIBLE);
                tvUserDynamic.setText("关注了项目");
                setCrackTag(bean,0);
                break;
            case 1:
                tvSore.setText(totalScore+"分");
                if(totalScore==0){
                    tvSore.setVisibility(View.INVISIBLE);
                }
                setCrackTag(bean,1);
                tvUserDynamic.setText("发表了评测");
                break;
            case 2:
                tvSore.setVisibility(View.INVISIBLE);
                tvUserDynamic.setText("发表了爆料");
                setCrackTag(bean,2);
                break;
            case 3:
                tvSore.setVisibility(View.INVISIBLE);
                tvUserDynamic.setText("发表了文章");
                setCrackTag(bean,3);
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
                        ivImgMax.setVisibility(View.GONE);
                        GlideUtils.loadSideMinImage(context, ivOnt, Constants.BASE_IMG_URL + lists.get(0).getUrl());
                        GlideUtils.loadSideMinImage(context, ivTwo, Constants.BASE_IMG_URL + lists.get(1).getUrl());
                        GlideUtils.loadSideMinImage(context, ivThree, Constants.BASE_IMG_URL + lists.get(2).getUrl());
                    } else {
                        llMultiImg.setVisibility(View.GONE);
                        ivImgMax.setVisibility(View.VISIBLE);
                        GlideUtils.loadSideMaxImage(context, ivImgMax, Constants.BASE_IMG_URL + lists.get(0).getUrl());
                    }
                }else{
                    llMultiImg.setVisibility(View.GONE);
                    ivImgMax.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void setCrackTag(MainGzBean.DataBean.FollowsBean.RowsBean discussDetail, int type){
        String tagVal = discussDetail.getTagInfos();
        if(type==1){
            tagVal=discussDetail.getEvaluationTags();
        }
        if (StringUtil.isNotBlank(tagVal)&& tagVal.contains("tagName")) {
            try {
                JSONArray object = new JSONArray(tagVal);
                //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
                String tagAll = "";
                String tagOnly[] = new String[object.length()];
                for (int i = 0; i < object.length(); i++) {
                    JSONObject strObj = object.getJSONObject(i);
                    tagOnly[i] = "#" + strObj.getString("tagName") + "#";
                    tagAll += "#" + strObj.getString("tagName") + "#   ";
                }
                Clickable.getSpannableString(tagAll, tagOnly, tvCrackDown, new Clickable.ClickListener() {
                    @Override
                    public void setOnClick(String name) {
                        //ToastUtils.getInstance().show(name);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
