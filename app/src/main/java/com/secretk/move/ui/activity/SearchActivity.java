package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.SearchActivityRecyclerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/17.
 */

public class SearchActivity extends BaseActivity implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private SearchActivityRecyclerAdapter adapter;


    @Override
    protected int setOnCreate() {
        return R.layout.activity_search;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this,  UiUtils.getColor(R.color.main_background), 0);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter=new SearchActivityRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);

        List<String> list=new ArrayList<>();
        list.add("小米");
        list.add("华为");
        adapter.setData(list);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
