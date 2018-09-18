package com.secretk.move.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.ui.activity.DetailsRewardActivity;
import com.secretk.move.ui.activity.ImageViewVpAcivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.MainActivity;
import com.secretk.move.ui.activity.ReleaseDiscussActivity;
import com.secretk.move.ui.activity.RewardSquareActivity;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.GridSpacingItemDecoration;
import com.secretk.move.utils.IntentUtil;
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

public class UnifyUserListXsHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_organization)
    ImageView imgOrganization;
    @BindView(R.id.iv_model_type)
    ImageView ivModelType;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_stick)
    TextView tvStick;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_find_num)
    TextView tvFindNum;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.iv_file_name)
    ImageView ivFileName;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.tv_crack_down_1)
    TextView tvCrackDown1;
    @BindView(R.id.tv_crack_down_2)
    TextView tvCrackDown2;
    @BindView(R.id.ll_below)
    RelativeLayout llBelow;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_go_hd)
    TextView tvGoHd;
    @BindView(R.id.tv_go_gc)
    TextView tvGoGc;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private ImagesAdapter imagesadapter;
    private Context mContext;
    List<Integer> tagIdLists = new ArrayList<>();
    public UnifyUserListXsHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mContext=context;
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvImg.setLayoutManager(layoutManager);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());
    }
    public void setData(final RowsBean bean, int position,int size){
        //actionType  //1 关注的用户 点赞帖子  2关注的用户 发表帖子  3关注的用户 关注项目 4关注的项目下发表的帖子
        GlideUtils.loadCircleUserUrl(mContext,imgOrganization, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getCreateUserIcon()));
        tvGoGc.setVisibility(View.GONE);
        if(mContext instanceof MainActivity){
            if((position%10)==0 && position!=0){
                tvGoGc.setVisibility(View.VISIBLE);
            }
            if(position==size-1){
                tvGoGc.setVisibility(View.VISIBLE);
            }
        }
        tvName.setText(bean.getCreateUserName());
        tvTime.setText(TimeToolUtils.convertTimeToFormat(bean.getCreateTime()));
        if(StringUtil.isNotBlank(bean.getProjectCode())){
            tvProjectCode.setVisibility(View.VISIBLE);
            tvProjectCode.setText(bean.getProjectCode());
        }
        tvStick.setVisibility(View.GONE);
        if(bean.getDisStickTop()==1){
            tvStick.setVisibility(View.VISIBLE);
        }
        StringUtil.getUserType(bean.getUserType(),ivModelType);
        showPostDesc(bean);

        tvProjectCode.setOnClickListener(new View.OnClickListener() {
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
        imgOrganization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startHomeActivity(bean.getCreateUserId());
            }
        });
        //用户
        rlProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startHomeActivity(bean.getCreateUserId());
            }
        });
        //用户
        rlHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startHomeActivity(bean.getCreateUserId());
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    int postId = bean.getPostId();
                    IntentUtil.go2DetailsByType(10, String.valueOf(postId));
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
        rvImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (SharedUtils.getLoginZt()) {
                        int postId = bean.getPostId();
//                        int postType = bean.getPostType();
                        IntentUtil.go2DetailsByType(10, String.valueOf(postId));
                    } else {
                        IntentUtil.startActivity(LoginHomeActivity.class);
                    }
                }
                return false;
            }
        });
        ivFileName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ImageViewVpAcivity.class);
                intent.putParcelableArrayListExtra("lists", imageLists);
                intent.putExtra("position",0);
                mContext.startActivity(intent);
            }
        });
        tvGoGc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startActivity(RewardSquareActivity.class);
            }
        });
        tvGoHd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ReleaseDiscussActivity.class);
                intent.putExtra("projectId", bean.getProjectId());
                intent.putExtra("projectPay", bean.getProjectCode());
                intent.putExtra("postId", bean.getPostId());
                intent.putExtra("tagInfos", bean.getTagInfos());
                mContext.startActivity(intent);
            }
        });
    }

    ArrayList<PostDataInfo> imageLists;
    public void showPostDesc(RowsBean bean) {
        tvTitle.setVisibility(View.GONE);
        if(StringUtil.isNotBlank(bean.getPostTitle())){
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(StringUtil.getBeanString(bean.getPostTitle()));
        }
        String b = "悬赏"+bean.getRewardMoney()+"FIND";
        tvDesc.setText(StringUtil.getBeanString(bean.getPostShortDesc()));
        tvFindNum.setText(b);
        //截止时间08.08 12:00，已有12人回答
        //// status:/悬赏的状态：0-进行中，1-已结束，2-已撤销
        String endTime="";
        if(bean.getState() == 0){
            endTime = "截止时间"+StringUtil.getTimeMDHM(bean.getEndTime())+"，已有"+bean.getAnswerCount()+"人回答";
        }else if(bean.getState() == 1){
            endTime = "已结束，已有"+bean.getAnswerCount()+"人回答";
        }else if(bean.getState() == 2){
            endTime = "已撤销，已有"+bean.getAnswerCount()+"人回答";
        }
        tvEndTime.setText(endTime);
        imagesadapter = new ImagesAdapter(mContext);
        rvImg.setAdapter(imagesadapter);
        ivFileName.setVisibility(View.GONE);
        rvImg.setVisibility(View.GONE);

        if(StringUtil.isNotBlank(bean.getPostSmallImages())){
            try {
                //{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},
                JSONArray images = new JSONArray(bean.getPostSmallImages());
                imageLists = new ArrayList<>();
                for (int i = 0; i < images.length(); i++) {
                    JSONObject strObj = images.getJSONObject(i);
                    PostDataInfo info = new PostDataInfo();
                    info.setUrl(StringUtil.getBeanString(strObj.getString("fileUrl")));
                    info.setName(StringUtil.getBeanString(strObj.getString("fileName")));
                    info.setTitle(StringUtil.getBeanString(strObj.getString("extension")));
                    imageLists.add(info);
                }
                if (imageLists.size() != 0) {
                    if (imageLists.size() == 1) {
                        ivFileName.setVisibility(View.VISIBLE);
                        rvImg.setVisibility(View.GONE);
                        GlideUtils.loadSideMaxImage(mContext, ivFileName, Constants.BASE_IMG_URL + imageLists.get(0).getUrl());
                    } else {
                        ivFileName.setVisibility(View.GONE);
                        rvImg.setVisibility(View.VISIBLE);
                        imagesadapter.setBean(bean.getPostId(),bean.getPostType());
                        imagesadapter.setData(imageLists);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tvCrackDown.setVisibility(View.GONE);
        tvCrackDown1.setVisibility(View.GONE);
        tvCrackDown2.setVisibility(View.GONE);
        if (StringUtil.isNotBlank(bean.getTagInfos())&& bean.getTagInfos().contains("tagName")) {
            try {
                JSONArray object = new JSONArray(bean.getTagInfos());
                //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
                for (int i = 0; i < object.length(); i++) {
                    JSONObject strObj = object.getJSONObject(i);
                    if(i==0){
                        tvCrackDown.setVisibility(View.VISIBLE);
                        tvCrackDown.setText(strObj.getString("tagName"));
                        tagIdLists.add(strObj.getInt("tagId"));
                        IntentUtil.startCrackDown(tvCrackDown,tagIdLists.get(0));
                    }
                    if(i==1){
                        tvCrackDown1.setVisibility(View.VISIBLE);
                        tvCrackDown1.setText(strObj.getString("tagName"));
                        tagIdLists.add(strObj.getInt("tagId"));
                        IntentUtil.startCrackDown(tvCrackDown1,tagIdLists.get(1));
                    }
                    if(i==2){
                        tvCrackDown2.setVisibility(View.VISIBLE);
                        tvCrackDown2.setText(strObj.getString("tagName"));
                        tagIdLists.add(strObj.getInt("tagId"));
                        IntentUtil.startCrackDown(tvCrackDown2,tagIdLists.get(2));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
