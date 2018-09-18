package com.secretk.move.ui.fragment;

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
import com.secretk.move.bean.SearchContentBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MainRfFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：主页 --悬赏
 */
public class MainBlueZxFragment extends LazyFragment implements ItemClickListener {
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
    private MainRfFragmentRecyclerAdapter adapter;
    int pageIndex = 1;
    boolean showFragment = false;
    String tokenLs = "";

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_gz;
    }

    @Override
    public void initViews() {
        setVerticalManager(recycler);
        initRefresh();
        adapter = new MainRfFragmentRecyclerAdapter(getActivity());
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
            public void onLoadMore(RefreshLayout refreshlayout) {
                onFirstUserVisible();
            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLoginZt){
                    pageIndex=1;
                    onFirstUserVisible();
                }else {
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
            }
        });
    }

    @Override
    public void onFirstUserVisible() {
        isLoginZt=true;
        tokenLs = token;
        if (!isLoginZt) {
            showFragment = true;
            refreshLayout.setVisibility(View.GONE);
            convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
            tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_go_login));
            tvSubmit.setText(getActivity().getResources().getString(R.string.go_login));
            tvName.setVisibility(View.VISIBLE);
            tvName.setText("您尚未登陆,无法预览已关注内容");
            return;
        }
        if (!showFragment) {
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
                .url(Constants.NEWEST_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchContentBean.class, new HttpCallBackImpl<SearchContentBean>() {
            @Override
            public void onCompleted(SearchContentBean bean) {
                SearchContentBean.DataBean detailsBean = bean.getData();
                if (detailsBean.getCurPageNum() >= detailsBean.getPageCount()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (detailsBean.getRows() == null && pageIndex == 2) {
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_not_gznr));
                    tvName.setVisibility(View.INVISIBLE);
                    tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                refreshLayout.setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.no_data).setVisibility(View.GONE);
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onError(String message) {
                if(message.equals("暂无数据")){
                    if(pageIndex > 2){
                        refreshLayout.finishLoadMoreWithNoMoreData();
                    }else {
                        convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                        tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_not_gznr));
                        tvName.setVisibility(View.INVISIBLE);
                        rlTopTheme.setVisibility(View.VISIBLE);
                        tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
                        refreshLayout.setVisibility(View.GONE);
                        refreshLayout.setEnableLoadMore(false);
                    }
                }
            }
            @Override
            public void onFinish() {
                super.onFinish();
//                if (refreshLayout.isRefreshing()) {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
//                if (refreshLayout.isLoading()) {
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
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
//        if (isLoginZt) {
//            int postId = adapter.getDataIndex(postion).getPostId();
//            int postType = adapter.getDataIndex(postion).getPostType();
//            IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
//        } else {
//            IntentUtil.startActivity(LoginHomeActivity.class);
//        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

}
