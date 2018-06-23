package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nex3z.flowlayout.FlowLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DetailsReviewBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.adapter.ImagesAdapter;
import com.secretk.move.ui.adapter.ProgressAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.PileLayout;
import com.secretk.move.view.PopupWindowUtils;
import com.secretk.move.view.ProgressBarStyleView;
import com.secretk.move.view.ShareView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/5/2 14:12
 * 邮箱；ltg263@126.com
 * 描述: 评测详情--全部
 */
public class DetailsReviewAllActivity extends BaseActivity {

    String postId;
    @BindView(R.id.tv_post_title)
    TextView tvPostTitle;
    @BindView(R.id.tv_total_score)
    TextView tvTotalScore;
    @BindView(R.id.iv_create_user_icon)
    ImageView ivCreateUserIcon;
    @BindView(R.id.tv_create_user_name)
    TextView tvCreateUserName;
    @BindView(R.id.tv_create_user_signature)
    TextView tvCreateUserSignature;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.pb_comprehensive_evaluation)
    ProgressBarStyleView pbComprehensiveEvaluation;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.fl_evaluation_tags)
    FlowLayout flEvaluationTags;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.pile_layout)
    PileLayout pileLayout;
    @BindView(R.id.tv_donate_num)
    TextView tvDonateNum;
    @BindView(R.id.tv_praise_status)
    TextView tvPraiseStatus;
    @BindView(R.id.tv_collect_status)
    TextView tvCollectStatus;
    @BindView(R.id.tv_commendation_Num)
    TextView tvCommendationNum;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.wv_post_short_desc)
    WebView wvPostShortDesc;
    private int createUserId;
    private ImagesAdapter adapter;
    private int projectId;
    private int praiseNum;
    private ProgressAdapter adapterProgress;
    private String postShortDesc;
    private String shareUrl;
    private String imgUrl="";

    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_review_all;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
