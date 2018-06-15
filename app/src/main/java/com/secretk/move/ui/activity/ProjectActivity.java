package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.ProjectHomeBean;
import com.secretk.move.ui.adapter.HomePageAdapter;
import com.secretk.move.ui.fragment.ProjectArticleFragment;
import com.secretk.move.ui.fragment.ProjectDiscussFragment;
import com.secretk.move.ui.fragment.ProjectIntroFragment;
import com.secretk.move.ui.fragment.ProjectReviewFragment;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_project_chinese_name)
    TextView tvProjectChineseName;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_total_score)
    TextView tvTotalScore;
    @BindView(R.id.rl_grade)
    RelativeLayout rlGrade;
    @BindView(R.id.tv_rater_num)
    TextView tvRaterNum;
    @BindView(R.id.tv_follower_num)
    TextView tvFollowerNum;
    @BindView(R.id.iv_project_icon)
    ImageView ivProjectIcon;
    @BindView(R.id.tv_project_signature)
    TextView tvProjectSignature;
    @BindView(R.id.tv_review)
    TextView tvReview;
    @BindView(R.id.tv_follow_status)
    TextView tvFollowStatus;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private ProjectIntroFragment introFragment;
    private ProjectReviewFragment reviewFragment;
    private ProjectDiscussFragment discussFragment;
    private ProjectArticleFragment articleFragment;
    private String projectId;
    private ProjectHomeBean.DataBean.ProjectBean projectInfo;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_project;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        tvReview.setSelected(true);
        projectId = getIntent().getStringExtra("projectId");
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
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", Integer.valueOf(projectId));//查看的项目ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_INDEX)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, ProjectHomeBean.class, new HttpCallBackImpl<ProjectHomeBean>() {
            @Override
            public void onCompleted(ProjectHomeBean projectHomeBean) {
                projectInfo = projectHomeBean.getData().getProject();
                reviewFragment.initUiDate(projectHomeBean);
                discussFragment.initUiData(projectHomeBean.getData().getHotDiscuss());
                reviewFragment.initUiData(projectHomeBean.getData().getHotEva());
                if (projectInfo != null) {
                    mHeadView.setTitle(projectInfo.getProjectCode());
                    mHeadView.setTitleVice("/" + projectInfo.getProjectChineseName());
                    introFragment.initUiData(projectInfo);
                    //  discussFragment.initUiData(30);
                    GlideUtils.loadCircleProjectUrl(ProjectActivity.this,ivProjectIcon, Constants.BASE_IMG_URL + projectInfo.getProjectIcon());
                    tvProjectCode.setText(projectInfo.getProjectCode());
                    tvProjectChineseName.setText("/" + projectInfo.getProjectChineseName());
                    tvCreateTime.setText("发布时间：" + StringUtil.getTimeToM(projectInfo.getCreateTime()));
                    tvFollowerNum.setText(String.valueOf(projectInfo.getFollowerNum()));
                    tvTotalScore.setText(String.valueOf(projectInfo.getTotalScore()));
                    tvRaterNum.setText(String.valueOf(projectInfo.getRaterNum()));
                    tvProjectSignature.setText(projectInfo.getProjectSignature());
                    // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                    if(projectInfo.getFollowStatus()==0){
                        tvFollowStatus.setSelected(false);
                        tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                    }else if(projectInfo.getFollowStatus() == 1){
                        tvFollowStatus.setSelected(true);
                        tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                    }else{
                        tvFollowStatus.setVisibility(View.GONE);
                    }

                }

            }

            @Override
            public void onFinish() {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }

    public String getProjectId() {
        return projectId;
    }

    public LoadingDialog getloadingDialog(){
        return loadingDialog;
    }


    private void initListener() {
        fab.setVisibility(View.GONE);
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
//                    mHeadView.getImageView().setVisibility(View.VISIBLE);
//                    mHeadView.getTextView().setVisibility(View.GONE);
                } else {  //展开
//                    mHeadView.getImageView().setVisibility(View.GONE);
//                    mHeadView.getTextView().setVisibility(View.VISIBLE);
//                    mHeadView.setTitle("项目首页");
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
                    case 1:
                        reviewFragment.getLoadData(refreshlayout);
                        if (reviewFragment.isHaveData) {
                            refreshlayout.setLoadmoreFinished(false);
                        } else {
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 2:
                        discussFragment.getLoadData(refreshlayout);
                        if (discussFragment.isHaveData) {
                            refreshlayout.setLoadmoreFinished(false);
                        } else {
                            refreshlayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 3:
                        articleFragment.getLoadData(refreshlayout, "");
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
                if (viewPager.getCurrentItem() == 0) {
                    fab.setVisibility(View.GONE);
                    refreshLayout.setEnableLoadmore(false);
                } else {
                    fab.setVisibility(View.VISIBLE);
                    refreshLayout.setEnableLoadmore(true);
                }
                switch (viewPager.getCurrentItem()) {
                    case 1:
                        if (discussFragment.isHaveData) {
                            refreshLayout.setLoadmoreFinished(false);
                        } else {
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 2:
                        if (articleFragment.isHaveData) {
                            refreshLayout.setLoadmoreFinished(false);
                        } else {
                            refreshLayout.setLoadmoreFinished(true);
                        }
                        break;
                    case 3:
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

    @OnClick({R.id.fab,R.id.rl_grade, R.id.tv_review, R.id.tv_follow_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startAcy();
                break;
            case R.id.rl_grade:
                String key[] = {"id","code","chineseName","icon"};
                String values[] = {projectId,projectInfo.getProjectCode(),projectInfo.getProjectChineseName(),projectInfo.getProjectIcon()};
                IntentUtil.startActivity(DetailsUserGradeActivity.class, key, values);
                break;
            case R.id.tv_review:
                IntentUtil.startProjectSimplenessActivity(projectInfo.getProjectId(),projectInfo.getProjectIcon(),
                        projectInfo.getProjectChineseName(),projectInfo.getProjectCode());
                break;
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.PROJECT,projectInfo.getProjectId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                               // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                                tvFollowStatus.setEnabled(true);
                                if(!str.equals(Constants.FOLLOW_ERROR)){
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
        }
    }

    private void startAcy() {
        Intent intent;
        switch (viewPager.getCurrentItem()){
            case 1:
                IntentUtil.startProjectSimplenessActivity(projectInfo.getProjectId(),projectInfo.getProjectIcon(),
                        projectInfo.getProjectChineseName(),projectInfo.getProjectCode());
                break;
            case 2:
                intent = new Intent(this, ReleaseDiscussActivity.class);
                intent.putExtra("projectId",projectInfo.getProjectId());
                intent.putExtra("projectPay",projectInfo.getProjectCode());
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, ReleaseArticleActivity.class);
                intent.putExtra("projectId",projectInfo.getProjectId());
                intent.putExtra("projectPay",projectInfo.getProjectCode());
                startActivity(intent);
                break;
        }
    }
}
