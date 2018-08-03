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
import com.secretk.move.bean.ProjectByTabBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MainProjectListAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：主页 --主页项目
 */
public class MainProjectOneFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private LinearLayoutManager layoutManager;
    private MainProjectListAdapter adapter;
    protected LoadingDialog loadingDialog;
    private String tokenLs = "";
    boolean showFragment = false;//需要弹框
    int pageIndex = 1;
    int tabId;

    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_project;
    }

    @Override
    public void initViews() {
        initRefresh();
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new MainProjectListAdapter(getActivity());
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        loadingDialog = new LoadingDialog(getActivity());
    }
    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex=1;
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
    @Override
    public void onUserVisible() {
        boolean isFollowerSky = SharedUtils.singleton().get("isFollowerSky",false);
        if(isFollowerSky){
            onFirstUserVisible();
        }
        super.onUserVisible();
    }

    @Override
    public void onFirstUserVisible() {
        if(!showFragment){
            loadingDialog.show();
        }
        showFragment=true;
        tokenLs = token;
        final String token = SharedUtils.singleton().get("token", "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", 10);
            node.put("tabId", tabId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
//                .url(Constants.MAIN_BLUE_SKY)
                .url(Constants.GET_PROJECT_BY_TAB_ID)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, ProjectByTabBean.class, new HttpCallBackImpl<ProjectByTabBean>() {
            @Override
            public void onCompleted(ProjectByTabBean bean) {
                SharedUtils.singleton().put("isFollowerSky",false);
                ProjectByTabBean.DataBean.ProjectResponsePageBean detailsBean = bean.getData().getProjectResponsePage();
                if ((detailsBean == null || detailsBean.getRows()==null) && pageIndex == 2) {
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    rlTopTheme.setVisibility(View.VISIBLE);
                    tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_not_gznr));
                    tvName.setVisibility(View.INVISIBLE);
                    tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                refreshLayout.setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.no_data).setVisibility(View.GONE);
                List<ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean> rows = detailsBean.getRows();
                getCoinmarketcap(rows,0);
            }

            @Override
            public void onError(String message) {
                if(message.contains("用户未登录,请重新登录")){
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    rlTopTheme.setVisibility(View.VISIBLE);
                    tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_go_login));
                    tvName.setVisibility(View.VISIBLE);
                    tvSubmit.setText(getActivity().getResources().getString(R.string.go_login));
                    refreshLayout.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getCoinmarketcap(final List<ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean> rows , final int index) {
        final ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean row = rows.get(index);
        if(row!=null){
            NetUtil.getCoinmarketcapTicker(String.valueOf(row.getCmcId()), "CNY", new NetUtil.SaveCommendationImp() {
                @Override
                public void finishCommendation(String str, String donateNum, boolean status) {
                    if(status){
                        try {
                            JSONObject obj = new JSONObject(str);
                            if(obj.getJSONObject("data")!=null){
                                JSONObject cny = obj.getJSONObject("data").getJSONObject("quotes").getJSONObject("CNY");
                                row.setPrice(cny.getDouble("price"));
                                row.setVolume_24h(cny.getDouble("volume_24h"));
                                row.setMarket_cap(cny.getLong("market_cap"));
                                row.setPercent_change_1h(cny.getLong("percent_change_1h"));
                                row.setPercent_change_24h(cny.getDouble("percent_change_24h"));
                                row.setPercent_change_7d(cny.getDouble("percent_change_7d"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                        row.setPrice(-1);
                    }
                    if(index<rows.size()-1){
                        getCoinmarketcap(rows, index + 1);
                    }else {
                        if (pageIndex > 2) {
                            adapter.setAddData(rows);
                        } else {
                            adapter.setData(rows);
                            loadingDialog.dismiss();
                        }
                        if(refreshLayout.isEnableRefresh()){
                            refreshLayout.finishRefresh();
                        }
                        if(refreshLayout.isEnableLoadMore()){
                            refreshLayout.finishLoadMore();
                        }
                    }
                }
            });
        }
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
            ProjectByTabBean.DataBean.ProjectResponsePageBean.RowsBean bean = adapter.getDataIndex(postion);
            IntentUtil.startProjectActivity(bean.getProjectId());
        } else {
            IntentUtil.startActivity(LoginHomeActivity.class);
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
