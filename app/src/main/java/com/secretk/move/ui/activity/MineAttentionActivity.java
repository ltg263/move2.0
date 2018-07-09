package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MineAttentionPagerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ViewPagerFixed;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/28 14:50
 * 邮箱；ltg263@126.com
 * 描述：我的关注
 */
public class MineAttentionActivity extends BaseActivity{
    @BindView(R.id.vp_main_children)
    ViewPagerFixed vp_main_children;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    MineAttentionPagerAdapter adapter;
    public String[] head_list;
    Intent intent;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }
    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_attention;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.app_bar_background), 0);
        tool_bar.inflateMenu(R.menu.activity_main_menu1);
        tool_bar.setNavigationIcon(R.drawable.toobar_back_blue);
        adapter = new MineAttentionPagerAdapter(getSupportFragmentManager());
        vp_main_children.setAdapter(adapter);
        vp_main_children.setOffscreenPageLimit(2);
        tab_layout.setupWithViewPager(vp_main_children);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);

        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineAttentionActivity.this.finish();
            }
        });
        TabLayoutLenth();
        head_list = UiUtils.getStringArray(R.array.mine_attention_titles);
        for (String name : head_list) {
            tab_layout.addTab(tab_layout.newTab().setText(name));
        }
        vp_main_children.setOffscreenPageLimit(head_list.length);
        adapter.setData(head_list);
    }

    @Override
    protected void initData() {

    }



    public void TabLayoutLenth(){
        tab_layout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tab_layout.getChildAt(0);

                    int dp10 = UiUtils.dip2px( 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
