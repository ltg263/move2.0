package com.secretk.move.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.ProjectTabBean;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.adapter.MainProjectFragmentPagerAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.ColorFlipPagerTitleView;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/7 16:03
 * 邮箱；ltg263@126.com
 * 描述：主页项目
 */
public class ProjectFragment extends LazyFragment {

    @BindView(R.id.vp_main_children)
    ViewPagerFixed vp_main_children;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    MainProjectFragmentPagerAdapter adapter;

    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    private List<ProjectTabBean.DataBean.TabsBean> tabs;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project;
    }

    @Override
    public void initViews() {
        tool_bar.setNavigationIcon(R.drawable.main_search);
        adapter = new MainProjectFragmentPagerAdapter(getChildFragmentManager());
        vp_main_children.setAdapter(adapter);

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setEnablePivotScroll(true);
//        commonNavigator.setScrollPivotX(0.65f);
//        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs == null ? 0 : tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(tabs.get(index).getTabTitle());
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(getContext(), R.color.app_toolbar));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(getContext(), R.color.app_background));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp_main_children.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
//                indicator.setStartInterpolator(new AccelerateInterpolator());
//                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(getActivity(), R.color.app_background));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, vp_main_children);

    }

    @Override
    public void onFirstUserVisible() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_PROJECT_TAB)
//                .url(Constants.TOKEN_POP)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, ProjectTabBean.class, new HttpCallBackImpl<ProjectTabBean>() {
            @Override
            public void onCompleted(ProjectTabBean bean) {
                tabs = bean.getData().getTabs();
                vp_main_children.setOffscreenPageLimit(tabs.size());
                adapter.setData(tabs);
                initMagicIndicatorTitle();
            }
        });
    }
}
