package com.secretk.move.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
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
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 13:41
 * 邮箱；ltg263@126.com
 * 描述：我的主页 加载三个Fragment：
 *          HomeReviewFragment      测评
 *          HomeDiscussFragment     讨论
 *          HomeArticleFragment     文章
 */

public class HomeActivity extends BaseActivity {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_evaluating_sign)
    TextView tvEvaluatingSign;
    @BindView(R.id.tv_attention)
    TextView tvAttention;
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
    private int mOffset = 0;
    //token：MzoxNTI0NzQ1MzA1NDQ1OmMyM2EwODU0NjE0YTNkYWRhYjg3MDg2OGY2MmRjZGFh
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
    @Override
    protected void initUI(Bundle savedInstanceState) {
        setSmartRefreshStyle();
        GlideUtils.loadCircle(ivHead, R.mipmap.ic_launcher);
        HomePageAdapter adapter = new HomePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeReviewFragment(), getString(R.string.review));
        adapter.addFragment(new HomeDiscussFragment(), getString(R.string.discuss));
        adapter.addFragment(new HomeArticleFragment(), getString(R.string.article));
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

    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", sharedUtils.get(Constants.TOKEN_KEY,""));
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
                tvUserName.setText(userData.getUserName());
                tvIndividualResume.setText(userData.getUserSignature());
                //"userType": 1,// 用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
                tvEvaluatingSign.setText(userData.getUserType());
                //“showFollow”: 0 , //是否显示 关注按钮 0- 不显示；1-显示关注  2-显示取消关注
                tvAttention.setText(userData.getShowFollow());
                tvAnswer.setText(userData.getTotalPostNum());
                tvPraise.setText(userData.getPraiseNum());
                tvFans.setText(userData.getFansNum());
            }
        });
    }

    private void initListener() {
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
                //iv_parallax.setTranslationY(mOffset);
            }
        });

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
                    mHeadView.setTitle("我的首页");
                }
            }
        });
    }

    /**
     * 指定刷线的样式
     */
    private void setSmartRefreshStyle(){
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });

    }

}
