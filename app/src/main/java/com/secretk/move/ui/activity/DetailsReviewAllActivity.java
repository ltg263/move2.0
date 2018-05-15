package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
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
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.PileLayout;
import com.secretk.move.view.ProgressBarStyleView;

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
    @BindView(R.id.pb_project_location)
    ProgressBarStyleView pbProjectLocation;
    @BindView(R.id.pb_technical_framework)
    ProgressBarStyleView pbTechnicalFramework;
    @BindView(R.id.pb_team_strength)
    ProgressBarStyleView pbTeamStrength;
    @BindView(R.id.pb_project_schedule)
    ProgressBarStyleView pbProjectSchedule;
    @BindView(R.id.pb_speculative_risk)
    ProgressBarStyleView pbSpeculativeRisk;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;
    @BindView(R.id.tv_post_short_desc)
    TextView tvPostShortDesc;
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
    private int createUserId;
    private ImagesAdapter adapter;
    private int projectId;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_details_review_all;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setToolbarListener(0);
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0, new MenuInfo(R.string.share, "分享", R.drawable.ic_share));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        setVerticalManager(rvImg);
        adapter = new ImagesAdapter(this);
        rvImg.setAdapter(adapter);
    }

    protected void initData() {
        if(!NetUtil.isNetworkAvailable()){
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
        loadingDialog.show();
        RetrofitUtil.request(params, DetailsReviewBean.class, new HttpCallBackImpl<DetailsReviewBean>() {
            @Override
            public void onCompleted(DetailsReviewBean bean) {
                initUiData(bean.getData().getEvaluationDetail());
            }

            @Override
            public void onFinish() {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }

    private void initUiData(DetailsReviewBean.DataBean.EvaluationDetailBean evaluationDetail) {
        mHeadView.setTitle(evaluationDetail.getProjectCode());
        mHeadView.setTitleVice("/"+evaluationDetail.getProjectChineseName());
        GlideUtils.loadCircleUrl(mHeadView.getImageView(), Constants.BASE_IMG_URL + evaluationDetail.getProjectIcon());
        createUserId = evaluationDetail.getCreateUserId();
        projectId  = evaluationDetail.getProjectId();
        tvPostTitle.setText(evaluationDetail.getPostTitle());
        tvTotalScore.setText(String.valueOf(evaluationDetail.getTotalScore()));
        GlideUtils.loadCircleUrl(ivCreateUserIcon,Constants.BASE_IMG_URL+evaluationDetail.getCreateUserIcon());
        tvCreateUserName.setText(evaluationDetail.getCreateUserName());
        tvCreateUserSignature.setText(evaluationDetail.getCreateUserSignature());
        //,//"0 未关注；1-已关注；2-不显示关注按钮"\
        if (evaluationDetail.getFollowStatus() == 0) {
            tvFollowStatus.setText(getString(R.string.follow_status_0));
        } else if (evaluationDetail.getFollowStatus() == 1) {
            tvFollowStatus.setText(getString(R.string.follow_status_1));
        } else {
            tvFollowStatus.setVisibility(View.GONE);
        }
        tvPostShortDesc.setText(evaluationDetail.getPostShortDesc());
        tvCreateTime.setText(StringUtil.getTimeToM(evaluationDetail.getCreateTime()));
        tvDonateNum.setText(evaluationDetail.getDonateNum()+getString(R.string.sponsor_num));
        tvPraiseStatus.setText(getString(R.string.like)+ String.valueOf(evaluationDetail.getPraiseNum()));
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
        tvCommentsNum.setText(getString(R.string.comment)  + String.valueOf(evaluationDetail.getCollectNum()));


        pbComprehensiveEvaluation.setTvOne(getResources().getString(R.string.comprehensive_evaluation),0,
                getResources().getColor(R.color.title_gray));
        pbComprehensiveEvaluation.setTvThree(evaluationDetail.getTotalScore(),16,R.color.app_background);
        try {
            initProfessionalData(evaluationDetail.getProfessionalEvaDetail());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String evaluationTags = evaluationDetail.getEvaluationTags();
        if(StringUtil.isNotBlank(evaluationTags)){
            try {
                JSONArray array = new JSONArray(evaluationTags);
                for(int i = 0;i<array.length();i++){
                    String tagName = array.getJSONObject(i).getString("tagName");
                    TextView tv = buildLabel(tagName);
                    flEvaluationTags.addView(tv);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        List<PostDataInfo> lists = new ArrayList<>();
        try {
            JSONArray images = new JSONArray(evaluationDetail.getPostSmallImages());
            for (int i = 0; i < images.length() && i>7; i++) {
                JSONObject strObj = images.getJSONObject(i);
                PostDataInfo info = new PostDataInfo();
                info.setUrl(strObj.getString("fileUrl"));
                info.setName(strObj.getString("fileName"));
                info.setTitle(strObj.getString("extension"));
                lists.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter.setData(lists);
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
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < pileLists.size(); i++) {
            ImageView imageView = (ImageView) inflater.inflate(R.layout.item_praise, pileLayout, false);
            GlideUtils.loadCircleUrl(imageView, Constants.BASE_IMG_URL + pileLists.get(i).getSendUserIcon());
            pileLayout.addView(imageView);
        }
    }

    /**
     * 设置评分进度
     * @param eva
     * @throws JSONException
     */
    private void initProfessionalData(String eva) throws JSONException {
        JSONArray object = new JSONArray(eva);
        for(int i=0;i<object.length();i++){
            String projectName = object.getJSONObject(i).getString("detailName");
            int detailWeight = object.getJSONObject(i).getInt("detailWeight");
            int raterNum = object.getJSONObject(i).getInt("raterNum");
            double totalScor= object.getJSONObject(i).getDouble("totalScore");
            String two = "/ "+String.valueOf(detailWeight)+"% ("+raterNum+"人)";
            if (getString(R.string.project_location).equals(projectName)) {
                pbProjectLocation.setVisibility(View.VISIBLE);
                pbProjectLocation.setProgressDrawable(R.drawable.pb_view_xmdw, R.color.xmdw);
                pbProjectLocation.setAllTv(getResources().getString(R.string.project_location),
                        two, totalScor);
            }
            if (getString(R.string.technical_framework).equals(projectName)) {
                pbTechnicalFramework.setVisibility(View.VISIBLE);
                pbTechnicalFramework.setProgressDrawable(R.drawable.pb_view_jskj, R.color.jskj);
                pbTechnicalFramework.setAllTv(getResources().getString(R.string.technical_framework),
                        two, totalScor);
            }
            if (getString(R.string.team_strength).equals(projectName)) {
                pbTeamStrength.setVisibility(View.VISIBLE);
                pbTeamStrength.setProgressDrawable(R.drawable.pb_view_tdsl, R.color.tdsl);
                pbTeamStrength.setAllTv(getResources().getString(R.string.team_strength),
                        two, totalScor);
            }
            if (getString(R.string.project_schedule).equals(projectName)) {
                pbProjectSchedule.setVisibility(View.VISIBLE);
                pbProjectSchedule.setProgressDrawable(R.drawable.pb_view_xmjd, R.color.xmjd);
                pbProjectSchedule.setAllTv(getResources().getString(R.string.project_schedule),
                        two, totalScor);
            }
            if (getString(R.string.speculative_risk).equals(projectName)) {
                pbSpeculativeRisk.setVisibility(View.VISIBLE);
                pbSpeculativeRisk.setProgressDrawable(R.drawable.pb_view_tzfx, R.color.tzfx);
                pbSpeculativeRisk.setAllTv(getResources().getString(R.string.speculative_risk),
                        two, totalScor);
            }
        }
    }


    @OnClick({R.id.rl_ge_ren,R.id.tv_follow_status, R.id.tv_praise_status, R.id.tv_collect_status, R.id.tv_commendation_Num, R.id.tv_comments_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_ge_ren:
                IntentUtil.startHomeActivity(createUserId);
                break;
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus.getText().toString().trim(),
                        Constants.SaveFollow.USER,Integer.valueOf(createUserId), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
            case R.id.tv_praise_status:
                tvPraiseStatus.setEnabled(false);
                setPraise(tvPraiseStatus.isSelected(),Integer.valueOf(postId));
                break;
            case R.id.tv_collect_status:
                tvCollectStatus.setEnabled(false);
                NetUtil.saveCollect(!tvCollectStatus.isSelected(),
                        Integer.valueOf(postId), new NetUtil.SaveCollectImp() {
                            @Override
                            public void finishCollect(String str,boolean status) {
                                tvCollectStatus.setEnabled(true);
                                if(!str.equals(Constants.COLLECT_ERROR)){
                                    tvCollectStatus.setSelected(status);
                                }
                            }
                        });
                break;
            case R.id.tv_commendation_Num:
                DialogUtils.showEditTextDialog(this, "赞助", new DialogUtils.EditTextDialogInterface() {
                    @Override
                    public void btnConfirm(String season) {
                        NetUtil.commendation(Integer.valueOf(postId), createUserId,Double.valueOf(season), projectId, new NetUtil.SaveCommendationImp() {
                            @Override
                            public void finishCommendation(String commendationNum, String donateNum, boolean status) {
                                if(status){
                                    tvCommendationNum.setText(getString(R.string.sponsor)+ commendationNum);
                                    tvDonateNum.setText(donateNum+getString(R.string.sponsor_num));
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.tv_comments_num:
                Intent intent = new Intent(this,DetailsArticleCommentActivity.class);
                intent.putExtra("postId",String.valueOf(postId));
                intent.putExtra("url",Constants.EVLUATION_COMMENT_LIST);
                startActivity(intent);
                break;
        }
    }
    private void setPraise(boolean isPraise, int postId) {
        NetUtil.setPraise(isPraise, postId, new NetUtil.SaveFollowImpl() {
            @Override
            public void finishFollow(String praiseNum,boolean status) {
                tvPraiseStatus.setEnabled(true);
                ////点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
                if(!praiseNum.equals(Constants.PRAISE_ERROR)){
                    tvPraiseStatus.setSelected(status);
                    tvPraiseStatus.setText("赞"+praiseNum);
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
                ToastUtils.getInstance().show("dinada");
            }
        });
        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
