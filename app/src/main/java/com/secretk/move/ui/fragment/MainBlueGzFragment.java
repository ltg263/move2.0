package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MainRfFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
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
 * 描述：主页 --发现
 */
public class MainBlueGzFragment extends LazyFragment implements ItemClickListener {
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
    @BindView(R.id.rcv)
    RecycleScrollView rcv;

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
//        rlTopTheme.setVisibility(View.VISIBLE);
//        tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_go_login));
//        tvName.setVisibility(View.VISIBLE);
//        tvSubmit.setText(getActivity().getResources().getString(R.string.go_login));
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
    public void dblclickRefresh() {
        if (getUserVisibleHint()) {
            if(rcv.getScrollY()!=0){
                rcv.fullScroll(ScrollView.FOCUS_UP);
            }
            refreshLayout.autoRefresh();
        }
    }
    @Override
    public void onFirstUserVisible() {
        tokenLs = token;
        if (!isLoginZt) {
            showFragment = true;
            refreshLayout.setVisibility(View.GONE);
            rlTopTheme.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
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
                .url(Constants.MAIN_FOLLOW)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getFollows();
                LogUtil.w("detailsBean.getCurPageNum():"+detailsBean.getCurPageNum());
                LogUtil.w("detailsBean.getPageCount():"+detailsBean.getPageCount());
                if (detailsBean.getCurPageNum() == detailsBean.getPageCount()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (bean.getData().getFollows().getRows() == null && pageIndex == 2) {
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_not_gznr));
                    tvName.setVisibility(View.INVISIBLE);
                    tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if(bean.getData().getFollows().getRows() == null){
                    refreshLayout.finishLoadMoreWithNoMoreData();
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
            public void onFinish() {
                super.onFinish();
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
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
