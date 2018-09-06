package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.widget.ImageView;
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
import com.secretk.move.bean.TopicDesIndexBean;
import com.secretk.move.ui.adapter.HomePageAdapter;
import com.secretk.move.ui.fragment.TopicArticleFragment;
import com.secretk.move.ui.fragment.TopicDiscussFragment;
import com.secretk.move.ui.fragment.TopicReviewFragment;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.MagicIndicatorUtils;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 13:41
 * 邮箱；ltg263@126.com
 * 描述：我的主页 加载三个Fragment：
 * TopicReviewFragment      测评
 * TopicDiscussFragment     讨论
 * TopicArticleFragment     文章
 */

public class TopicActivity extends BaseActivity {
    @BindView(R.id.iv_topic_icon)
    ImageView ivTopicIcon;
    @BindView(R.id.tv_topic_name)
    TextView tvTopicName;
    @BindView(R.id.tv_topic_sin)
    TextView tvTopicSin;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.view_pager)
    ViewPagerFixed viewPager;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    public static final int HOME_REVIEW_FRAGMENT = 0;
    public static final int HOME_DISCUSS_FRAGMENT = 1;
    public static final int HOME_ARTICLE_FRAGMENT = 2;
    int tagId;
    private String currentType;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_topic;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        isLoginUi=true;
        mHeadView.setTitleColor(R.color.white);
        mHeadView.setTitle("话题");
        return mHeadView;
    }

    TopicReviewFragment reviewFragment;
    TopicDiscussFragment discussFragment;
    TopicArticleFragment articleFragment;

    @Override
    protected void initUI(Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.app_background), 0);
        tagId = getIntent().getIntExtra("tagId",0);
        currentType = getIntent().getStringExtra("currentType");
        refreshLayout.setEnableRefresh(false);//禁止下拉刷新
        reviewFragment = new TopicReviewFragment();
        discussFragment = new TopicDiscussFragment();
        articleFragment = new TopicArticleFragment();
        reviewFragment.setSmartRefreshLayout(refreshLayout);
        discussFragment.setSmartRefreshLayout(refreshLayout);
        articleFragment.setSmartRefreshLayout(refreshLayout);
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        adapter.addFragment(reviewFragment, getString(R.string.review));
        adapter.addFragment(discussFragment, getString(R.string.discuss));
        adapter.addFragment(articleFragment, getString(R.string.article));
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        MagicIndicatorUtils.initMagicIndicatorTitle(this, adapter.getmFragmentTitles(), viewPager, magicIndicatorTitle);
        if (StringUtil.isNotBlank(currentType)) {
            viewPager.setCurrentItem(Integer.valueOf(currentType));
        } else {
            viewPager.setCurrentItem(0);
        }
        initListener();
    }

    public int getTagId() {
        return tagId;
    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("id", tagId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_DTAG_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, TopicDesIndexBean.class, new HttpCallBackImpl<TopicDesIndexBean>() {
            @Override
            public void onCompleted(TopicDesIndexBean userInfo) {
                TopicDesIndexBean.DataBean userData = userInfo.getData();
                String iconUrl = Constants.BASE_IMG_URL + userData.getImgPath();
                GlideUtils.loadSideMinImage_76(TopicActivity.this, ivTopicIcon, iconUrl);
                tvTopicName.setText(userData.getTagName());
                tvTopicSin.setText(userData.getMemo());
                tagId = userData.getTagId();
            }
        });
    }

    public LoadingDialog getLoadingDialog() {
        return loadingDialog;
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
//                    mHeadView.getImageView().setVisibility(View.VISIBLE);
//                    mHeadView.getTextView().setVisibility(View.GONE);
                } else {  //展开
//                    mHeadView.getImageView().setVisibility(View.GONE);
//                    mHeadView.getTextView().setVisibility(View.VISIBLE);
                }
            }
        });
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        reviewFragment.onRefreshLayout();
                        break;
                    case 1:
                        discussFragment.onRefreshLayout();
                        break;
                    case 2:
                        articleFragment.onRefreshLayout();
                        break;
                }
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
