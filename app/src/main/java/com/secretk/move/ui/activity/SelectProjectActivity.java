package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.SelectProjectAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/6/5 14:48
 * 邮箱；ltg263@126.com
 * 描述：发表 种类 选择项目
 * publication_type 发表的类型
 * 1 评测
 * 2 文章
 * 3 讨论
 */
public class SelectProjectActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.ed_search)
    EditText edSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private SelectProjectAdapter adapter;

    private List<SearchedBean.Projects> list;
    private int publicationType;
    private int projectId;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_select_project;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.background_gray), 0);
        initRefresh();
        publicationType = getIntent().getIntExtra("publication_type", 0);
        projectId = getIntent().getIntExtra("projectId", -1);
        setVerticalManager(recycler);
        adapter = new SelectProjectAdapter(this);
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        loadingDialog.show();
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
                initData();
            }
        });
    }
    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectCode", "");
            node.put("sortType", 2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SEARCH_PROJECTS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchedBean.class, new HttpCallBackImpl<SearchedBean>() {
            @Override
            public void onCompleted(SearchedBean bean) {
                list = bean.getData().getProjects();
                tvCount.setText("共" + list.size() + "个币种");
                adapter.setData(list,projectId);
            }


            @Override
            public void onFinish() {
                super.onFinish();
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                loadingDialog.dismiss();
            }
        });
    }

    /**
     * 1 评测
     * 2 文章
     * 3 讨论
     */
    @Override
    public void onItemClick(View view, int postion) {
        if(!isLoginZt){
            IntentUtil.startActivity(LoginHomeActivity.class);
            return;
        }
        SearchedBean.Projects bean = list.get(postion);
        if (publicationType == 1) {
            IntentUtil.startProjectSimplenessActivity(bean.getProjectId(), bean.getProjectIcon(),
                    bean.getProjectChineseName(), bean.getProjectCode());
        } else if (publicationType == 2) {
            Intent intent = new Intent(this, ReleaseArticleActivity.class);
            intent.putExtra("projectId", list.get(postion).getProjectId());
            intent.putExtra("projectPay", bean.getProjectCode());
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, ReleaseDiscussActivity.class);
            intent.putExtra("projectId", list.get(postion).getProjectId());
            intent.putExtra("projectPay", bean.getProjectCode());
            startActivity(intent);
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @OnClick({R.id.img_return, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.tv_search:
                searchProject();
                break;
        }
    }

    private void searchProject() {
        String searchContent = edSearch.getText().toString().trim();
        if(StringUtil.isBlank(searchContent)){
            ToastUtils.getInstance().show("搜索币种不能为空");
//            adapter.setData(list);
            return;
        }
        if(list==null){
            return;
        }
        List<SearchedBean.Projects> listSearch = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            if(list.get(i).getProjectCode().contains(searchContent)
                    || list.get(i).getProjectCode().contains(searchContent.toUpperCase())){
                listSearch.add(list.get(i));
            }
        }
        if(listSearch.size()==0){
            ToastUtils.getInstance().show("没有相关的币种");
            return;
        }
        tvCount.setText("共搜索到" + listSearch.size() + "个币种");
        adapter.setData(listSearch,projectId);
    }
}