package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindRingRequiredBean;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.activity.DetailsArticleActivity;
import com.secretk.move.ui.activity.DetailsDiscussActivity;
import com.secretk.move.ui.activity.DetailsReviewAllActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:21
 * 邮箱；ltg263@126.com
 * 描述：币圈必读
 */
public class FindRingRequiredHolder extends RecyclerViewBaseHolder {


    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_title)
    TextView tvTitle;
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
    @BindView(R.id.ll_ring)
    LinearLayout llRing;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.img_comment)
    ImageView imgComment;
    @BindView(R.id.tv_comments)
    TextView tvComments;
    public FindRingRequiredHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final int position, List<FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean> lists, Context context) {
        final FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean rowsBean = lists.get(position);
        if(position<10){
            tvNum.setText("0"+String.valueOf(position+1));
        }else{
            tvNum.setText(String.valueOf(position+1));
        }
        tvTime.setText(StringUtil.getTimeToM(rowsBean.getCreateTime()));
        tvTitle.setText(StringUtil.getBeanString(rowsBean.getPostTitle()));
        tvPraise.setText(String.valueOf(rowsBean.getPraiseNum()));
        tvComments.setText(String.valueOf(rowsBean.getCommentsNum()));

        if(StringUtil.isNotBlank(rowsBean.getPostSmallImages())){
            try {
                JSONArray images = new JSONArray(rowsBean.getPostSmallImages());
                List<PostDataInfo> listImgs = new ArrayList<>();
                for (int i = 0; i < images.length(); i++) {
                    JSONObject strObj = images.getJSONObject(i);
                    PostDataInfo info = new PostDataInfo();
                    info.setUrl(StringUtil.getBeanString(strObj.getString("fileUrl")));
                    info.setName(StringUtil.getBeanString(strObj.getString("fileName")));
                    info.setTitle(StringUtil.getBeanString(strObj.getString("extension")));
                    listImgs.add(info);
                }
                if (listImgs != null && listImgs.size()>0) {
                    if (listImgs.size() > 2) {
                        llMultiImg.setVisibility(View.VISIBLE);
                        ivImgMax.setVisibility(View.GONE);
                        GlideUtils.loadSideMinImage_76(context, ivOnt, Constants.BASE_IMG_URL + listImgs.get(0).getUrl());
                        GlideUtils.loadSideMinImage_76(context, ivTwo, Constants.BASE_IMG_URL + listImgs.get(1).getUrl());
                        GlideUtils.loadSideMinImage_76(context, ivThree, Constants.BASE_IMG_URL + listImgs.get(2).getUrl());
                    } else {
                        llMultiImg.setVisibility(View.GONE);
                        ivImgMax.setVisibility(View.VISIBLE);
                        GlideUtils.loadSideMaxImage_135(context, ivImgMax, Constants.BASE_IMG_URL + listImgs.get(0).getUrl());
                    }
                }else{
                    llMultiImg.setVisibility(View.GONE);
                    ivImgMax.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        llRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llStartActivity(rowsBean);
            }
        });
    }

    private void llStartActivity(FindRingRequiredBean.DataBean.BgmustReadPageBean.RowsBean rowsBean) {
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
