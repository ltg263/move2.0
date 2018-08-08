package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.ProjectHomeBean;
import com.secretk.move.ui.adapter.ProjectPageAdapter;
import com.secretk.move.ui.fragment.ProjectArticleFragment;
import com.secretk.move.ui.fragment.ProjectDiscussFragment;
import com.secretk.move.ui.fragment.ProjectIntroFragment;
import com.secretk.move.ui.fragment.ProjectMarketFragment;
import com.secretk.move.ui.fragment.ProjectNewsFragment;
import com.secretk.move.ui.fragment.ProjectReviewFragment;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.MagicIndicatorUtils;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
    @BindView(R.id.view_pager)
    ViewPagerFixed viewPager;
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
    @BindView(R.id.tv_bu_zu)
    TextView tvBuZu;
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
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_percent_change_24h)
    TextView tvPercentChange24h;
    @BindView(R.id.iv_market_cap)
    ImageView ivMarketCap;
    @BindView(R.id.tv_market_cap)
    TextView tvMarketCap;
    @BindView(R.id.tv_volume_24h)
    TextView tvVolume24h;
    private ProjectIntroFragment introFragment;
    private ProjectReviewFragment reviewFragment;
    private ProjectDiscussFragment discussFragment;
    private ProjectArticleFragment articleFragment;
    private ProjectMarketFragment marketFragment;
    private ProjectNewsFragment newsFragment;
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
//        tvReview.setSelected(true);
        projectId = getIntent().getStringExtra("projectId");
        List<String> list = new ArrayList<>();
        list.add(getString(R.string.review));
        list.add(getString(R.string.discuss));
        list.add(getString(R.string.article));
        list.add(getString(R.string.market));
        list.add(getString(R.string.news));
        list.add(getString(R.string.intro));
        MagicIndicatorUtils.initMagicIndicatorTitle(this, list, viewPager, magicIndicatorTitle);
        introFragment = new ProjectIntroFragment();
        reviewFragment = new ProjectReviewFragment();
        discussFragment = new ProjectDiscussFragment();
        articleFragment = new ProjectArticleFragment();
        marketFragment = new ProjectMarketFragment();
        newsFragment = new ProjectNewsFragment();
        ProjectPageAdapter adapter = new ProjectPageAdapter(getSupportFragmentManager());
        adapter.addFragment(reviewFragment);
        adapter.addFragment(discussFragment);
        adapter.addFragment(articleFragment);
        adapter.addFragment(marketFragment);
        adapter.addFragment(newsFragment);
        adapter.addFragment(introFragment);
        reviewFragment.setSmartRefreshLayout(refreshLayout);
        discussFragment.setSmartRefreshLayout(refreshLayout);
        articleFragment.setSmartRefreshLayout(refreshLayout);
        marketFragment.setSmartRefreshLayout(refreshLayout);
        newsFragment.setSmartRefreshLayout(refreshLayout);
        viewPager.setAdapter(adapter);
        adapter.setData(list);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(6);
        initListener();
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
                    setProjectCode(projectInfo.getProjectCode());
                    mHeadView.setTitleVice("/" + projectInfo.getProjectChineseName());
                    introFragment.initUiData(projectInfo);
                    //  discussFragment.initUiData(30);
                    GlideUtils.loadCircleProjectUrl(ProjectActivity.this, ivProjectIcon, Constants.BASE_IMG_URL + projectInfo.getProjectIcon());
                    tvProjectCode.setText(projectInfo.getProjectCode());
                    tvProjectChineseName.setText("/" + projectInfo.getProjectChineseName());
                    tvCreateTime.setText("发布时间：" + StringUtil.getTimeToM(projectInfo.getCreateTime()));
                    tvFollowerNum.setText(String.valueOf(projectInfo.getFollowerNum()));
                    if (projectInfo.getTotalScore() != 0) {
                        tvBuZu.setVisibility(View.GONE);
                        tvTotalScore.setVisibility(View.VISIBLE);
                        tvTotalScore.setText(String.valueOf(projectInfo.getTotalScore()));
                    }
                    tvRaterNum.setText(String.valueOf(projectInfo.getRaterNum()));
                    tvProjectSignature.setText(projectInfo.getProjectSignature());
                    // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                    if (projectInfo.getFollowStatus() == 0) {
                        tvFollowStatus.setSelected(false);
                        tvFollowStatus.setText(getResources().getString(R.string.follow_status_0));
                    } else if (projectInfo.getFollowStatus() == 1) {
                        tvFollowStatus.setSelected(true);
                        tvFollowStatus.setText(getResources().getString(R.string.follow_status_1));
                    } else {
                        tvFollowStatus.setVisibility(View.GONE);
                    }

                }

                double price = projectInfo.getPrice();
                double percent_change_24h = projectInfo.getPercentChange24h();
                double volume_24h = projectInfo.getVolume24h();
                double market_cap = projectInfo.getMarketCap();
                if(price!=0 || percent_change_24h!=0
                        || volume_24h!=0 || market_cap!=0){
                    tvPrice.setText("￥" + StringUtil.getYxNum(price));
                    ivMarketCap.setVisibility(View.VISIBLE);
                    if (percent_change_24h < 0) {
                        tvPercentChange24h.setBackgroundColor(getResources().getColor(R.color.theme_title_red));
                        tvPrice.setTextColor(getResources().getColor(R.color.theme_title_red));
                        tvPercentChange24h.setText(String.format("%.2f", percent_change_24h) + "%");
                        ivMarketCap.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_fall));
                    } else {
                        tvPercentChange24h.setBackgroundColor(getResources().getColor(R.color.theme_title_lv));
                        tvPrice.setTextColor(getResources().getColor(R.color.theme_title_lv));
                        tvPercentChange24h.setText("+" + String.format("%.2f", percent_change_24h) + "%");
                        ivMarketCap.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_rise));
                    }
                    if(market_cap!=0){
                        String market_capStr = "";
                        if (market_cap < 10000) {
                            market_capStr = String.valueOf(Math.round(market_cap));
                        } else if (market_cap < 100000000) {
                            market_capStr = String.format("%.2f", market_cap / 10000) + "万";
                        } else {
                            market_capStr = String.format("%.2f", market_cap / 100000000) + "亿";
                        }
                        tvMarketCap.setText(market_capStr);

                    }
                    if(volume_24h!=0){
                        String volume_24hStr = "";
                        if (volume_24h < 10000) {
                            volume_24hStr = String.valueOf(Math.round(volume_24h));
                        } else if (market_cap < 100000000) {
                            volume_24hStr = String.format("%.2f", volume_24h / 10000) + "万";
                        } else {
                            volume_24hStr = String.format("%.2f", volume_24h / 100000000) + "亿";
                        }
                        tvVolume24h.setText(volume_24hStr);
                    }

                }
