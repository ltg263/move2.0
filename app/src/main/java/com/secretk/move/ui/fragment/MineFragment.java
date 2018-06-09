package com.secretk.move.ui.fragment;

import android.content.Intent;
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
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.MineApproveSubmitiCertificateActivity;
import com.secretk.move.ui.activity.MineAttentionActivity;
import com.secretk.move.ui.activity.MineCheckDetailsActivity;
import com.secretk.move.ui.activity.MineCollectActivity;
import com.secretk.move.ui.activity.MineOpinionBackActivity;
import com.secretk.move.ui.activity.MineRecommendActivity;
import com.secretk.move.ui.activity.MineSetActivity;
import com.secretk.move.ui.activity.WebViewActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.FragmentMineView;

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
    @BindView(R.id.tv_article)
    TextView tvArticle;
    @BindView(R.id.rl_essay)
    RelativeLayout rlEssay;
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
    private UserLoginInfo.DataBean.UserBean userInfos;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initViews() {
    }

    @Override
    public void onFirstUserVisible() {
        LogUtil.w("");
        if(isLoginZt){
            rlNoLogin.setVisibility(View.GONE);
            rlUserInfo.setVisibility(View.VISIBLE);
            presenter = new MineFragmentPresenterImpl(this);
            presenter.initialized();
        }else{
            rlNoLogin.setVisibility(View.VISIBLE);
            rlUserInfo.setVisibility(View.GONE);
        }
    }


    @Override
    public void loadInfoSuccess(UserLoginInfo.DataBean.UserBean infos) {
        userInfos = infos;
        GlideUtils.loadCircleUserUrl(getActivity(),ivHeadImg, Constants.BASE_IMG_URL+infos.getIcon());
        LogUtil.w("img:"+Constants.BASE_IMG_URL+infos.getIcon());
//        x.image().bind(ivHeadImg, Constants.BASE_IMG_URL+infos.getIcon(), mOptions);
        tvUserName.setText(infos.getUserName());
        //300 粉丝 •1568 赞
        String fansNum = String.valueOf(infos.getFansNum())+"  粉丝 • "+String.valueOf(infos.getPraiseNum())+" 赞";
        tvFansNum.setText(fansNum);
        tvUserState.setText(infos.getUserSignature());
        tvEvaluationMun.setText(String.valueOf(infos.getEvaluationNum()));
        tvDiscussMun.setText(String.valueOf(infos.getDiscussNum()));
        tvArticleMun.setText(String.valueOf(infos.getArticleNum()));
        tvBalance.setText(String.valueOf(infos.getKffCoinNum()));
        // //用户类型，数字，用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
        tvUserType.setText(StringUtil.getUserType(infos.getUserType()));
    }

    @OnClick({R.id.iv_my_set,R.id.ll_my_approve, R.id.tv_go_login, R.id.tv_go_register, R.id.rl_user_info, R.id.rl_appraisal, R.id.rl_discuss, R.id.rl_essay, R.id.ll_check_details, R.id.ll_my_attention, R.id.ll_my_collect, R.id.ll_my_recommend, R.id.ll_my_about, R.id.ll_my_feedback})
    public void onViewClicked(View view) {
        if(!isLoginZt){
            IntentUtil.startActivity(LoginHomeActivity.class);
            return;
        }
        String key[] = {"currentType"};
        switch (view.getId()) {
            case R.id.iv_my_set:
                if(userInfos!=null){
                    Intent intent = new Intent(getActivity(),MineSetActivity.class);
                    intent.putExtra(Constants.USER_INFOS, userInfos);
                    startActivityForResult(intent,0);
                }
                break;
            case R.id.tv_go_login://登陆
                IntentUtil.startActivity(LoginHomeActivity.class);
                break;
            case R.id.tv_go_register://注册
                break;
            case R.id.rl_user_info://用户Head
                IntentUtil.startActivity(HomeActivity.class);
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
            case R.id.ll_my_recommend://推荐好友
                IntentUtil.startActivity(MineRecommendActivity.class);
                break;
            case R.id.ll_my_about://关于我们
                startActivity(new Intent(getActivity(), WebViewActivity.class).putExtra("url", Constants.HELP));
                break;
            case R.id.ll_my_feedback://意见反馈
                IntentUtil.startActivity(MineOpinionBackActivity.class);
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
        isLoginZt = sharedUtils.get(Constants.IS_LOGIN_KEY,false);
        if(isLoginZt){
            token = sharedUtils.get(Constants.TOKEN_KEY,"");
            if(StringUtil.isNotBlank(token)){
                getUserInfo(token);
            }
        }else{
            token="";
            onFirstUserVisible();
        }
    }

    private  void getUserInfo(String token) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url =Constants.GET_USER_INFO;
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, UserLoginInfo.class, new HttpCallBackImpl<UserLoginInfo>() {
            @Override
            public void onCompleted(UserLoginInfo userInfo) {
                //将token存入Shared中
//                sharedUtils.put(Constants.TOKEN_KEY,userInfo.getData().getToken());
                //登录的状态
                sharedUtils.put(Constants.IS_LOGIN_KEY,true);
                ////用户信息
                sharedUtils.put(Constants.USER_INFOS,userInfo.getData().getUser().toString());
                sharedUtils.put(Constants.USER_TYPE,userInfo.getData().getUser().getUserType());
                sharedUtils.put(Constants.MOBILE,userInfo.getData().getUser().getMobile());
                onFirstUserVisible();
            }

            @Override
            public void onError(String message) {
            }
        });
    }
}
