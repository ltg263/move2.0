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

/**
 * 作者： litongge
 * 时间： 2018/6/10 14:45
 * 邮箱；ltg263@126.com
 * 描述：关注的用户
 */

public class MineAttentionUserFragment extends LazyFragment {
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
    private LoadingDialog loadingDialog;
    private LinearLayoutManager layoutManager;
    private MineAttentionAdapter adapter;
    private int pageIndexUser=1;

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
        loadingDialog=new LoadingDialog(getActivity());
        rlTopTheme.setVisibility(View.VISIBLE);
        tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_attention_user));
        tvName.setText(getActivity().getResources().getString(R.string.not_attention));
        tvSubmit.setText(getActivity().getResources().getString(R.string.not_go_look));
    }
    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndexUser=1;
                loadReCommendPageIndex();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
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
            node.put("followType", 3);
            node.put("pageIndex", pageIndexUser++);
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
                if(follows.getRows()==null ||follows.getRows().size()==0){
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (follows.getCurPageNum() == follows.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    refreshLayout.setEnableLoadMoreWhenContentNotFull(true);
                }
                if(pageIndexUser>2){
                    adapter.addData(follows.getRows());
                }else {
                    adapter.setData(follows.getRows());
                }

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
    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        IntentUtil.startActivity(MainActivity.class);
        getActivity().finish();
    }

}
