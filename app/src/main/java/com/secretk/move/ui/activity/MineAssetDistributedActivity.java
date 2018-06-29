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
import com.secretk.move.bean.DistributedList;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MineAssetDistributeAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/29 13:57
 * 邮箱；ltg263@126.com
 * 描述：发放中
 */
public class MineAssetDistributedActivity extends BaseActivity {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    int pageIndex = 1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.tv_b)
    TextView tvB;
    private MineAssetDistributeAdapter adapter;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.send));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_distributed;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MineAssetDistributeAdapter(this);
        rvReview.setAdapter(adapter);
        initRefresh();
        //暂不支持上拉下拉
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);
        loadingDialog.show();
    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            loadingDialog.dismiss();
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
//            node.put("pageIndex", pageIndex++);//帖子ID
//            node.put("pageSize", Constants.PAGE_SIZE);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.TOKEN_IN_DISTRIBUTED)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, DistributedList.class, new HttpCallBackImpl<DistributedList>() {
            @Override
            public void onCompleted(DistributedList bean) {
                tvTop.setText(String.valueOf(bean.getData().getSum().getGiveNext()));
                tvB.setText(String.valueOf(bean.getData().getSum().getRewardToken()));
                List<DistributedList.DataBean.InDistributionBean> detailsBean = bean.getData().getInDistribution();
                if (detailsBean == null || detailsBean.size() == 0) {
                    return;
                }
                adapter.setData(detailsBean);
            }

            @Override
            public void onFinish() {
//                if (refreshLayout.isRefreshing()) {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
//                if (refreshLayout.isLoading()) {
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
                }
                loadingDialog.dismiss();
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
                refreshLayout.setNoMoreData(false);
                pageIndex = 1;
                initData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                initData();
            }
        });
    }
}
