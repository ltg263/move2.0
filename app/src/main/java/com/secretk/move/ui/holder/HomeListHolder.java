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

import org.json.JSONArray;
import org.json.JSONException;

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
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_english_name)
    TextView tvEnglishName;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.ll_details)
    LinearLayout llDetails;
    @BindView(R.id.rl_discuss)
    RelativeLayout rlDiscuss;
    @BindView(R.id.iv_create_user_icon)
    ImageView ivCreateUserIcon;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.tv_post_title)
    TextView tvPostTitle;
    @BindView(R.id.tv_total_score)
    TextView tvTotalScore;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
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
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.iv_assist)
    ImageView ivAssist;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    public HomeListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvFollowStatus.setOnClickListener(this);
    }

    public void refresh(final int position, List<RowsBean> lists , Context context) {
        final RowsBean rowsBean = lists.get(position);
        GlideUtils.loadCircleUrl(ivCreateUserIcon, Constants.BASE_IMG_URL+rowsBean.getProjectIcon());
        tvCreateUserName.setText(rowsBean.getProjectChineseName());
        tvEnglishName.setText("/"+rowsBean.getProjectCode());
        tvCreateTime.setText(StringUtil.getTimeToM(rowsBean.getCreateTime()));
        tvPostTitle.setText(rowsBean.getPostTitle());
        tvTotalScore.setText(String.valueOf(rowsBean.getTotalScore()));
        tvPostShortDesc.setText(rowsBean.getPostShortDesc());
        tvPraiseNum.setText(String.valueOf(rowsBean.getPraiseNum()));
        tvCommentsNum.setText(String.valueOf(rowsBean.getCollectNum()));
        String tagInfos = rowsBean.getEvaluationTags();
        String tagName = "";
        if(StringUtil.isNotBlank(tagInfos)){
            try {
                JSONArray array = new JSONArray(tagInfos);
                if(array.length()>0){
                    tagName = array.getJSONObject(0).getString("tagName");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        switch (rowsBean.getPostType()){//帖子类型，数字，帖子类型：1-评测；2-讨论；3-文章
            case 1:
                tvTotalScore.setVisibility(View.VISIBLE);//分数
                tvFollowStatus.setVisibility(View.GONE);//关注
                rlDiscuss.setVisibility(View.GONE);//发表
                if(StringUtil.isNotBlank(tagName)){
                    tvCrackDown.setVisibility(View.VISIBLE);//标签
                    tvCrackDown.setText(tagName);
                }else{
                    tvCrackDown.setVisibility(View.GONE);//标签
                }
                break;
            case 2:
                tvTotalScore.setVisibility(View.GONE);
                tvFollowStatus.setVisibility(View.VISIBLE);
                rlDiscuss.setVisibility(View.GONE);
                tvCrackDown.setVisibility(View.GONE);
                break;
            case 3:
                tvTotalScore.setVisibility(View.GONE);
                tvFollowStatus.setVisibility(View.GONE);
                rlDiscuss.setVisibility(View.GONE);
                tvCrackDown.setVisibility(View.GONE);
                break;
        }
        if(rowsBean.getFollowStatus()==1){ //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
            tvFollowStatus.setText(context.getString(R.string.follow_status_1));
            tvPraiseNum.setSelected(true);
            ivAssist.setSelected(true);
        }else if(rowsBean.getFollowStatus()==0){
            tvFollowStatus.setText(context.getString(R.string.follow_status_0));
            tvPraiseNum.setSelected(false);
            ivAssist.setSelected(false);
        }else{
            tvFollowStatus.setVisibility(View.GONE);
        }
        List<RowsBean.PostSmallImagesListBean> imgs = rowsBean.getPostSmallImagesList();
        if(imgs.size()>=2){
            llMultiImg.setVisibility(View.VISIBLE);
            ivFileName.setVisibility(View.GONE);
            GlideUtils.loadImage(context,ivOnt, Constants.BASE_IMG_URL+imgs.get(0).getFileUrl());
            GlideUtils.loadImage(context,ivTwo, Constants.BASE_IMG_URL+imgs.get(1).getFileUrl());
            GlideUtils.loadImage(context,ivThree, Constants.BASE_IMG_URL+imgs.get(2).getFileUrl());
        }else{
            llMultiImg.setVisibility(View.GONE);
            ivFileName.setVisibility(View.VISIBLE);
            GlideUtils.loadImage(context,ivFileName, Constants.BASE_IMG_URL+imgs.get(0).getFileUrl());
        }
        rlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startProjectActivity(rowsBean.getProjectId());
            }
        });
        llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postId = String.valueOf(rowsBean.getPostId());
                String key[]={"postId"};
                String values[]={postId};
                switch (rowsBean.getPostType()){//帖子类型，数字，帖子类型：1-评测；2-讨论；3-文章
                    case 1:
                        IntentUtil.startActivity(DetailsReviewAllActivity.class,key,values);
                        break;
                    case 2://
                        IntentUtil.startActivity(DetailsDiscussActivity.class,key,values);
                        break;
                    case 3:
                        IntentUtil.startActivity(DetailsArticleActivity.class,key,values);
                        break;
                }
            }
        });
    }
}
