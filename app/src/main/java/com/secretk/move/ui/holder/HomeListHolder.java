package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.ui.activity.DetailsArticleActivity;
import com.secretk.move.ui.activity.DetailsDiscussActivity;
import com.secretk.move.ui.activity.DetailsReviewAllActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.view.Clickable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：我的主页 评分、讨论、文章列表
 */
public class HomeListHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.img_user_head)
    ImageView imgUserHead;
    @BindView(R.id.iv_model_icon_d)
    ImageView ivModelIconD;
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

    public HomeListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<RowsBean> lists, Context context) {
        final RowsBean rowsBean = lists.get(position);
        GlideUtils.loadCircleProjectUrl(context,ivProjectIcon, Constants.BASE_IMG_URL + rowsBean.getProjectIcon());
        tvProjectCode.setText(rowsBean.getProjectCode());
        tvProjectName.setText("/" + rowsBean.getProjectChineseName());
        tvTime.setText(TimeToolUtils.convertTimeToFormat(rowsBean.getCreateTime()));
        tvTitle.setText(rowsBean.getPostTitle());
        tvSore.setText(String.valueOf(rowsBean.getTotalScore())+"分");
        tvDesc.setText(rowsBean.getPostShortDesc());
        tvPraise.setText(String.valueOf(rowsBean.getPraiseNum()));
        tvComments.setText(String.valueOf(rowsBean.getCommentsNum()));
        StringUtil.getUserType(rowsBean.getCreateUserType(),ivModelIconD);
        String professionalEvaDetail = rowsBean.getProfessionalEvaDetail();
        String professional = "";
        if (StringUtil.isNotBlank(professionalEvaDetail)) {
            try {
                JSONArray array = new JSONArray(professionalEvaDetail);
                if (array.length() > 0) {
                    professional = array.getJSONObject(0).getString("modelName");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        rlHead.setVisibility(View.GONE);
        switch (rowsBean.getPostType()) {//帖子类型，数字，帖子类型：1-评测；2-讨论；3-文章
            case 1:
                tvSore.setVisibility(View.VISIBLE);//分数
                setCrackTag(rowsBean,1);
                break;
            case 2:
                setCrackTag(rowsBean,2);
                tvSore.setVisibility(View.GONE);
                break;
            case 3:
                setCrackTag(rowsBean,3);
                tvSore.setVisibility(View.GONE);
                tvCrackDown.setVisibility(View.GONE);
                break;
        }
        tvProjectFolly.setVisibility(View.GONE);
        if (rowsBean.getFollowStatus() == 1) { //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
            tvProjectFolly.setText(context.getString(R.string.follow_status_1));
        } else if (rowsBean.getFollowStatus() == 0) {
            tvProjectFolly.setText(context.getString(R.string.follow_status_0));
        } else {
            tvProjectFolly.setVisibility(View.GONE);
        }
        List<RowsBean.PostSmallImagesListBean> imgs = rowsBean.getPostSmallImagesList();
        if (imgs != null && imgs.size()>0) {
            if (imgs.size() > 2) {
                llMultiImg.setVisibility(View.VISIBLE);
                ivImgMax.setVisibility(View.GONE);
                GlideUtils.loadSideMinImage(context, ivOnt, Constants.BASE_IMG_URL + imgs.get(0).getFileUrl());
                GlideUtils.loadSideMinImage(context, ivTwo, Constants.BASE_IMG_URL + imgs.get(1).getFileUrl());
                GlideUtils.loadSideMinImage(context, ivThree, Constants.BASE_IMG_URL + imgs.get(2).getFileUrl());
            } else {
                llMultiImg.setVisibility(View.GONE);
                ivImgMax.setVisibility(View.VISIBLE);
                GlideUtils.loadSideMaxImage(context, ivImgMax, Constants.BASE_IMG_URL + imgs.get(0).getFileUrl());
            }
        }else{
            llMultiImg.setVisibility(View.GONE);
            ivImgMax.setVisibility(View.GONE);
        }
        rlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startProjectActivity(rowsBean.getProjectId());
            }
        });
        rlContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llStartActivity(rowsBean);
            }
        });
    }

    private void llStartActivity(RowsBean rowsBean) {
        String postId = String.valueOf(rowsBean.getPostId());
        String key[] = {"postId"};
        String values[] = {postId};
        switch (rowsBean.getPostType()) {//帖子类型，数字，帖子类型：1-评测；2-讨论；3-文章
            case 1:
                IntentUtil.startActivity(DetailsReviewAllActivity.class, key, values);
                break;
            case 2://
                IntentUtil.startActivity(DetailsDiscussActivity.class, key, values);
                break;
            case 3:
                IntentUtil.startActivity(DetailsArticleActivity.class, key, values);
                break;
        }
    }
    private void setCrackTag(RowsBean discussDetail,int type){
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
