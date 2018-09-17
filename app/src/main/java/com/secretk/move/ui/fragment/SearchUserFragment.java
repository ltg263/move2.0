package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchUserBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.SearchUserAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：搜索===用户
 */


public class SearchUserFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.iv_not_content)
    ImageView ivNotContent;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private SearchUserAdapter adapter;
    private int pageIndex = 1;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_search_content;
    }

    @Override
    public void initViews() {
        if(loadingDialog == null){
            loadingDialog=new LoadingDialog(getActivity());
        }
        setVerticalManager(rvReview);
        adapter = new SearchUserAdapter(getActivity());
        rvReview.setAdapter(adapter);
        initRefresh();
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageIndex = 1;
                getLoadData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getLoadData();
            }
        });
    }
    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        getLoadData();
    }


    @Override
    public void onItemClick(View view, int postion) {
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    private void getLoadData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("type", 3);
            node.put("title", searchTxt);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.INDEX_SEARCH)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchUserBean.class, new HttpCallBackImpl<SearchUserBean>() {
            @Override
            public void onCompleted(SearchUserBean bean) {
                SearchUserBean.DataBean detailsBean = bean.getData();
                if (detailsBean.getPageSize() == detailsBean.getCurPageNum()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    return;
                }
                refreshLayout.setVisibility(View.VISIBLE);
                ivNotContent.setVisibility(View.GONE);
                if (pageIndex > 2) {
                    adapter.addData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if(refreshLayout.isEnableLoadMore()){
                    refreshLayout.finishLoadMore();
                }
                if(refreshLayout.isEnableRefresh()){
                    refreshLayout.finishRefresh();
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onError(String message) {
                if(message.equals("暂无数据") && !(pageIndex > 2)){
                    refreshLayout.setVisibility(View.GONE);
                    ivNotContent.setVisibility(View.VISIBLE);
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        });
    }
    public void onLoadData(String searchTxt) {
        this.searchTxt = searchTxt;
        pageIndex = 1;
        getLoadData();
    }
    String searchTxt;
    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }
}
