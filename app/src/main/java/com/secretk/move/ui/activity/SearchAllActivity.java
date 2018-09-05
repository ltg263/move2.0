package com.secretk.move.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.secretk.move.R;
import com.secretk.move.base.MvpBaseActivity;
import com.secretk.move.bean.SearchBean;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.contract.ActivitySearchContract;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.ActivitySearchPresenterImpl;
import com.secretk.move.ui.adapter.SearchHistoryAdapter;
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

public class SearchAllActivity extends MvpBaseActivity<ActivitySearchPresenterImpl> implements ItemClickListener, ActivitySearchContract.View {
    @BindView(R.id.recycler)
    RecyclerView recycler;
   LoadingDialog loadingDialog;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private LinearLayoutManager layoutManager;
    private SearchHistoryAdapter historyAdapter;
    private int searchType;
    public static String searchTxt = null;

    @Override
    protected int setLayout() {
        return R.layout.activity_search_all;
    }


    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
        initRefresh();
        searchType = getIntent().getIntExtra("SearchType",0);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        loadingDialog = new LoadingDialog(this);
        historyAdapter = new SearchHistoryAdapter();
        historyAdapter.setItemListener(this);
    }
    private void initRefresh() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
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
            return;
        }
        presenter.searchSuccess(null,ed_search.getText().toString().trim(),1);
         if(searchType==-1){
             searchTxt=ed_search.getText().toString().trim();
             finish();
             return;
         }
         Intent intent = new Intent(SearchAllActivity.this,SearchAllContentActivity.class);
         intent.putExtra("searchTxt",ed_search.getText().toString().trim());
         startActivity(intent);
     }

    @Override
    public void onItemClick(View view, int postion) {
//        SearchBean bean=historyAdapter.getDataInPosition(postion);
//        presenter.SearchBean(bean);

        SearchBean bean=historyAdapter.getDataInPosition(postion);
        ed_search.setText(bean.getSearchTxt());
        searchTxt=ed_search.getText().toString().trim();
        if(searchType==-1){
            finish();
            return;
        }
        Intent intent = new Intent(SearchAllActivity.this,SearchAllContentActivity.class);
        intent.putExtra("searchTxt",bean.getSearchTxt());
        startActivity(intent);

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

    }

    @Override
    public String getSearchTxt() {
        return ed_search.getText().toString().trim();
    }

}
