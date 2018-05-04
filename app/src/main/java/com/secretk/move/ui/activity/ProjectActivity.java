package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
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
import com.secretk.move.ui.fragment.ProjectArticleFragment;
import com.secretk.move.ui.fragment.ProjectDiscussFragment;
import com.secretk.move.ui.fragment.ProjectIntroFragment;
import com.secretk.move.ui.fragment.ProjectReviewFragment;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 13:41
 * 邮箱；ltg263@126.com
 * 描述：项目主页 加载四个Fragment：
 * ProjectIntroFragment        简介
 * ProjectReviewFragment()    测评
 * ProjectDiscussFragment(    讨论
 * ProjectArticleFragment(    文章
 */

public class ProjectActivity extends BaseActivity {

    @BindView(R.id.tv_user_name_y)
    TextView tvUserNameY;
    @BindView(R.id.tv_user_name_z)
    TextView tvUserNameZ;
    @BindView(R.id.tv_issue_time)
    TextView tvIssueTime;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.tv_praise)
    TextView tvPraise;
    @BindView(R.id.tv_fans)
    TextView tvFans;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_individual_resume)
    TextView tvIndividualResume;
    @BindView(R.id.btn_review)
    Button btnReview;
    @BindView(R.id.btn_attention)
    Button btnAttention;
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
    private ProjectIntroFragment introFragment;
    private ProjectReviewFragment reviewFragment;
    private ProjectDiscussFragment discussFragment;
    private ProjectArticleFragment articleFragment;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_project;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("EOX");
        mHeadView.setTitleVice("/臭子");
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        GlideUtils.loadCircle(ivHead, R.mipmap.ic_launcher);
        introFragment = new ProjectIntroFragment();
        reviewFragment = new ProjectReviewFragment();
        discussFragment = new ProjectDiscussFragment();
        articleFragment = new ProjectArticleFragment();
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        adapter.addFragment(introFragment, getString(R.string.intro));
        adapter.addFragment(reviewFragment, getString(R.string.review));
        adapter.addFragment(discussFragment, getString(R.string.discuss));
        adapter.addFragment(articleFragment, getString(R.string.article));
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(4);
        tabs.post(new Runnable() {
            @Override
            public void run() {
                StatusBarUtil.setIndicator(tabs, 30, 30);
            }
        });
        initListener();
    }

    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", sharedUtils.get(Constants.TOKEN_KEY, ""));
            // node.put("userId", edPassword.getText().toString());
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
                GlideUtils.loadCircleUrl(ivHead, iconUrl);
                GlideUtils.loadCircleUrl(mHeadView.getImageView(), iconUrl);
                mHeadView.setTitle(userData.getHomePageTitle());
                tvUserNameY.setText(userData.getUserName());
                //"userType": 1,// 用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
                //tvEvaluatingSign.setText(userData.getUserType());
                //“showFollow”: 0 , //是否显示 关注按钮 0- 不显示；1-显示关注  2-显示取消关注
                btnAttention.setText(userData.getShowFollow());
                //tvAnswer.setText(userData.getTotalPostNum());
                tvPraise.setText(userData.getPraiseNum());
                tvFans.setText(userData.getFansNum());
            }
        });
    }

    private void initListener() {
        refreshLayout.setEnableRefresh(false);//禁止下拉刷新
        refreshLayout.setEnableLoadmore(false);
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
                    mHeadView.setTitle("项目首页");
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
                switch (viewPager.getCurrentItem()){
                    case 1:
                        reviewFragment.getLoadData(refreshlayout);
                        if(reviewFragment.isHaveData){
                            refreshlayout.setLoadmoreFinished(false);
                        }else{
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 2:
                        discussFragment.getLoadData(refreshlayout);
                        if(discussFragment.isHaveData){
                            refreshlayout.setLoadmoreFinished(false);
                        }else{
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 3:
                        articleFragment.getLoadData(refreshlayout);
                        if(articleFragment.isHaveData){
                            refreshlayout.setLoadmoreFinished(false);
                        }else{
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                }
            }
        });
        StringUtil.getVpPosition(viewPager, new StringUtil.VpPageSelected() {
            @Override
            public void getVpPageSelected(int position) {
                if(viewPager.getCurrentItem()==0){
                    refreshLayout.setEnableLoadmore(false);
                }else{
                    refreshLayout.setEnableLoadmore(true);
                }
                switch (viewPager.getCurrentItem()){
                    case 1:
                        reviewFragment.getLoadData(refreshLayout);
                        if(discussFragment.isHaveData){
                            refreshLayout.setLoadmoreFinished(false);
                        }else{
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 2:
                        discussFragment.getLoadData(refreshLayout);
                        if(articleFragment.isHaveData){
                            refreshLayout.setLoadmoreFinished(false);
                        }else{
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 3:
                        articleFragment.getLoadData(refreshLayout);
                        if(articleFragment.isHaveData){
                            refreshLayout.setLoadmoreFinished(false);
                        }else{
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                }

            }
        });
    }
}
