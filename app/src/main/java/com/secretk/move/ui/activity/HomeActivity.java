package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.HomeUserIndexBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.HomePageAdapter;
import com.secretk.move.ui.fragment.HomeArticleFragment;
import com.secretk.move.ui.fragment.HomeDiscussFragment;
import com.secretk.move.ui.fragment.HomeReviewFragment;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.ViewPagerFixed;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/4/27 13:41
 * 邮箱；ltg263@126.com
 * 描述：我的主页 加载三个Fragment：
 * HomeReviewFragment      测评
 * HomeDiscussFragment     讨论
 * HomeArticleFragment     文章
 */

public class HomeActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.iv_model_type)
    ImageView ivModelType;
    @BindView(R.id.tv_evaluating_sign)
    TextView tvEvaluatingSign;
    @BindView(R.id.tv_save_follow)
    TextView tvSaveFollow;
    @BindView(R.id.tv_answer)
    TextView tvAnswer;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.tv_individual_resume)
    TextView tvIndividualResume;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPagerFixed viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    public static final int HOME_REVIEW_FRAGMENT = 0;
    public static final int HOME_DISCUSS_FRAGMENT = 1;
    public static final int HOME_ARTICLE_FRAGMENT = 2;
    String userId;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    private String homePageTitle;
    private String currentType;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_home;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    HomeReviewFragment reviewFragment;
    HomeDiscussFragment discussFragment;
    HomeArticleFragment articleFragment;

    @Override
    protected void initUI(Bundle savedInstanceState) {
        userId = getIntent().getStringExtra("userId");
        currentType = getIntent().getStringExtra("currentType");
        refreshLayout.setEnableRefresh(false);//禁止下拉刷新
        reviewFragment = new HomeReviewFragment();
        discussFragment = new HomeDiscussFragment();
        articleFragment = new HomeArticleFragment();
        reviewFragment.setSmartRefreshLayout(refreshLayout);
        discussFragment.setSmartRefreshLayout(refreshLayout);
        articleFragment.setSmartRefreshLayout(refreshLayout);
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        adapter.addFragment(reviewFragment, getString(R.string.review));
        adapter.addFragment(discussFragment, getString(R.string.discuss));
        adapter.addFragment(articleFragment, getString(R.string.article));
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        if (StringUtil.isNotBlank(currentType)) {
            viewPager.setCurrentItem(Integer.valueOf(currentType));
        } else {
            viewPager.setCurrentItem(1);
        }
        viewPager.setOffscreenPageLimit(3);
        tabs.post(new Runnable() {
            @Override
            public void run() {
                StatusBarUtil.setIndicator(tabs, 40, 40);
            }
        });
        initListener();
    }

    public String getUserId() {
        return userId;
    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            //查看自己不用传userId只用token就可以，查看他人需要传入他人userID
            if (StringUtil.isNotBlank(userId)) {
                node.put("userId", Integer.valueOf(userId));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERHOME_INDEX)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, HomeUserIndexBean.class, new HttpCallBackImpl<HomeUserIndexBean>() {
            @Override
            public void onCompleted(HomeUserIndexBean userInfo) {
                HomeUserIndexBean.DataBean.UserBean userData = userInfo.getData().getUser();
                String iconUrl = Constants.BASE_IMG_URL + userData.getIcon();
                GlideUtils.loadCircleUserUrl(HomeActivity.this, ivHead, iconUrl);
                GlideUtils.loadCircleUserUrl(HomeActivity.this, mHeadView.getImageView(), iconUrl);
                mHeadView.setTitle(userData.getHomePageTitle());
                tvUserName.setText(userData.getUserName());
                tvIndividualResume.setText(userData.getUserSignature());
                //"userType": 1,// 用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
                if (userData.getUserType() != 1) {
                    tvEvaluatingSign.setVisibility(View.VISIBLE);
                    ivModelType.setVisibility(View.VISIBLE);
                    tvEvaluatingSign.setText(StringUtil.getUserType(userData.getUserType(), ivModelType));
                }
                //“showFollow”: 0 , //是否显示 关注按钮 0- 不显示；1-显示关注  2-显示取消关注
                if (userData.getShowFollow() == 1) {
                    tvSaveFollow.setSelected(false);
                    tvSaveFollow.setText(getResources().getString(R.string.follow_status_0));
                } else {
                    tvSaveFollow.setSelected(true);
                    tvSaveFollow.setText(getResources().getString(R.string.follow_status_1));
                }

                if (baseUserId == userData.getUserId()) {
                    tvSaveFollow.setVisibility(View.GONE);
                }
                homePageTitle = userData.getHomePageTitle();
                mHeadView.setTitle(homePageTitle);
                tvAnswer.setText(String.valueOf(userData.getTotalPostNum()));
                tvPraise.setText(String.valueOf(userData.getPraiseNum()));
                tvFans.setText(String.valueOf(userData.getFansNum()));
            }
        });
    }

    public LoadingDialog getLoadingDialog() {
        return loadingDialog;
    }

    @OnClick({R.id.tv_save_follow, R.id.ll_fans})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save_follow:
                tvSaveFollow.setEnabled(false);
                NetUtil.addSaveFollow(tvSaveFollow,
                        Constants.SaveFollow.USER, Integer.valueOf(userId), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                tvSaveFollow.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvSaveFollow.setText(str);
                                    tvSaveFollow.setSelected(tvSaveFollow.getText().toString().trim()
                                            .equals(getResources().getString(R.string.follow_status_1)));
                                }
                            }
                        });
                break;
            case R.id.ll_fans:
//                String key[] = {"userId"};
//                String values[] = {String.valueOf(userId)};
//                IntentUtil.startActivity(HomeFansActivity.class, key, values);
                break;
        }
    }


    /**
     * 各种滑动监听
     */
    private void initListener() {
        /**
         * 设置Toolbar的变化
         */
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //if (Math.abs(verticalOffset) == DensityUtil.dp2px(200)-mHeadView.getHeight()) {//关闭
                if (Math.abs(verticalOffset) > 200) {//关闭
                    mHeadView.getImageView().setVisibility(View.VISIBLE);
                    mHeadView.getTextView().setVisibility(View.GONE);
                } else {  //展开
                    mHeadView.getImageView().setVisibility(View.GONE);
                    mHeadView.getTextView().setVisibility(View.VISIBLE);
                    mHeadView.setTitle(homePageTitle);
                }
            }
        });
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                switch (viewPager.getCurrentItem()) {
                    case HOME_REVIEW_FRAGMENT:
                        if (reviewFragment.isHaveData) {
                            reviewFragment.getLoadData();
                        }
                        break;
                    case HOME_DISCUSS_FRAGMENT:
                        if (discussFragment.isHaveData) {
                            discussFragment.getLoadData();
                        }
                        break;
                    case HOME_ARTICLE_FRAGMENT:
                        if (articleFragment.isHaveData) {
                            articleFragment.getLoadData();
                        }
                        break;
                }
            }
        });
        StringUtil.getVpPosition(viewPager, new StringUtil.VpPageSelected() {
            @Override
            public void getVpPageSelected(int position) {
                switch (position) {
                    case HOME_REVIEW_FRAGMENT:
                        if (reviewFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                        break;
                    case HOME_DISCUSS_FRAGMENT:
                        if (discussFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                        break;
                    case HOME_ARTICLE_FRAGMENT:
                        if (articleFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                        break;
                }
            }
        });
    }
}
