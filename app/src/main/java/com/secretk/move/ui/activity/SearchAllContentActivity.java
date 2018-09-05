package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.ProjectPageAdapter;
import com.secretk.move.ui.fragment.SearchContentFragment;
import com.secretk.move.ui.fragment.SearchProjectFragment;
import com.secretk.move.ui.fragment.SearchTopicFragment;
import com.secretk.move.ui.fragment.SearchUserFragment;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.MagicIndicatorUtils;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/9/4 11:47
 * 邮箱；ltg263@126.com
 * 描述：搜索帖子币种 话题等 全部
 */
public class SearchAllContentActivity extends BaseActivity {

    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.vp_main_children)
    ViewPagerFixed vpMainChildren;
    private ProjectPageAdapter adapter;
    @BindView(R.id.tv_search_1)
    TextView tvSearch1;
    private String searchTxt;
    private SearchProjectFragment projectFragment;
    private SearchContentFragment contentFragment;
    private SearchUserFragment userFragment;
    private SearchTopicFragment topicFragment;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    @OnClick({R.id.img_return, R.id.tv_search_1,R.id.tv_search})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.tv_search_1:
                intent = new Intent(this, SearchAllActivity.class);
                intent.putExtra("SearchType",-1);
                startActivity(intent);
                break;
            case R.id.tv_search:
//                searchProject();
                intent = new Intent(this, SearchAllActivity.class);
                intent.putExtra("SearchType",-1);
                startActivity(intent);
                break;
        }
    }
    @Override
    protected int setOnCreate() {
        return R.layout.actvity_seward_all;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(StringUtil.isNotBlank(SearchAllActivity.searchTxt)
                && !SearchAllActivity.searchTxt.equals(searchTxt)){
            tvSearch1.setText(SearchAllActivity.searchTxt);
            searchTxt=SearchAllActivity.searchTxt;
            projectFragment.onLoadData(searchTxt);
            contentFragment.onLoadData(searchTxt);
            topicFragment.onLoadData(searchTxt);
            userFragment.onLoadData(searchTxt);
        }
        SearchAllActivity.searchTxt=null;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.white), 0);
        searchTxt = getIntent().getStringExtra("searchTxt");
        tvSearch1.setText(searchTxt);
        adapter = new ProjectPageAdapter(getSupportFragmentManager());
        vpMainChildren.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        list.add("项目");
        list.add("内容");
        list.add("用户");
        list.add("话题");
        vpMainChildren.setOffscreenPageLimit(list.size());
        MagicIndicatorUtils.initMagicIndicatorTitle(this,list,vpMainChildren,magicIndicatorTitle);
        projectFragment = new SearchProjectFragment();
        contentFragment = new SearchContentFragment();
        userFragment = new SearchUserFragment();
        topicFragment = new SearchTopicFragment();
        projectFragment.setSearchTxt(searchTxt);
        contentFragment.setSearchTxt(searchTxt);
        userFragment.setSearchTxt(searchTxt);
        topicFragment.setSearchTxt(searchTxt);
        adapter.addFragment(projectFragment);
        adapter.addFragment(contentFragment);
        adapter.addFragment(userFragment);
        adapter.addFragment(topicFragment);
        adapter.setData(list);
    }

    @Override
    protected void initData() {

    }
}
