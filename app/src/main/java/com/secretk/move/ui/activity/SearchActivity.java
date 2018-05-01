package com.secretk.move.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.secretk.move.R;
import com.secretk.move.base.MvpBaseActivity;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.contract.ActivitySearchContract;
import com.secretk.move.customview.ProgressWheel;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.ActivitySearchPresenterImpl;
import com.secretk.move.ui.adapter.SearchFromNetAdapter;
import com.secretk.move.ui.adapter.SearchHistoryAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/17.
 */

public class SearchActivity extends MvpBaseActivity<ActivitySearchPresenterImpl> implements ItemClickListener, ActivitySearchContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.progress_bar)
    ProgressWheel progress_bar;
    @BindView(R.id.ed_search)
    EditText ed_search;

    private LinearLayoutManager layoutManager;
    private SearchHistoryAdapter historyAdapter;
    private SearchFromNetAdapter searchFromNetAdapter;
    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }


    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        historyAdapter = new SearchHistoryAdapter();
        searchFromNetAdapter=new SearchFromNetAdapter();
        historyAdapter.setItemListener(this);
        searchFromNetAdapter.setItemListener(this);
    }

    @Override
    protected ActivitySearchPresenterImpl initPresenter() {
        return new ActivitySearchPresenterImpl(this);
    }

    @Override
    protected void initData() {
        presenter.initHistoryInfo();
    }
     public void search(View view){
         presenter.search();
     }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
    @Override
    public void showLoading() {
        progress_bar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progress_bar.setVisibility(android.view.View.INVISIBLE);
    }

    @Override
    public void showMsg(String msg) {
        ToastUtils.getInstance().show(msg);
    }

    @Override
    public void loadHistorySuccess(List<SearchBean> list) {
        recycler.setAdapter(historyAdapter);
        historyAdapter.setData(list);
    }

    @Override
    public void loadSearchSuccess(List<SearchBean> list) {
        recycler.setAdapter(searchFromNetAdapter);
        searchFromNetAdapter.setData(list);
    }

    @Override
    public String getSearchTxt() {
        return ed_search.getText().toString().trim();
    }

}
