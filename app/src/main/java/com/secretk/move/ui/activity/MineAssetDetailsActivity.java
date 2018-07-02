package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.MineAssetDetailsBean;
import com.secretk.move.ui.adapter.MineAssetDetailsAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/29 11:11
 * 邮箱；ltg263@126.com
 * 描述：账单明细
 */
public class MineAssetDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_total_assets)
    TextView tvTotalAssets;
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.rsv)
    RecycleScrollView rsv;
    private MineAssetDetailsAdapter adapter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    int pageIndex=1;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.expenditure));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_details;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MineAssetDetailsAdapter(this);
        rvReview.setAdapter(adapter);
        initRefresh();
    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MY_TOKEN_BILL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, MineAssetDetailsBean.class, new HttpCallBackImpl<MineAssetDetailsBean>() {
            @Override
            public void onCompleted(MineAssetDetailsBean rsn) {
                MineAssetDetailsBean.DataBean.MyTokenBillBean data = rsn.getData().getMyTokenBill();
                if(data.getRows()==null ||data.getRows().size()==0){
                    return;
                }
                tvTotalAssets.setText(StringUtil.getBeanString(String.valueOf(rsn.getData().getSum())));
                if (data.getCurPageNum() == data.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if(pageIndex>2){
                    adapter.setAddData(data.getRows());
                }else {
                    adapter.setData(data.getRows());
                }
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
//                if (refreshLayout.isRefreshing()) {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
//                if (refreshLayout.isLoading()) {
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
                }
            }
        });
    }
    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                initData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }
}
