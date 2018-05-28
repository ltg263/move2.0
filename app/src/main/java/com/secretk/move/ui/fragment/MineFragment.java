package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.presenter.MineFragmentPresenter;
import com.secretk.move.presenter.impl.MineFragmentPresenterImpl;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.MineCollectActivity;
import com.secretk.move.ui.activity.MineSetActivity;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.view.FragmentMineView;

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
        GlideUtils.loadCircleUrl(ivHeadImg, Constants.BASE_IMG_URL+infos.getIcon());
        tvUserName.setText(infos.getUserName());
        //300 粉丝 •1568 赞
        String fansNum = String.valueOf(infos.getFansNum())+"  粉丝 • "+String.valueOf(infos.getPraiseNum())+" 赞";
        tvFansNum.setText(fansNum);
        tvUserState.setText(infos.getUserSignature());
        tvEvaluationMun.setText(String.valueOf(infos.getEvaluationNum()));
        tvDiscussMun.setText(String.valueOf(infos.getDiscussNum()));
        tvArticleMun.setText(String.valueOf(infos.getArticleNum()));
        tvBalance.setText(String.valueOf(infos.getKffCoinNum()));
    }

    @OnClick({R.id.btn,R.id.iv_my_set, R.id.tv_go_login, R.id.tv_go_register, R.id.rl_user_info, R.id.rl_appraisal, R.id.rl_discuss, R.id.rl_essay, R.id.ll_check_details, R.id.ll_my_attention, R.id.ll_my_collect, R.id.ll_my_recommend, R.id.ll_my_about, R.id.ll_my_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_set:
                if(userInfos!=null){
                    Intent intent = new Intent(getActivity(),MineSetActivity.class);
                    intent.putExtra(Constants.USER_INFOS, userInfos);
                    startActivityForResult(intent,0);
                }
                break;
            case R.id.tv_go_login:
                IntentUtil.startActivity(LoginHomeActivity.class);
                break;
            case R.id.tv_go_register:
                break;
            case R.id.rl_user_info:
                IntentUtil.startActivity(HomeActivity.class);
                break;
            case R.id.rl_appraisal:
                break;
            case R.id.rl_discuss:
                break;
            case R.id.rl_essay:
                break;
            case R.id.ll_check_details:
                break;
            case R.id.ll_my_attention:
                break;
            case R.id.ll_my_collect:
                IntentUtil.startActivity(MineCollectActivity.class);
                break;
            case R.id.ll_my_recommend:
                break;
            case R.id.ll_my_about:
                break;
            case R.id.ll_my_feedback:
                break;
            case R.id.btn:
                sharedUtils.clear();
                IntentUtil.startActivity(LoginHomeActivity.class);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.w("requestCode:"+requestCode);
        LogUtil.w("data:"+data);
        if(requestCode==0 && data!=null){
            LogUtil.w("data:"+data.getExtras().getBoolean(Constants.REQUEST_CODE));
            if(data.getExtras().getBoolean(Constants.REQUEST_CODE)){
                onFirstUserVisible();
            }
        }
    }
}
