package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.secretk.move.bean.MainGzBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MainProjectCyAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：主页 --项目--1
 */
public class MainProjectFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
    private MainProjectCyAdapter adapter;
    int pageIndex = 1;
    String tokenLs = "";
    boolean showFragment = false;//需要弹框


    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_rf;
    }

    @Override
    public void initViews() {
        setVerticalManager(recycler);
        initRefresh();
        adapter = new MainProjectCyAdapter(getActivity());
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                onFirstUserVisible();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                onFirstUserVisible();
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        tokenLs = token;
        if (!refreshLayout.isEnableRefresh() && !refreshLayout.isEnableLoadMore() && !showFragment) {
            loadingDialog.show();
        }
        showFragment = true;
        final String token = SharedUtils.singleton().get("token", "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MAIN_RECOMMEND)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MainGzBean.class, new HttpCallBackImpl<MainGzBean>() {
            @Override
            public void onCompleted(MainGzBean bean) {
                MainGzBean.DataBean.FollowsBean detailsBean = bean.getData().getRecommends();
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
                }
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (showFragment && !tokenLs.equals(token)) {
            pageIndex = 1;
            onFirstUserVisible();
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        if (isLoginZt) {
            int postId = adapter.getDataIndex(postion).getPostId();
            int postType = adapter.getDataIndex(postion).getPostType();
            IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
        } else {
            IntentUtil.startActivity(LoginHomeActivity.class);
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
