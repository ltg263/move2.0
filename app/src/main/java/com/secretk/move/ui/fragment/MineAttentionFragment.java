package com.secretk.move.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
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
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MineAttentionBean;
import com.secretk.move.ui.activity.MainActivity;
import com.secretk.move.ui.adapter.MineAttentionAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者： litongge
 * 时间： 2018/6/4 10:43
 * 邮箱；ltg263@126.com
 * 描述：我的关注  话题
 */

public class MineAttentionFragment extends LazyFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
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
    Unbinder unbinder;
    private LoadingDialog loadingDialog;
    private LinearLayoutManager layoutManager;
    private MineAttentionAdapter adapter;
    private int pageIndex = 1;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_mine_attention;
    }

    @Override
    public void initViews() {
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new MineAttentionAdapter(getActivity());
        recycler.setAdapter(adapter);
        initRefresh();
        loadingDialog = new LoadingDialog(getActivity());
        rlTopTheme.setVisibility(View.VISIBLE);
        tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_not_message));
        tvName.setText(getActivity().getResources().getString(R.string.not_topics));
        tvSubmit.setText(getActivity().getResources().getString(R.string.not_go_look));
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
                loadReCommendPageIndex();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                loadReCommendPageIndex();
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        loadReCommendPageIndex();
    }

    public void loadReCommendPageIndex() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("followType", 1);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MY_FOLLOW_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MineAttentionBean.class, new HttpCallBackImpl<MineAttentionBean>() {
            @Override
            public void onCompleted(MineAttentionBean mainRecommendBean) {
                MineAttentionBean.DataBean.MyFollowsBean follows = mainRecommendBean.getData().getMyFollows();
                if (follows.getRows() == null || follows.getRows().size() == 0) {
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (follows.getCurPageNum() == follows.getPageSize()) {
                    refreshLayout.setNoMoreData(true);
                }
                if (pageIndex > 2) {
                    adapter.addData(follows.getRows());
                } else {
                    adapter.setData(follows.getRows());
                }

            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore(true);
                }
                loadingDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        IntentUtil.startActivity(MainActivity.class);
        getActivity().finish();
    }
}
