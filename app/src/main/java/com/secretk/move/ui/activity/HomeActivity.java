package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
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
import com.secretk.move.view.AppBarHeadView;

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
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    public static final int HOME_REVIEW_FRAGMENT = 0;
    public static final int HOME_DISCUSS_FRAGMENT = 1;
    public static final int HOME_ARTICLE_FRAGMENT = 2;
    String userId;
    private String homePageTitle;
    private Boolean isFollow=true;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_home;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("XXX的主页");
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    HomeReviewFragment reviewFragment;
    HomeDiscussFragment discussFragment;
    HomeArticleFragment articleFragment;

    @Override
    protected void initUI(Bundle savedInstanceState) {
        userId = getIntent().getStringExtra("userId");
        GlideUtils.loadCircle(ivHead, R.mipmap.ic_launcher);
        refreshLayout.setEnableRefresh(false);//禁止下拉刷新
        reviewFragment = new HomeReviewFragment();
        discussFragment = new HomeDiscussFragment();
        articleFragment = new HomeArticleFragment();
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        adapter.addFragment(reviewFragment, getString(R.string.review));
        adapter.addFragment(discussFragment, getString(R.string.discuss));
        adapter.addFragment(articleFragment, getString(R.string.article));
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
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
        JSONObject node = new JSONObject();
        try {
            node.put("token", sharedUtils.get(Constants.TOKEN_KEY, ""));
            //查看自己不用传userId只用token就可以，查看他人需要传入他人userID
            if (StringUtil.isNotBlank(userId)) {
               node.put("userId", userId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERHOME_INDEX)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, HomeUserIndexBean.class, new HttpCallBackImpl<HomeUserIndexBean>() {
            @Override
            public void onCompleted(HomeUserIndexBean userInfo) {
                HomeUserIndexBean.DataBean.UserBean userData = userInfo.getData().getUser();
                String iconUrl = userData.getIcon();
                iconUrl="https://t11.baidu.com/it/u=3204748471,2053904929&fm=173&app=25&f=JPEG?w=640&h=426&s=98C1A14C0C4B2B5F52CC969D03004085";
                GlideUtils.loadCircleUrl(ivHead, iconUrl);
                GlideUtils.loadCircleUrl(mHeadView.getImageView(), iconUrl);
                mHeadView.setTitle(userData.getHomePageTitle());
                tvUserName.setText(userData.getUserName());
                tvIndividualResume.setText(userData.getUserSignature());
                //"userType": 1,// 用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
                if(userData.getUserType()==2){
                    tvEvaluatingSign.setText("项目方");
                }else if(userData.getUserType()==3){
                    tvEvaluatingSign.setText("评测机构");
                }else if(userData.getUserType()==4){
                    tvEvaluatingSign.setText("机构用户");
                }else{
                    tvEvaluatingSign.setText("普通用户");
                    tvEvaluatingSign.setVisibility(View.GONE);
                }
                //“showFollow”: 0 , //是否显示 关注按钮 0- 不显示；1-显示关注  2-显示取消关注
                if(userData.getShowFollow()==0){
                    tvSaveFollow.setVisibility(View.GONE);
                }else if(userData.getShowFollow()==1){
                    isFollow=false;
                    tvSaveFollow.setText("+关注");
                }else{
                    isFollow=true;
                    tvSaveFollow.setText("已关注");
                }
                homePageTitle =  userData.getHomePageTitle();
                mHeadView.setTitle(homePageTitle);
                tvAnswer.setText(String.valueOf(userData.getTotalPostNum()));
                tvPraise.setText(String.valueOf(userData.getPraiseNum()));
                tvFans.setText(String.valueOf(userData.getFansNum()));
            }
        });
    }

    @OnClick(R.id.tv_save_follow)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_save_follow:
               // NetUtil.addSaveFollow(isFollow,token,Constants.SaveFollow.USER,userId);
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
                //设置可上啦
                refreshlayout.setLoadmoreFinished(false);
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                switch (viewPager.getCurrentItem()) {
                    case HOME_REVIEW_FRAGMENT:
                        reviewFragment.getLoadData(refreshlayout);
                        if (reviewFragment.isHaveData) {
                            refreshlayout.setLoadmoreFinished(false);
                        } else {
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                    case HOME_DISCUSS_FRAGMENT:
                        discussFragment.getLoadData(refreshlayout);
                        if (discussFragment.isHaveData) {
                            refreshlayout.setLoadmoreFinished(false);
                        } else {
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                    case HOME_ARTICLE_FRAGMENT:
                        articleFragment.getLoadData(refreshlayout);
                        if (articleFragment.isHaveData) {
                            refreshlayout.setLoadmoreFinished(false);
                        } else {
                            refreshlayout.setLoadmoreFinished(true);
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
                            refreshLayout.setLoadmoreFinished(false);
                        } else {
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                    case HOME_DISCUSS_FRAGMENT:
                        if (discussFragment.isHaveData) {
                            refreshLayout.setLoadmoreFinished(false);
                        } else {
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                    case HOME_ARTICLE_FRAGMENT:
                        if (articleFragment.isHaveData) {
                            refreshLayout.setLoadmoreFinished(false);
                        } else {
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                }
            }
        });
    }
}
