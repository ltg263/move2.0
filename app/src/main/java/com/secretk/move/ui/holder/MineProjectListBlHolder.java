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
import com.secretk.move.ui.activity.ImageViewVpAcivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.GridSpacingItemDecoration;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.ReportPopupWindowPull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MineProjectListBlHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img_organization)
    ImageView imgOrganization;
    @BindView(R.id.iv_model_type)
    ImageView ivModelType;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tv_project_folly)
    TextView tvProjectFolly;
    @BindView(R.id.iv_pupo)
    ImageView ivPupo;
    @BindView(R.id.rl_project)
    RelativeLayout rlProject;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDesc)
    TextView tvDesc;
    @BindView(R.id.iv_file_name)
    ImageView ivFileName;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.tv_crack_down_1)
    TextView tvCrackDown1;
    @BindView(R.id.tv_crack_down_2)
    TextView tvCrackDown2;
    @BindView(R.id.ll_below)
    RelativeLayout llBelow;
    @BindView(R.id.tvPraise)
    TextView tvPraise;
    @BindView(R.id.tvComments)
    TextView tvComments;
    @BindView(R.id.rl)
    RelativeLayout rl;
    private ImagesAdapter imagesadapter;
    private Context mContext;
    public MineProjectListBlHolder(View itemView,Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvImg.setLayoutManager(layoutManager);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());

    }
    public void setData(final RowsBean bean, final Context context){
        this.mContext=context;
        //actionType  //1 关注的用户 点赞帖子  2关注的用户 发表帖子  3关注的用户 关注项目 4关注的项目下发表的帖子
        GlideUtils.loadCircleUserUrl(context,imgOrganization, Constants.BASE_IMG_URL + StringUtil.getBeanString(bean.getProjectIcon()));
        tvProjectCode.setText(StringUtil.getBeanString(bean.getProjectCode()));
        tvProjectName.setText("/"+StringUtil.getBeanString(bean.getProjectChineseName()));
        tvTime.setText(TimeToolUtils.convertTimeToFormat(bean.getCreateTime()));
        StringUtil.getUserType(bean.getUserType(),ivModelType);
        showPostDesc(bean);
        ///0-未点赞，1-已点赞，数字
        if (bean.getPraiseStatus() == 0) {
            tvPraise.setSelected(true);
        } else {
            tvPraise.setSelected(false);
        }
        tvProjectFolly.setVisibility(View.VISIBLE);
        if(bean.getFollowStatus() == 1){
            tvProjectFolly.setSelected(true);
            tvProjectFolly.setText(context.getResources().getString(R.string.follow_status_1));
        }else{
            tvProjectFolly.setSelected(false);
            tvProjectFolly.setText(context.getResources().getString(R.string.follow_status_0));
        }

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
        //关注
        tvProjectFolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                tvProjectFolly.setEnabled(false);
                NetUtil.addSaveFollow(tvProjectFolly,
                        Constants.SaveFollow.USER, bean.getCreateUserId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvProjectFolly.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvProjectFolly.setText(str);
                                }
                            }
                        });
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
                IntentUtil.startProjectActivity(bean.getProjectId());
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
                IntentUtil.startProjectActivity(bean.getProjectId());
            }
        });
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    int postId = bean.getPostId();
                    int postType = bean.getPostType();
                    IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
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
                        int postType = bean.getPostType();
                        IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
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
                Intent intent = new Intent(context,ImageViewVpAcivity.class);
                intent.putParcelableArrayListExtra("lists", imageLists);
                intent.putExtra("position",0);
                context.startActivity(intent);
            }
        });
        tvPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    if(!tvPraise.isSelected()){
                        return;
                    }
                    if(!NetUtil.isPraise(bean.getCreateUserId(),SharedUtils.getUserId())){
                        return;
                    }
                    tvPraise.setEnabled(false);
                    String strS;
                    if(tvPraise.isSelected()){
                        strS = String.valueOf(bean.getPraiseNum()+1);
                    }else{
                        strS = String.valueOf(bean.getPraiseNum()-1);
                    }
                    tvPraise.setText(strS);
                    tvPraise.setSelected(!tvPraise.isSelected());
                    setPraise(!tvPraise.isSelected(),bean);
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
        ivPupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ReportPopupWindowPull popupWindowPull = new ReportPopupWindowPull(context);
                popupWindowPull.showAtLocation(ivPupo);
                popupWindowPull.setOnItemClickListener(new ReportPopupWindowPull.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View v) {
                        popupWindowPull.setListenerDate(v,bean.getPostId());
                    }
                });
            }
        });
    }

    ArrayList<PostDataInfo> imageLists;
    public void showPostDesc(RowsBean bean) {
        tvTitle.setText(StringUtil.getBeanString(bean.getPostTitle()));
        tvDesc.setText(StringUtil.getBeanString(bean.getPostShortDesc()));
        tvPraise.setText(bean.getPraiseNum() + "");
        tvComments.setText(bean.getCommentsNum() + "");

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
                String tagAll = "";
                String tagOnly[] = new String[object.length()];
                for (int i = 0; i < object.length(); i++) {
                    JSONObject strObj = object.getJSONObject(i);
                    if(i==0){
                        tvCrackDown.setVisibility(View.VISIBLE);
                        tvCrackDown.setText(strObj.getString("tagName"));
                    }
                    if(i==1){
                        tvCrackDown1.setVisibility(View.VISIBLE);
                        tvCrackDown1.setText(strObj.getString("tagName"));
                    }
                    if(i==2){
                        tvCrackDown2.setVisibility(View.VISIBLE);
                        tvCrackDown2.setText(strObj.getString("tagName"));
                    }
//                    tagOnly[i] = "#" + strObj.getString("tagName") + "#";
//                    tagAll += "#" + strObj.getString("tagName") + "#   ";
                }
//                Clickable.getSpannableString(tagAll, tagOnly, tvCrackDown, new Clickable.ClickListener() {
//                    @Override
//                    public void setOnClick(String name) {
//                        //ToastUtils.getInstance().show(name);
//                    }
//                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void setPraise(boolean isPraise, final RowsBean bead) {
        NetUtil.setPraise(isPraise, bead.getPostId(), new NetUtil.SaveFollowImpl() {
            @Override
            public void finishFollow(String praiseNum,boolean status,double find,double postTotalIncome) {
                tvPraise.setEnabled(true);
                ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                    DialogUtils.showDialogPraise(mContext,1,true,find);
                    tvPraise.setSelected(status);
                    bead.setPageviewNum(Integer.valueOf(praiseNum));
                    tvPraise.setText(praiseNum);
                }
            }
        });
    }
}