//        String str =  postShortDesc.substring(0, 10);
//        1对应为值为“简单评测", 2 为 "ALL-专业评测" 3 为 "PART-专业评测" 4 为 "ALL-专业评测
        ShareView.showShare(shareUrl,tvPostTitle.getText().toString(),postShortDesc,imgUrl);
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        setVerticalManager(rvImg);
        setVerticalManager(rvReview);
        adapterProgress = new ProgressAdapter(this);
        rvReview.setAdapter(adapterProgress);
        adapter = new ImagesAdapter(this);
        rvImg.setAdapter(adapter);
        WebSettings webSettings = wvPostShortDesc.getSettings();
        webSettings.setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        loadingDialog.show();
    }

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            loadingDialog.dismiss();
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", Integer.valueOf(postId));//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.EVALUATION_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DetailsReviewBean.class, new HttpCallBackImpl<DetailsReviewBean>() {
            @Override
            public void onCompleted(DetailsReviewBean bean) {
                initUiData(bean.getData().getEvaluationDetail());
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
                findViewById(R.id.yes_data).setVisibility(View.VISIBLE);
                findViewById(R.id.no_data).setVisibility(View.GONE);
            }
        });
    }

    private void initUiData(DetailsReviewBean.DataBean.EvaluationDetailBean evaluationDetail){
        mHeadView.setTitle(StringUtil.getBeanString(evaluationDetail.getProjectCode()));
        mHeadView.setTitleVice("/" + StringUtil.getBeanString(evaluationDetail.getProjectChineseName()));
        //        1对应为值为“简单评测", 2 为 "ALL-专业评测" 3 为 "PART-专业评测" 4 为 "ALL-专业评测
        if(evaluationDetail.getModelType()==3){
            shareUrl=Constants.EVALUATION_PART_SHARE+Integer.valueOf(postId);
        }else{
            shareUrl=Constants.EVALUATION_SHARE+Integer.valueOf(postId);
        }

        GlideUtils.loadCircleProjectUrl(this,mHeadView.getImageView(), Constants.BASE_IMG_URL +
                StringUtil.getBeanString(evaluationDetail.getProjectIcon()));
        tvProjectCode.setText(evaluationDetail.getProjectCode());
        postShortDesc = evaluationDetail.getPostShortDesc();
        createUserId = evaluationDetail.getCreateUserId();
        projectId = evaluationDetail.getProjectId();
        mHeadView.setToolbarListener(projectId);
        if(evaluationDetail.getModelType()==1){
            tvPostTitle.setText(evaluationDetail.getProjectCode()+"-"+StringUtil.getBeanString(evaluationDetail.getPostTitle()));
        }else {
            tvPostTitle.setText(StringUtil.getBeanString(evaluationDetail.getPostTitle()));
        }
        tvTotalScore.setText(String.valueOf(evaluationDetail.getTotalScore()));
        GlideUtils.loadCircleUserUrl(this,ivCreateUserIcon,
                Constants.BASE_IMG_URL + StringUtil.getBeanString(evaluationDetail.getCreateUserIcon()));
        tvCreateUserName.setText(StringUtil.getBeanString(evaluationDetail.getCreateUserName()));
        if(evaluationDetail.getUserType()!=1){
            ivModelIcon.setVisibility(View.VISIBLE);
            StringUtil.getUserType(evaluationDetail.getUserType(),ivModelIcon);
        }
        tvCreateUserSignature.setText(StringUtil.getBeanString(evaluationDetail.getCreateUserSignature()));
        //,//"0 未关注；1-已关注；2-不显示关注按钮"\
        if (evaluationDetail.getFollowStatus() == 0) {
            tvFollowStatus.setSelected(false);
            tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
        } else if (evaluationDetail.getFollowStatus() == 1) {
            tvFollowStatus.setSelected(true);
            tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
        } else {
            tvFollowStatus.setVisibility(View.GONE);
        }
        if(baseUserId==evaluationDetail.getCreateUserId()){
            tvFollowStatus.setVisibility(View.GONE);
        }
        wvPostShortDesc.loadData(StringUtil.getNewContent(evaluationDetail.getEvauationContent()), "text/html; charset=UTF-8", null);//这种写法可以正确解码
        tvCreateTime.setText("发布于 "+StringUtil.getTimeToM(evaluationDetail.getCreateTime()));
        if(evaluationDetail.getDonateNum()>0){
            pileLayout.setVisibility(View.VISIBLE);
            tvDonateNum.setVisibility(View.VISIBLE);
            tvDonateNum.setText(evaluationDetail.getDonateNum() + getString(R.string.sponsor_num));
        }
        praiseNum = evaluationDetail.getPraiseNum();
        tvPraiseStatus.setText(getString(R.string.like) + String.valueOf(praiseNum));
        ///0-未点赞，1-已点赞，数字
        if (evaluationDetail.getPraiseStatus() == 0) {
            tvPraiseStatus.setSelected(true);
        } else {
            tvPraiseStatus.setSelected(false);
        }
        //0-未收藏，1-已收藏，数字
        if (evaluationDetail.getCollectStatus() == 0) {
            tvCollectStatus.setSelected(true);
        } else {
            tvCollectStatus.setSelected(false);
        }
        tvCommendationNum.setText(getString(R.string.sponsor) + String.valueOf(Math.round(evaluationDetail.getCommendationNum())));
        tvCommentsNum.setText(getString(R.string.comment) + String.valueOf(evaluationDetail.getCollectNum()));

//        modelType = 1-简单评测；2-全面系统专业评测;3-部分系统专业评测；4-专业评测-自定义类型
        //进度名称
        try {
            String modelType = getResources().getString(R.string.comprehensive_evaluation);
            if(evaluationDetail.getModelType()==Constants.ModelType.MODEL_TYPE_PART){
                String evaDetail = evaluationDetail.getProfessionalEvaDetail();
                if(StringUtil.isNotBlank(evaDetail)){
                    JSONArray array = new JSONArray(evaDetail);
                    modelType =array.getJSONObject(0).getString("modelName");
                }
            }
            pbComprehensiveEvaluation.setTvOne(modelType, 0,getResources().getColor(R.color.title_gray));
            pbComprehensiveEvaluation.setTvThree(evaluationDetail.getTotalScore(), 16, R.color.app_background);
            pbComprehensiveEvaluation.setPbProgressMaxVisible();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(evaluationDetail.getModelType()==Constants.ModelType.MODEL_TYPE_ALL
                || evaluationDetail.getModelType()==Constants.ModelType.MODEL_TYPE_ALL_NEW){
            try {
                initProfessionalData(evaluationDetail.getProfessionalEvaDetail());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //标签
        try {
            String evaluationTags = evaluationDetail.getEvaluationTags();
            if (StringUtil.isNotBlank(evaluationTags)) {
                JSONArray array = new JSONArray(evaluationTags);
                for (int i = 0; i < array.length(); i++) {
                    String tagName = array.getJSONObject(i).getString("tagName");
                    TextView tv = buildLabel(tagName);
                    flEvaluationTags.addView(tv);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //图片
        try {
            List<PostDataInfo> lists = new ArrayList<>();
            if(StringUtil.isNotBlank(evaluationDetail.getPostSmallImages())){
                JSONArray images  = new JSONArray(evaluationDetail.getPostSmallImages());
                for (int i = 0; i < images.length(); i++) {
                    JSONObject strObj = images.getJSONObject(i);
                    PostDataInfo info = new PostDataInfo();
                    info.setUrl(strObj.getString("fileUrl"));
                    info.setName(strObj.getString("fileName"));
                    info.setTitle(strObj.getString("extension"));
                    lists.add(info);
                }
//                rvImg.setVisibility(View.VISIBLE);
            }
            if(lists.size()>0){
                imgUrl = lists.get(0).getUrl();
            }
            adapter.setData(lists);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<DetailsReviewBean.DataBean.EvaluationDetailBean.CommendationListBean> pileLists = evaluationDetail.getCommendationList();
        if (pileLists != null) {
            initPraises(pileLists);
        }
    }

    /**
     * 设置捐款人头像
     *
     * @param pileLists
     */
    public void initPraises(List<DetailsReviewBean.DataBean.EvaluationDetailBean.CommendationListBean> pileLists) {
        LayoutInflater nflater = LayoutInflater.from(this);
        if(pileLayout!=null){
            pileLayout.removeAllViews();
        }
        List<String> lists = new ArrayList<>();
        for (int i = 0; i < pileLists.size() && i<7; i++) {
            if(!lists.contains(String.valueOf(pileLists.get(i).getReceiveUserId()))){
                ImageView imageView = (ImageView) nflater.inflate(R.layout.item_praise, pileLayout, false);
                GlideUtils.loadCircleUserUrl(this,imageView, Constants.BASE_IMG_URL + pileLists.get(i).getSendUserIcon());
                pileLayout.addView(imageView);
            }
            lists.add(String.valueOf(pileLists.get(i).getReceiveUserId()));
        }
    }
    /**
     * 设置评分进度
     *
     * @param eva
     * @throws JSONException
     */
    private void initProfessionalData(String eva) throws JSONException {
        if(StringUtil.isBlank(eva)){
            return;
        }
        List<String> listPd = new ArrayList<>();
        JSONArray object = new JSONArray(eva);
        for (int i = 0; i < object.length(); i++) {
            listPd.add(object.getJSONObject(i).toString());
        }
        if(listPd.size()>0){
            rvReview.setVisibility(View.VISIBLE);
            adapterProgress.setData(listPd);
        }
    }


    @OnClick({R.id.rl_ge_ren, R.id.tv_follow_status, R.id.tv_praise_status, R.id.tv_collect_status, R.id.tv_commendation_Num, R.id.tv_comments_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(createUserId);
                break;
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.USER, Integer.valueOf(createUserId), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.tv_praise_status:
                if(!tvPraiseStatus.isSelected()){
                    return;
                }
                if(!NetUtil.isPraise(createUserId,baseUserId)){
                    return;
                }
                tvPraiseStatus.setEnabled(false);
                String str;
                if (tvPraiseStatus.isSelected()) {
                    str = getString(R.string.like) + String.valueOf(praiseNum + 1);
                } else {
                    str = getString(R.string.like) + String.valueOf(praiseNum - 1);
                }
                tvPraiseStatus.setText(str);
                tvPraiseStatus.setSelected(!tvPraiseStatus.isSelected());
                setPraise(!tvPraiseStatus.isSelected(), Integer.valueOf(postId));
                break;
            case R.id.tv_collect_status:
                tvCollectStatus.setEnabled(false);
                tvCollectStatus.setSelected(!tvCollectStatus.isSelected());
                NetUtil.saveCollect(tvCollectStatus.isSelected(),
                        Integer.valueOf(postId), new NetUtil.SaveCollectImp() {
                            @Override
                            public void finishCollect(String str, boolean status) {
                                tvCollectStatus.setEnabled(true);
                                if (!str.equals(Constants.COLLECT_ERROR)) {
                                    tvCollectStatus.setSelected(status);
                                }
                            }
                        });
                break;
            case R.id.tv_commendation_Num:
                if(!NetUtil.isSponsor(createUserId,baseUserId)){
                    return;
                }
                PopupWindowUtils window = new PopupWindowUtils(this, new PopupWindowUtils.GiveDialogInterface() {
                    @Override
                    public void btnConfirm(String season) {
                        NetUtil.commendation(Integer.valueOf(postId), createUserId, Double.valueOf(season), projectId, new NetUtil.SaveCommendationImp() {
                            @Override
                            public void finishCommendation(String commendationNum, String donateNum, boolean status) {
                                if (status) {
//                                    tvCommendationNum.setText(getString(R.string.sponsor) + commendationNum);
//                                    tvDonateNum.setText(donateNum + getString(R.string.sponsor_num));
                                    initData();
                                }
                            }
                        });
                    }
                });
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                window.showAtLocation(findViewById(R.id.head_app_server), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
                break;
            case R.id.tv_comments_num:
                IntentUtil.startCommentActivity(String.valueOf(postId),Constants.EVLUATION_COMMENT_LIST,
                        shareUrl,tvPostTitle.getText().toString(),postShortDesc);
                break;
        }
    }

    private void setPraise(boolean isPraise, int postId) {
        NetUtil.setPraise(isPraise, postId, new NetUtil.SaveFollowImpl() {
            @Override
            public void finishFollow(String praiseNum, boolean status) {
                tvPraiseStatus.setEnabled(true);
                ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                if (!praiseNum.equals(Constants.PRAISE_ERROR)) {
                    DetailsReviewAllActivity.this.praiseNum = Integer.valueOf(praiseNum);
                    tvPraiseStatus.setSelected(status);
                    tvPraiseStatus.setText("赞" + praiseNum);
                }

            }
        });
    }

    private TextView buildLabel(final String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        textView.setTextColor(getResources().getColor(R.color.app_background));
        textView.setPadding((int) dpToPx(5), (int) dpToPx(3), (int) dpToPx(5), (int) dpToPx(3));
        textView.setBackgroundResource(R.drawable.garden_crack);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
