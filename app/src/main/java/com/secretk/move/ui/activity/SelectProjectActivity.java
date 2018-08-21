package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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
import com.secretk.move.utils.GlideUtils;
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
    @BindView(R.id.ll_not)
    LinearLayout llNot;
    private SelectProjectAdapter adapter;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvFollws)
    TextView tvFollws;
    @BindView(R.id.iv_selected)
    ImageView ivSlected;
    private List<SearchedBean.DataBean.ProjectsBean.RowsBean> list;
    private int publicationType;
    private int projectId;
    private SearchedBean.DataBean.ProjectsBean.RowsBean beanTop;

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
        setTopData();
        initRefresh();
        publicationType = getIntent().getIntExtra("publication_type", 0);
        projectId = getIntent().getIntExtra("projectId", -1);
        setVerticalManager(recycler);
        adapter = new SelectProjectAdapter(this);
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        loadingDialog.show();
        findViewById(R.id.include_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick(null,-1);
            }
        });
    }

    private void setTopData() {
        JSONObject node = new JSONObject();
        try {
            node.put("projectCode", "FREE");
            node.put("sortType", Constants.TOPIC_SORT_BY_NUM);
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
            public void onCompleted(SearchedBean detailsBean) {
                List<SearchedBean.DataBean.ProjectsBean.RowsBean> listSearch = detailsBean.getData().getProjects().getRows();
                if(listSearch!=null){
                    llNot.setVisibility(View.VISIBLE);
                    for(int i=0;i<listSearch.size();i++){
                        beanTop = listSearch.get(i);
                        if(beanTop.getProjectId()==276){
                            GlideUtils.loadCircleProjectUrl(SelectProjectActivity.this,img, Constants.BASE_IMG_URL + beanTop.getProjectIcon());
                            tvCode.setText(beanTop.getProjectCode()+"/");
                            tvName.setText(beanTop.getProjectChineseName());
                            tvFollws.setText(beanTop.getFollowerNum()+"关注");
                            ivSlected.setSelected(true);
                        }
                    }
                }
            }
        });
    }

    private void initRefresh() {
//        refreshLayout.setEnableLoadMore(false);
//        refreshLayout.setEnableRefresh(false);
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
                initData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
    }
    int pageIndex=1;
    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectCode", "");
            node.put("sortType", 2);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", 20);
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
            public void onCompleted(SearchedBean detailsBean) {
                if (detailsBean.getData().getProjects() == null || detailsBean.getData().getProjects().getRows().size() == 0) {
                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (detailsBean.getData().getProjects().getCurPageNum() == detailsBean.getData().getProjects().getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                list =  detailsBean.getData().getProjects().getRows();
                tvCount.setText("共" + detailsBean.getData().getProjects().getRowCount() + "个币种");
                if (pageIndex > 2) {
                    adapter.addData(list);
                } else {
                    adapter.setData(list,projectId);
                }
            }


            @Override
            public void onFinish() {
                super.onFinish();
                loadingDialog.dismiss();
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
            }
        });
    }
    public static int staticProjectId =0;
    public static String staticProjectCode ="";
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
        SearchedBean.DataBean.ProjectsBean.RowsBean bean ;
        if(postion==-1){
            bean=beanTop;
        }else{
            bean = adapter.getData().get(postion);
        }
        if(listSearch!=null && listSearch.size()!=0){
            bean = listSearch.get(postion);
        }
        if (publicationType == 1) {
            IntentUtil.startProjectSimplenessActivity(bean.getProjectId(), bean.getProjectIcon(),
                    bean.getProjectChineseName(), bean.getProjectCode());
        } else if (publicationType == 2) {
            Intent intent = new Intent(this, ReleaseArticleActivity.class);
            intent.putExtra("projectId", bean.getProjectId());
            intent.putExtra("projectPay", bean.getProjectCode());
            startActivity(intent);
        } else if(publicationType == 3){
            Intent intent = new Intent(this, ReleaseDiscussActivity.class);
            intent.putExtra("projectId", bean.getProjectId());
            intent.putExtra("projectPay", bean.getProjectCode());
            startActivity(intent);
        }else{
            staticProjectId = bean.getProjectId();
            staticProjectCode = bean.getProjectCode();
           this.finish();
        }

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @OnClick({R.id.img_return, R.id.tv_search_1,R.id.tv_search})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.img_return:
                finish();
                break;
            case R.id.tv_search_1:
                intent = new Intent(this, SearchActivity.class);
                intent.putExtra("publication_type",publicationType);
                intent.putExtra("search_type",-1);
                startActivity(intent);
                break;
            case R.id.tv_search:
//                searchProject();
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
    List<SearchedBean.DataBean.ProjectsBean.RowsBean> listSearch;
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
        listSearch = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            if(list.get(i).getProjectCode().toUpperCase().contains(searchContent.toUpperCase()) ||
                    list.get(i).getProjectChineseName().toUpperCase().contains(searchContent.toUpperCase())){
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
