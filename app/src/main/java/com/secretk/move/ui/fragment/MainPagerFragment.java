package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.adapter.MainFragmentPagerAdapter;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/5.
 *
 * 主页fragment 下面还有3个子fragment
 * 分别是
 * MainRfFragment
 * MainFollowFragment
 * MainBlueSkyFragment
 */

public class MainPagerFragment extends LazyFragment implements Toolbar.OnMenuItemClickListener{
    @BindView(R.id.vp_main_children)
    ViewPager vp_main_children;
    @BindView(R.id.tool_bar)
    Toolbar tool_bar;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    MainFragmentPagerAdapter adapter;
    public String[] head_list;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_main;
    }

    @Override
    public void initViews() {
        tool_bar.inflateMenu(R.menu.activity_main_menu);
        tool_bar.setNavigationIcon(R.drawable.main_search);
        adapter=new MainFragmentPagerAdapter(getChildFragmentManager());
        vp_main_children.setAdapter(adapter);
        tab_layout.setupWithViewPager(vp_main_children);
        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tool_bar.setOnMenuItemClickListener(this);
        tool_bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        head_list= UiUtils.getStringArray(R.array.fragment_main_titles);
        for (String name : head_list) {
            tab_layout.addTab(tab_layout.newTab().setText(name));
        }
        vp_main_children.setOffscreenPageLimit(head_list.length);
        adapter.setData(head_list);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ToastUtils.getInstance().show("发表");
        return false;
    }
}
