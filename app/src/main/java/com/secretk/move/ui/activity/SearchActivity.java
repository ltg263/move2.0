package com.secretk.move.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.secretk.move.ui.adapter.SearchHistoryAdapter;
import com.secretk.move.ui.adapter.TopicFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
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
    private TopicFragmentRecyclerAdapter adapter;
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
        adapter = new TopicFragmentRecyclerAdapter(this);
        historyAdapter.setItemListener(this);
        adapter.setItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                boolean isLoginZt = SharedUtils.singleton().get(Constants.IS_LOGIN_KEY,false);
                if(isLoginZt){
                    int id = adapter.getData().get(postion).getProjectId();
                    IntentUtil.startProjectActivity(id);
                }else{
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }

            @Override
            public void onItemLongClick(View view, int postion) {

            }
        });

    }
    private void initRefresh() {
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.setLoadmoreFinished(false);

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
    public void loadSearchSuccess(List<SearchedBean.Projects> list) {
        recycler.setAdapter(adapter);
        adapter.setData(list, Constants.TOPIC_SORT_BY_NUM);
    }

    @Override
    public String getSearchTxt() {
        return ed_search.getText().toString().trim();
    }

}
