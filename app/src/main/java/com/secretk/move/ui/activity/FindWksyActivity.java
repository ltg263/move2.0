package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.secretk.move.bean.FindWksyList;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.FindWksyAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/7/31 10:05
 * 邮箱；ltg263@126.com
 * 描述：挖矿收益
 */
public class FindWksyActivity extends BaseActivity{

    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    private String postId;
    private int pageIndex = 1;
    private FindWksyAdapter adapter;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_collect;
    }
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getResources().getString(R.string.home_find_wk_3));
//        mMenuInfos.add(0, new MenuInfo(R.string.home_find_wk_1, getString(R.string.home_find_wk_1), 0));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        setVerticalManager(rvCollect);
        adapter = new FindWksyAdapter(this);
        rvCollect.setAdapter(adapter);
        initRefresh();
        loadingDialog.show();
        rlTopTheme.setVisibility(View.VISIBLE);
        tvIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_data));
        tvName.setText(getResources().getString(R.string.not_collect));
        tvSubmit.setText(getResources().getString(R.string.not_go_look));
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
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

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);//帖子ID
            node.put("pageSize", Constants.PAGE_SIZE);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_USER_REWARD_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, FindWksyList.class, new HttpCallBackImpl<FindWksyList>() {
            @Override
            public void onCompleted(FindWksyList bean) {
                FindWksyList.DataBeanX.DataBean detailsBean = bean.getData().getData();
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
//                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
//                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        IntentUtil.startActivity(MainActivity.class);
        finish();
    }
}
