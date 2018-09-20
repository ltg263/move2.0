package com.secretk.move.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.activity.RewardSquareActivity;
import com.secretk.move.ui.activity.SearchAllContentActivity;
import com.secretk.move.ui.activity.TopicActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/7/28 10:02
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class MagicIndicatorUtils {

    public static void initMagicIndicatorTitle(final Context mContext, final List<String> tabs,
                                                final ViewPagerFixed vp_main_children,MagicIndicator magicIndicatorTitle) {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setScrollPivotX(0.25f);
        commonNavigator.setEnablePivotScroll(true);
//        commonNavigator.setScrollPivotX(0.65f);
        if(mContext instanceof ProjectActivity
                || mContext instanceof RewardSquareActivity
                || mContext instanceof SearchAllContentActivity
                || mContext instanceof HomeActivity
                || mContext instanceof TopicActivity){
             commonNavigator.setAdjustMode(true);//ture 即标题平分屏幕宽度的模式
        }
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabs == null ? 0 : tabs.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(tabs.get(index));
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.app_toolbar));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.app_background));
                simplePagerTitleView.setTextSize(16);
                if(!(mContext instanceof ProjectActivity)){
                    simplePagerTitleView.setPadding(45,0,45,0);
                }
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
                indicator.setColors(ContextCompat.getColor(mContext, R.color.app_background));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, vp_main_children);

    }
    public static void initMagicIndicator(final Context mContext, final List<String> mDataList,
                                                final ViewPagerFixed vp_main_children,MagicIndicator magicIndicator) {
        CommonNavigator commonNavigator = new CommonNavigator(mContext);
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdjustMode(true);//ture 即标题平分屏幕宽度的模式
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setPadding(0,0,0,0);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(mContext, R.color.title_gray));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(mContext, R.color.app_background));

                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp_main_children.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 20));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
//                indicator.setStartInterpolator(new AccelerateInterpolator());
//                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(ContextCompat.getColor(mContext, R.color.app_background));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                return 0.5f;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, vp_main_children);
    }

}