//                getLoadData(projectInfo.getCmcId(), "CNY");

            }

            @Override
            public void onError(String message) {
                DialogUtils.showDialogHint(ProjectActivity.this, message, true, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        finish();
                    }
                });
            }

            @Override
            public void onFinish() {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectCode(String projectCode) {
        newsFragment.setProjectCode(projectCode);
    }

    public LoadingDialog getloadingDialog() {
        return loadingDialog;
    }


    private void initListener() {
//        fab.setVisibility(View.GONE);
        refreshLayout.setEnableRefresh(false);//禁止下拉刷新
//        refreshLayout.setEnableLoadmore(false);
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
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        if (reviewFragment.isHaveData) {
                            reviewFragment.getLoadData("");
                        }
                        break;
                    case 1:
                        if (discussFragment.isHaveData) {
                            discussFragment.getLoadData("");
                        }
                        break;
                    case 2:
                        if (articleFragment.isHaveData) {
                            articleFragment.getLoadData("");
                        }
                    case 3:
                        if (marketFragment.isHaveData) {
                            marketFragment.getLoadData("");
                        }
                    case 4:
                        if (newsFragment.isHaveData) {
                            newsFragment.getLoadData("");
                        }
                        break;
                }
            }
        });
        StringUtil.getVpPosition(viewPager, new StringUtil.VpPageSelected() {
            @Override
            public void getVpPageSelected(int position) {
                if (viewPager.getCurrentItem() == 5 || viewPager.getCurrentItem()==4 || viewPager.getCurrentItem()==3) {
                    fab.setVisibility(View.GONE);
                    refreshLayout.setEnableLoadMore(false);
                } else {
                    fab.setVisibility(View.VISIBLE);
                    refreshLayout.setEnableLoadMore(true);
                }
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        if (reviewFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                        break;
                    case 1:
                        if (discussFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                        break;
                    case 2:
                        if (articleFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                    case 3:
                        if (marketFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                    case 4:
                        if (newsFragment.isHaveData) {
                            refreshLayout.setNoMoreData(false);
                        } else {
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                        break;
                }

            }
        });
    }

    @OnClick({R.id.fab, R.id.rl_grade, R.id.tv_review, R.id.tv_follow_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab:
                startAcy();
                break;
            case R.id.rl_grade:
                String key[] = {"id", "code", "chineseName", "icon"};
                String values[] = {projectId, projectInfo.getProjectCode(), projectInfo.getProjectChineseName(), projectInfo.getProjectIcon()};
                IntentUtil.startActivity(DetailsUserGradeActivity.class, key, values);
                break;
            case R.id.tv_review:
                IntentUtil.startProjectSimplenessActivity(projectInfo.getProjectId(), projectInfo.getProjectIcon(),
                        projectInfo.getProjectChineseName(), projectInfo.getProjectCode());
                break;
            case R.id.tv_follow_status:
                tvFollowStatus.setEnabled(false);
                NetUtil.addSaveFollow(tvFollowStatus,
                        Constants.SaveFollow.PROJECT, projectInfo.getProjectId(), new NetUtil.SaveFollowImp() {
                            @Override
                            public void finishFollow(String str) {
                                // 0 显示 关注按钮； 1--显示取消关注 按钮 ；2 不显示按钮
                                tvFollowStatus.setEnabled(true);
                                if (!str.equals(Constants.FOLLOW_ERROR)) {
                                    tvFollowStatus.setText(str);
                                }
                            }
                        });
                break;
        }
    }

    private void startAcy() {
        Intent intent;
        switch (viewPager.getCurrentItem()) {
            case 0:
                IntentUtil.startProjectSimplenessActivity(projectInfo.getProjectId(), projectInfo.getProjectIcon(),
                        projectInfo.getProjectChineseName(), projectInfo.getProjectCode());
                break;
            case 1:
                intent = new Intent(this, ReleaseDiscussActivity.class);
                intent.putExtra("projectId", projectInfo.getProjectId());
                intent.putExtra("projectPay", projectInfo.getProjectCode());
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ReleaseArticleActivity.class);
                intent.putExtra("projectId", projectInfo.getProjectId());
                intent.putExtra("projectPay", projectInfo.getProjectCode());
                startActivity(intent);
                break;
        }
    }

    /**
     * 获1取CNY
     */
    public void getLoadData(int id, String name) {
        RxHttpParams params = new RxHttpParams.Build()
//                .url(Constants.SEND_TOKEN)
                .url("https://api.coinmarketcap.com/v2/ticker/" + id + "/?convert=" + name + "")
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if (obj.getJSONObject("data") != null) {
                        JSONObject cny = obj.getJSONObject("data").getJSONObject("quotes").getJSONObject("CNY");
                        if (StringUtil.isEmptyObject(cny.get("price"))) {
                            return;
                        }
                        double price = cny.getDouble("price");
                        double percent_change_24h = cny.getDouble("percent_change_24h");
                        tvPrice.setText("￥" + StringUtil.getYxNum(price));

                        ivMarketCap.setVisibility(View.VISIBLE);
                        if (percent_change_24h < 0) {
                            tvPercentChange24h.setBackgroundColor(getResources().getColor(R.color.theme_title_red));
                            tvPrice.setTextColor(getResources().getColor(R.color.theme_title_red));
                            tvPercentChange24h.setText(String.format("%.2f", percent_change_24h) + "%");
                            ivMarketCap.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_fall));
                        } else {
                            tvPercentChange24h.setBackgroundColor(getResources().getColor(R.color.theme_title_lv));
                            tvPrice.setTextColor(getResources().getColor(R.color.theme_title_lv));
                            tvPercentChange24h.setText("+" + String.format("%.2f", percent_change_24h) + "%");
                            ivMarketCap.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_rise));
                        }

                        double market_cap = cny.getDouble("market_cap");
                        String market_capStr = "";
                        if (market_cap < 10000) {
                            market_capStr = String.valueOf(Math.round(market_cap));
                        } else if (market_cap < 100000000) {
                            market_capStr = String.format("%.2f", market_cap / 10000) + "万";
                        } else {
                            market_capStr = String.format("%.2f", market_cap / 100000000) + "亿";
                        }
                        tvMarketCap.setText(market_capStr);

                        double volume_24h = cny.getDouble("volume_24h");
                        String volume_24hStr = "";
                        if (volume_24h < 10000) {
                            volume_24hStr = String.valueOf(Math.round(volume_24h));
                        } else if (market_cap < 100000000) {
                            volume_24hStr = String.format("%.2f", volume_24h / 10000) + "万";
                        } else {
                            volume_24hStr = String.format("%.2f", volume_24h / 100000000) + "亿";
                        }

                        tvVolume24h.setText(volume_24hStr);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
