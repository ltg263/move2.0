package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.base.MvpBaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.contract.ActivitySearchContract;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.ActivitySearchPresenterImpl;
import com.secretk.move.ui.adapter.FindFragmentRecyclerAdapter;
import com.secretk.move.ui.adapter.SearchHistoryAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.LoadingDialog;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/17.
 */

public class SearchActivity extends MvpBaseActivity<ActivitySearchPresenterImpl> implements ItemClickListener, ActivitySearchContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
   LoadingDialog loadingDialog;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private LinearLayoutManager layoutManager;
    private SearchHistoryAdapter historyAdapter;
    private FindFragmentRecyclerAdapter adapter;
    private int publicationType;
    private Bundle reward;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }


    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        initRefresh();
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        loadingDialog=new LoadingDialog(this);
        historyAdapter = new SearchHistoryAdapter();
        adapter = new FindFragmentRecyclerAdapter(this);
        historyAdapter.setItemListener(this);
        final int searchType = getIntent().getIntExtra("search_type", 0);
        if(searchType==-1){
            publicationType = getIntent().getIntExtra("publication_type", 0);
            if(publicationType == 4){
                reward = getIntent().getBundleExtra("reward");
            }

        }
        adapter.setItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if(searchType==-1){
                    SearchedBean.DataBean.ProjectsBean.RowsBean bean = adapter.getData().get(postion);
                    if (publicationType == 1) {
                        IntentUtil.startProjectSimplenessActivity(bean.getProjectId(), bean.getProjectIcon(),
                                bean.getProjectChineseName(), bean.getProjectCode());
                    } else if (publicationType == 2) {
                        Intent intent = new Intent(SearchActivity.this, ReleaseArticleActivity.class);
                        intent.putExtra("projectId", bean.getProjectId());
                        intent.putExtra("projectPay", bean.getProjectCode());
                        startActivity(intent);
                    }else if(publicationType == 4){
                        Intent intent = new Intent(SearchActivity.this, ReleaseRewardOkActivity.class);
                        intent.putExtra("projectId", bean.getProjectId());
                        intent.putExtra("projectPay", bean.getProjectCode());
                        intent.putExtra("reward", reward);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(SearchActivity.this, ReleaseDiscussActivity.class);
                        intent.putExtra("projectId", bean.getProjectId());
                        intent.putExtra("projectPay", bean.getProjectCode());
                        startActivity(intent);
                    }
                    return;
                }


                boolean isLoginZt = SharedUtils.singleton().get(Constants.IS_LOGIN_KEY,false);
                if(isLoginZt){
                    int id = adapter.getData().get(postion).getProjectId();
                    IntentUtil.startProjectActivity(id);
                }else{
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
                if(!isLoginZt){
                    IntentUtil.startActivity(LoginHomeActivity.class);
                    return;
                }

            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });
        ed_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == 66 && keyEvent.getAction()==KeyEvent.ACTION_UP){
                    if(StringUtil.isBlank(ed_search.getText().toString().trim())){
                        ToastUtils.getInstance().show("搜索内容不能为空");
                        return true;
                    }
                    presenter.search();
                }
                return false;
            }
        });

    }
    private void initRefresh() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
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
        if(StringUtil.isBlank(ed_search.getText().toString().trim())){
            ToastUtils.getInstance().show("搜索内容不能为空");
        }
         presenter.search();
     }

    @Override
    public void onItemClick(View view, int postion) {
        SearchBean bean=historyAdapter.getDataInPosition(postion);
        presenter.SearchBean(bean);
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
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
    public void loadSearchSuccess(List<SearchedBean.DataBean.ProjectsBean.RowsBean> list) {
        recycler.setAdapter(adapter);
        adapter.setData(list, Constants.TOPIC_SORT_BY_NUM);
    }

    @Override
    public String getSearchTxt() {
        return ed_search.getText().toString().trim();
    }

}
