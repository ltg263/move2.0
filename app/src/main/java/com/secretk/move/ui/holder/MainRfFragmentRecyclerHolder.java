package com.secretk.move.ui.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.ui.activity.DetailsRewardActivity;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.activity.ImageViewVpAcivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.ProjectActivity;
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
import com.secretk.move.view.ShareView;

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

public class MainRfFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @BindView(R.id.iv_model_icon_d)
    ImageView ivModelIconD;
    @BindView(R.id.rl_head_z)
    RelativeLayout rlHeadZ;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_user_folly)
    TextView tvUserFolly;
    @BindView(R.id.iv_pupo)
    ImageView ivPupo;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_sore)
    TextView tvSore;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.iv_img_max)
    ImageView ivImgMax;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_crack_down)
    TextView tvCrackDown;
    @BindView(R.id.tv_crack_down_1)
    TextView tvCrackDown1;
    @BindView(R.id.tv_crack_down_2)
    TextView tvCrackDown2;
    @BindView(R.id.rl_context)
    RelativeLayout rlContext;
    @BindView(R.id.tvPraise)
    TextView tvPraise;
    @BindView(R.id.tvComments)
    TextView tvComments;
    @BindView(R.id.tv_total_income)
    TextView tvTotalIncome;
    @BindView(R.id.tvShare)
    TextView tvShare;
    @BindView(R.id.tv_stick)
    TextView tvStick;
    @BindView(R.id.ll_xs)
    LinearLayout llXs;
    @BindView(R.id.tv_xs_1)
    TextView tvXs1;
    @BindView(R.id.tv_xs_2)
    TextView tvXs2;
    private ImagesAdapter imagesadapter;
    Context mContext;
    List<Integer> tagIdLists = new ArrayList<>();
    public MainRfFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvImg.setLayoutManager(layoutManager);
        rvImg.addItemDecoration(new GridSpacingItemDecoration());
    }
    ArrayList<PostDataInfo> imageLists;
    public  void  setData(final RowsBean bean, Context context){
        this.mContext = context;
        GlideUtils.loadCircleProjectUrl(context, ivUserIcon,Constants.BASE_IMG_URL + bean.getCreateUserIcon());
        StringUtil.getUserType(bean.getUserType(),ivModelIconD);
        String time= TimeToolUtils.convertTimeToFormat(bean.getCreateTime());
        tvUserName.setText(StringUtil.getBeanString(bean.getCreateUserName()));
        tvTime.setText(time);
        tvUserFolly.setVisibility(View.VISIBLE);
        tvStick.setVisibility(View.GONE);
        if(bean.getDisStickTop() ==1){
            tvStick.setVisibility(View.VISIBLE);
        }
        if(mContext instanceof HomeActivity){
            tvStick.setVisibility(View.GONE);
        }
        if(mContext instanceof ProjectActivity){
            tvStick.setVisibility(View.GONE);
        }
        if(bean.getFollowStatus() == 1){
            tvUserFolly.setSelected(true);
            tvUserFolly.setText(context.getResources().getString(R.string.follow_status_1));
        }else{
            tvUserFolly.setSelected(false);
            tvUserFolly.setText(context.getResources().getString(R.string.follow_status_0));
        }
        if (SharedUtils.getUserId()==bean.getCreateUserId()){
            tvUserFolly.setVisibility(View.GONE);
        }
        if(mContext instanceof HomeActivity){
            tvUserFolly.setVisibility(View.GONE);
        }
        tvTitle.setText(bean.getPostTitle());
        if(StringUtil.isBlank(bean.getPostTitle())){
            tvTitle.setVisibility(View.GONE);
        }
        String postShortDesc = StringUtil.getBeanString(bean.getPostShortDesc());
        tvDesc.setText(postShortDesc);
        llXs.setVisibility(View.GONE);
        if(bean.getPostType()==4){
            llXs.setVisibility(View.VISIBLE);
            tvTitle.setVisibility(View.GONE);
            tvXs1.setText(Html.fromHtml( "<font color=\"#ff2851\">【悬赏 ￥"+bean.getRewardMoney()+"FIND】</font>"
                    +StringUtil.getBeanString(bean.getPostTitle())));
            if(bean.getRewardMoneyToOne()>0){
                tvDesc.setText(Html.fromHtml("<font color=\"#ff2851\">【奖励"+bean.getRewardMoneyToOne()+"FIND】</font>"+postShortDesc));
            }
        }
        showRecommend(bean);
        if(StringUtil.isNotBlank(bean.getProjectCode())){
            tvProjectCode.setVisibility(View.VISIBLE);
            tvProjectCode.setText(bean.getProjectCode());
        }
        setCrackTag(bean,bean.getPostType());

        imagesadapter = new ImagesAdapter(mContext);
        rvImg.setAdapter(imagesadapter);

        ivImgMax.setVisibility(View.GONE);
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
                        ivImgMax.setVisibility(View.VISIBLE);
                        rvImg.setVisibility(View.GONE);
                        GlideUtils.loadSideMaxImage(mContext, ivImgMax, Constants.BASE_IMG_URL + imageLists.get(0).getUrl());
                    } else {
                        ivImgMax.setVisibility(View.GONE);
                        rvImg.setVisibility(View.VISIBLE);
                        imagesadapter.setBean(bean.getPostId(),bean.getPostType());
                        imagesadapter.setData(imageLists);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(bean.getPostSmallImagesList()!=null){
            List<RowsBean.PostSmallImagesListBean> listBeans = bean.getPostSmallImagesList();
            imageLists = new ArrayList<>();
            for (int i = 0; i < listBeans.size(); i++) {
                PostDataInfo info = new PostDataInfo();
                RowsBean.PostSmallImagesListBean listBean = listBeans.get(i);
                info.setUrl(StringUtil.getBeanString(listBean.getFileUrl()));
                info.setName(StringUtil.getBeanString(listBean.getFileName()));
                info.setTitle(StringUtil.getBeanString(listBean.getExtension()));
                imageLists.add(info);
            }
            if (imageLists.size() != 0) {
                if (imageLists.size() == 1) {
                    ivImgMax.setVisibility(View.VISIBLE);
                    rvImg.setVisibility(View.GONE);
                    GlideUtils.loadSideMaxImage(mContext, ivImgMax, Constants.BASE_IMG_URL + imageLists.get(0).getUrl());
                } else {
                    ivImgMax.setVisibility(View.GONE);
                    rvImg.setVisibility(View.VISIBLE);
                    imagesadapter.setData(imageLists);
                }
            }
        }
        ///0-未点赞，1-已点赞，数字
        if (bean.getPraiseStatus() == 0) {
            tvPraise.setSelected(true);
        } else {
            tvPraise.setSelected(false);
        }
        tvPraise.setText(bean.getPraiseNum() + "");
        tvComments.setText(bean.getCommentsNum() + "");
        if(bean.getPostTotalIncome()==0){
            tvTotalIncome.setText("待结算");
        }else{
            if(bean.getPostTotalIncome() == (int)bean.getPostTotalIncome()){
                tvTotalIncome.setText((int)bean.getPostTotalIncome() +"");
            }else{
                tvTotalIncome.setText(bean.getPostTotalIncome() +"");
            }
        }

        //关注
        tvUserFolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                tvUserFolly.setEnabled(false);
                NetUtil.addSaveFollow(tvUserFolly,
                        Constants.SaveFollow.USER, bean.getCreateUserId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvUserFolly.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvUserFolly.setText(str);
                                }
                            }
                        });
            }
        });

        //项目
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
        rlContext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    int postId = bean.getPostId();
                    int postType = bean.getPostType();
                    if(postType==4){
                        postType=2;
                    }
                    IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
        //用户
        rlUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!SharedUtils.getLoginZt()){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }
                IntentUtil.startHomeActivity(bean.getCreateUserId());
            }
        });
        ivPupo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ReportPopupWindowPull popupWindowPull = new ReportPopupWindowPull(mContext);
                popupWindowPull.showAtLocation(ivPupo);
                popupWindowPull.setOnItemClickListener(new ReportPopupWindowPull.OnItemClickListener() {
                    @Override
                    public void OnItemClick(View v) {
                        popupWindowPull.setListenerDate(v,bean.getPostId());
                    }
                });
            }
        });
        ivImgMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ImageViewVpAcivity.class);
                intent.putParcelableArrayListExtra("lists", imageLists);
                intent.putExtra("position",0);
                mContext.startActivity(intent);
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
        tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uslStr = "";
                switch (bean.getPostType()) {
                    case 1:
//                        if(bean.getModelType()==3){
                        if(false){//自定义评测
                            uslStr=Constants.EVALUATION_PART_SHARE+bean.getPostId();
                        }else{
                            uslStr=Constants.EVALUATION_SHARE+bean.getPostId();
                        }
                        break;
                    case 2://
                        uslStr=Constants.DISCUSS_SHARE+bean.getPostId();
                        break;
                    case 3:
                        uslStr=Constants.ARTICLE_SHARE+bean.getPostId();
                        break;
                    case 4:
                        uslStr=Constants.DISCUSS_SHARE+bean.getPostId();
                        break;
                }
                String imgUrl ="";
                if(imageLists!=null && imageLists.size()>0){
                    imgUrl = imageLists.get(0).getUrl();
                }
                ShareView.showShare(mContext,tvShare,"",uslStr,
                        tvTitle.getText().toString(), tvDesc.getText().toString(), imgUrl,bean.getPostId());
            }
        });
    }

    public void showRecommend(final RowsBean bean){
        switch (bean.getPostType()){
            case 1:
                tvSore.setVisibility(View.VISIBLE);
                tvSore.setText(bean.getTotalScore()+"分");
                if(bean.getTotalScore()==0){
                    tvSore.setVisibility(View.GONE);
                }
                break;
            case 2:
                tvSore.setVisibility(View.GONE);
                break;
            case 3:
                tvSore.setVisibility(View.GONE);
                break;
            case 4:
                tvSore.setVisibility(View.GONE);
                break;
        }

        llXs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SharedUtils.getLoginZt()) {
                    int postId = bean.getPostIdToReward();
                    IntentUtil.go2DetailsByType(10, String.valueOf(postId));
                } else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
    }
    private void setPraise(boolean isPraise, final RowsBean bead) {
        NetUtil.setAnimation(tvPraise);
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
                    if(postTotalIncome!=0){
                        if(postTotalIncome == postTotalIncome){
                            tvTotalIncome.setText((int)postTotalIncome +"");
                        }else{
                            tvTotalIncome.setText(postTotalIncome +"");
                        }
                    }
                }
            }
        });
    }

    private void setCrackTag(RowsBean discussDetail, int type){
        String tagVal = discussDetail.getTagInfos();
//        if(type==1){
        if(StringUtil.isBlank(tagVal)){
            tagVal=discussDetail.getEvaluationTags();
        }
        tvCrackDown.setVisibility(View.GONE);
        tvCrackDown1.setVisibility(View.GONE);
        tvCrackDown2.setVisibility(View.GONE);
        if (StringUtil.isNotBlank(tagVal)&& tagVal.contains("tagName")) {
            try {
                JSONArray object = new JSONArray(tagVal);
                //[{"tagId":1,"tagName":"进度讨论"},{"tagId":3,"tagName":"项目前景讨论"},{"tagId":4,"tagName":"打假"}]
                String tagAll = "";
                String tagOnly[] = new String[object.length()];
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
//                    tagOnly[i] = "#" + strObj.getString("tagName") + "#";
//                    tagAll += "#" + strObj.getString("tagName") + "#   ";
                }
//                tvCrackDown.setVisibility(View.VISIBLE);
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
}
