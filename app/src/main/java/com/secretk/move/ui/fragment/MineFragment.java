package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.presenter.MineFragmentPresenter;
import com.secretk.move.presenter.impl.MineFragmentPresenterImpl;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.activity.HttpDomeActivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.MineApproveSubmitiCertificateActivity;
import com.secretk.move.ui.activity.MineAttentionActivity;
import com.secretk.move.ui.activity.MineCheckDetailsActivity;
import com.secretk.move.ui.activity.MineCollectActivity;
import com.secretk.move.ui.activity.MineMessageActivity;
import com.secretk.move.ui.activity.MineMoneyGuideActivity;
import com.secretk.move.ui.activity.MineOpinionBackActivity;
import com.secretk.move.ui.activity.MineRecommendActivity;
import com.secretk.move.ui.activity.MineSetActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.FragmentMineView;
import com.secretk.move.view.RecycleNestedScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 作者： litongge
 * 时间： 2018/5/16 14:29
 * 邮箱；ltg263@126.com
 * 描述：我的界面
 */
public class MineFragment extends LazyFragment implements FragmentMineView {

    MineFragmentPresenter presenter;
    @BindView(R.id.iv_my_set)
    ImageView ivMySet;
    @BindView(R.id.iv_head_notLogin)
    ImageView ivHeadNotLogin;
    @BindView(R.id.tv_go_login)
    TextView tvGoLogin;
    @BindView(R.id.tv_message_sum)
    TextView tvMessageSum;
    @BindView(R.id.tv_go_register)
    TextView tvGoRegister;
    @BindView(R.id.rl_no_login)
    RelativeLayout rlNoLogin;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_fans_num)
    TextView tvFansNum;
    @BindView(R.id.tv_user_state)
    TextView tvUserState;
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.tv_evaluation_mun)
    TextView tvEvaluationMun;
    @BindView(R.id.tv_evaluation)
    TextView tvEvaluation;
    @BindView(R.id.rl_appraisal)
    RelativeLayout rlAppraisal;
    @BindView(R.id.tv_discuss_mun)
    TextView tvDiscussMun;
    @BindView(R.id.tv_discuss)
    TextView tvDiscuss;
    @BindView(R.id.rl_discuss)
    RelativeLayout rlDiscuss;
    @BindView(R.id.tv_article_mun)
    TextView tvArticleMun;
    @BindView(R.id.tv_user_type)
    TextView tvUserType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_model_icon)
    ImageView ivModelIcon;
    @BindView(R.id.tv_user_card_status)
    TextView tvUserCardStatus;
    @BindView(R.id.tv_article)
    TextView tvArticle;
    @BindView(R.id.rl_mes_num)
    RelativeLayout rlMesNum;
    @BindView(R.id.rl_essay)
    RelativeLayout rlEssay;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.ll_check_details)
    LinearLayout llCheckDetails;
    @BindView(R.id.ll_my_attention)
    LinearLayout llMyAttention;
    @BindView(R.id.ll_my_collect)
    LinearLayout llMyCollect;
    @BindView(R.id.ll_my_recommend)
    LinearLayout llMyRecommend;
    @BindView(R.id.ll_my_about)
    LinearLayout llMyAbout;
    @BindView(R.id.ll_my_feedback)
    LinearLayout llMyFeedback;
    @BindView(R.id.sv)
    RecycleNestedScrollView sv;
    private UserLoginInfo.DataBean.UserBean userInfos;
    private RelativeLayout rlActivity;
    private TextView tvActivity;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_mine;
    }
    public void setMessageView(RelativeLayout rlActivity,TextView tvActivity) {
        this.rlActivity=rlActivity;
        this.tvActivity = tvActivity;
    }

    @Override
    public void initViews() {
        LogUtil.w("rlActivity:"+rlActivity);
        sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if(scrollY <211){
//                    rl.setAlpha(scrollY/150f);
//                }
                if(scrollY>70){
                    rl.setVisibility(View.VISIBLE);
                    StatusBarUtil.setLightMode(getActivity());
                    StatusBarUtil.setColor(getActivity(), UiUtils.getColor(R.color.background_gray), 0);
                }else{
                    rl.setVisibility(View.GONE);
                    StatusBarUtil.setLightMode(getActivity());
                    StatusBarUtil.setColor(getActivity(), UiUtils.getColor(R.color.white), 0);
                }
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        LogUtil.w("");
        if (isLoginZt) {
            rlNoLogin.setVisibility(View.GONE);
            rlUserInfo.setVisibility(View.VISIBLE);
            presenter = new MineFragmentPresenterImpl(this);
            presenter.initialized();
        } else {
            tvEvaluationMun.setText("0");
            tvDiscussMun.setText("0");
            tvArticleMun.setText("0");
            tvBalance.setText("0");
            rlNoLogin.setVisibility(View.VISIBLE);
            rlUserInfo.setVisibility(View.GONE);
        }
    }


    @Override
    public void loadInfoSuccess(UserLoginInfo.DataBean.UserBean infos) {
        userInfos = infos;
        GlideUtils.loadCircleUserUrl(getActivity(), ivHeadImg, Constants.BASE_IMG_URL + StringUtil.getBeanString(infos.getIcon()));
        tvUserName.setText(StringUtil.getBeanString(infos.getUserName()));
        tvTitle.setText(StringUtil.getBeanString(infos.getUserName()));
        //300 粉丝 •1568 赞
        String fansNum = String.valueOf(infos.getFansNum()) + "  粉丝 • " + String.valueOf(infos.getPraiseNum()) + " 赞";
        tvFansNum.setText(fansNum);
        tvUserState.setText(StringUtil.getBeanString(infos.getUserSignature()));
        tvEvaluationMun.setText(String.valueOf(infos.getEvaluationNum()));
        tvDiscussMun.setText(String.valueOf(infos.getDiscussNum()));
        tvArticleMun.setText(String.valueOf(infos.getArticleNum()));
        tvBalance.setText(String.valueOf(infos.getKffCoinNum()));
        // //用户类型，数字，用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
        if (infos.getUserType() != 1) {
            tvUserType.setVisibility(View.VISIBLE);
            ivModelIcon.setVisibility(View.VISIBLE);
            tvUserType.setText(StringUtil.getUserType(infos.getUserType(), ivModelIcon));
        } else {
            tvUserType.setVisibility(View.GONE);
            ivModelIcon.setVisibility(View.GONE);
        }
        int userCardStatus = sharedUtils.get("userCardStatus", 0);
        tvUserCardStatus.setTextColor(getResources().getColor(R.color.gplus_color_5));
        if (userCardStatus == 2) {
            tvUserCardStatus.setTextColor(getResources().getColor(R.color.title_gray_66));
            tvUserCardStatus.setText("已认证");
        } else if (userCardStatus == 1) {// 1  待审核  2   审核通过  3   未通过审核  4   未提交
            tvUserCardStatus.setText("审核中");
        } else if (userCardStatus == 3) {
            tvUserCardStatus.setText("未通过审核");
        } else {
            tvUserCardStatus.setText("去认证");
        }
    }

    @OnClick({R.id.iv_my_set, R.id.ll_my_approve, R.id.tv_go_login, R.id.tv_go_register, R.id.rl_user_info,
            R.id.rl_appraisal, R.id.rl_discuss, R.id.rl_essay, R.id.ll_check_details, R.id.ll_my_attention,
            R.id.ll_my_collect, R.id.ll_my_message, R.id.ll_my_recommend, R.id.ll_my_about, R.id.ll_my_feedback,
            R.id.btn,R.id.tv_zb})
    public void onViewClicked(View view) {
        if (!isLoginZt) {
            IntentUtil.startActivity(LoginHomeActivity.class);
            return;
        }
        String key[] = {"currentType"};
        switch (view.getId()) {
            case R.id.iv_my_set:
                if (userInfos != null) {
                    Intent intent = new Intent(getActivity(), MineSetActivity.class);
                    intent.putExtra(Constants.USER_INFOS, userInfos);
                    startActivityForResult(intent, 0);
                }
                break;
            case R.id.tv_go_login://登陆
                IntentUtil.startActivity(LoginHomeActivity.class);
                break;
            case R.id.tv_go_register://注册
                break;
            case R.id.rl_user_info://用户Head
                if (userInfos != null) {
                    Intent intent = new Intent(getActivity(), MineSetActivity.class);
                    intent.putExtra(Constants.USER_INFOS, userInfos);
                    startActivityForResult(intent, 0);
                }
                break;
            case R.id.rl_appraisal://评测
                String value0[] = {String.valueOf(0)};
                IntentUtil.startActivity(HomeActivity.class, key, value0);
                break;
            case R.id.rl_discuss://讨论
                String value1[] = {String.valueOf(1)};
                IntentUtil.startActivity(HomeActivity.class, key, value1);
                break;
            case R.id.rl_essay://文章
                String value2[] = {String.valueOf(2)};
                IntentUtil.startActivity(HomeActivity.class, key, value2);
                break;
            case R.id.ll_my_approve://实名认证
                IntentUtil.startActivity(MineApproveSubmitiCertificateActivity.class);
                break;
            case R.id.ll_check_details://资产明细
                IntentUtil.startActivity(MineCheckDetailsActivity.class);
                break;
            case R.id.ll_my_attention://我的关注
                IntentUtil.startActivity(MineAttentionActivity.class);
                break;
            case R.id.ll_my_collect://收藏
                IntentUtil.startActivity(MineCollectActivity.class);
                break;
            case R.id.ll_my_message://消息
                IntentUtil.startActivity(MineMessageActivity.class);
                break;
            case R.id.ll_my_recommend://推荐好友
                IntentUtil.startActivity(MineRecommendActivity.class);
                break;
            case R.id.ll_my_about://关于我们
                IntentUtil.startWebViewActivity(Constants.ABOUT, "关于我们");
                break;
            case R.id.ll_my_feedback://意见反馈
                IntentUtil.startActivity(MineOpinionBackActivity.class);
//                IntentUtil.startActivity(ImagesSelectorActivity.class);
                break;
            case R.id.tv_zb://赚币指南
                IntentUtil.startActivity(MineMoneyGuideActivity.class);
//                IntentUtil.startActivity(ImagesSelectorActivity.class);
                break;
            case R.id.btn://意见反馈
                IntentUtil.startActivity(HttpDomeActivity.class);
//                IntentUtil.startActivity(ImagesSelectorActivity.class);
                //logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share)
//                Bitmap bitmap = ZXingUtils.createQRImage("https://blog.csdn.net/pxcz110112/article/details/80234997", 600, 600,null);
//                ivHeadImg.setImageBitmap(bitmap);
                break;
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        LogUtil.w("requestCode:"+requestCode);
//        LogUtil.w("data:"+data);
//        if(requestCode==0 && data!=null){
//            LogUtil.w("data:"+data.getExtras().getBoolean(Constants.REQUEST_CODE));
//            if(data.getExtras().getBoolean(Constants.REQUEST_CODE)){
////                onFirstUserVisible();
//            }
//        }
//    }


    @Override
    public void onResume() {
        super.onResume();
        isLoginZt = sharedUtils.get(Constants.IS_LOGIN_KEY, false);
        if (isLoginZt) {
            token = sharedUtils.get(Constants.TOKEN_KEY, "");
            if (StringUtil.isNotBlank(token)) {
                getUserInfo(token);
            }
        } else {
            token = "";
            onFirstUserVisible();
        }
    }

    private void getUserInfo(String token) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = Constants.GET_USER_INFO;
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, UserLoginInfo.class, new HttpCallBackImpl<UserLoginInfo>() {
            @Override
            public void onCompleted(UserLoginInfo userInfo) {
                ////用户信息
                //登录的状态
                sharedUtils.put(Constants.IS_LOGIN_KEY, true);
                ////用户信息
//                UserLoginInfo.DataBean dataBean = userInfo.getData();
                sharedUtils.put(Constants.USER_INFOS, userInfo.getData().getUser().toString());
                sharedUtils.put(Constants.USER_TYPE, userInfo.getData().getUser().getUserType());
                sharedUtils.put(Constants.MOBILE, userInfo.getData().getUser().getMobile());
                sharedUtils.put(Constants.USER_ID, userInfo.getData().getUser().getUserId());
                sharedUtils.put(Constants.KFF_COIN_NUM, String.valueOf(userInfo.getData().getUser().getKffCoinNum()));
                String mesSum = "";
                int messageSum = userInfo.getData().getMessageSum();
                rlMesNum.setVisibility(View.GONE);
                if(rlActivity != null){
                    rlActivity.setVisibility(View.GONE);
                }
                if(messageSum>0){
                    if(rlActivity != null){
                        rlActivity.setVisibility(View.VISIBLE);
                    }
                    rlMesNum.setVisibility(View.VISIBLE);
                    if(messageSum<100){
                        mesSum = String.valueOf(messageSum);
                    }else{
                        mesSum = "99+";
                    }
                    tvMessageSum.setText(mesSum);
                    if(tvActivity != null){
                        tvActivity.setText(mesSum);
                    }
                }
                sharedUtils.put("invaUIH", userInfo.getData().getInvaUIH());
                sharedUtils.put("awardToken", userInfo.getData().getAwardToken());
                sharedUtils.put("userCardStatus", userInfo.getData().getUserCardStatus());
                sharedUtils.put("statusHierarchyType", userInfo.getData().getStatusHierarchyType());
                onFirstUserVisible();

            }

            @Override
            public void onError(String message) {
                DialogUtils.showDialogHint(getActivity(), "帐号或密码错误请重新登陆",
                        true, new DialogUtils.ErrorDialogInterface() {
                            @Override
                            public void btnConfirm() {
                                SharedUtils.singleton().clear();
                                IntentUtil.startActivity(LoginHomeActivity.class);
                            }
                        });
            }
        });
    }
}
