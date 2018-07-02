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
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：我的收藏 评分、讨论、文章列表
 */
public class MineCollectHolder extends RecyclerViewBaseHolder {

    @BindView(R.id.iv_create_user_icon)
    ImageView ivCreateUserIcon;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.tv_english_name)
    TextView tvEnglishName;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
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
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_user_type)
    TextView tvUserType;
    @BindView(R.id.iv_assist)
    ImageView ivAssist;
    @BindView(R.id.tv_praise_num)
    TextView tvPraiseNum;
    @BindView(R.id.iv_comment)
    ImageView ivComment;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.ll_details)
    LinearLayout llDetails;
    @BindView(R.id.ll_user)
    LinearLayout llUser;

    public MineCollectHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tvFollowStatus.setOnClickListener(this);
    }

    public void refresh(final int position, List<RowsBean> lists, Context context) {
        final RowsBean rowsBean = lists.get(position);
        GlideUtils.loadCircleProjectUrl(context,ivCreateUserIcon, Constants.BASE_IMG_URL + StringUtil.getBeanString(rowsBean.getProjectIcon()));
        tvCreateUserName.setText(StringUtil.getBeanString(rowsBean.getProjectCode()));
        tvEnglishName.setText("/" + StringUtil.getBeanString(rowsBean.getProjectChineseName()));
        tvCreateTime.setText(TimeToolUtils.convertTimeToFormat(rowsBean.getCreateTime()));
        tvPostTitle.setText(rowsBean.getPostTitle());
        tvTotalScore.setText(String.valueOf(rowsBean.getTotalScore())+"分");
        tvPostShortDesc.setText(rowsBean.getPostShortDesc());
        tvPraiseNum.setText(String.valueOf(rowsBean.getPraiseNum()));
        tvCommentsNum.setText(String.valueOf(rowsBean.getCommentsNum()));
        String postType="";
        switch (rowsBean.getPostType()) {//帖子类型，数字，帖子类型：1-评测；2-讨论；3-文章
            case 1:
                postType = "发表了评测";
                break;
            case 2:
                postType = "发表了爆料";
                break;
            case 3:
                postType = "发表了文章";
                break;
        }
        GlideUtils.loadCircleUserUrl(context,imgHead,
                Constants.BASE_IMG_URL+StringUtil.getBeanString(rowsBean.getCreateUserIcon()));
        tvHead.setText(rowsBean.getCreateUserName());
        tvUserType.setText(postType);
        tvPraiseNum.setSelected(true);
        ivAssist.setSelected(true);
        if (rowsBean.getFollowStatus() == 1) { //关注状态  "//0 未关注；1-已关注；2-不显示关注按钮"
            tvFollowStatus.setSelected(true);
            tvFollowStatus.setText(context.getString(R.string.follow_status_1));
        } else if (rowsBean.getFollowStatus() == 0) {
            tvFollowStatus.setSelected(false);
            tvFollowStatus.setText(context.getString(R.string.follow_status_0));
        }
        List<RowsBean.PostSmallImagesListBean> imgs = rowsBean.getPostSmallImagesList();
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
        tvFollowStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.PROJECT,rowsBean.getProjectId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
            }
        });
        rlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startProjectActivity(rowsBean.getProjectId());
            }
        });
        llDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llStartActivity(rowsBean);
            }
        });
        llUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startHomeActivity(rowsBean.getCreateUserId());
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
}
