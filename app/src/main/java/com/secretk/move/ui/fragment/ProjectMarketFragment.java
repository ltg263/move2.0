package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.ProjectMarketBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.adapter.ProjectMarketAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：项目主页--行情
 */


public class ProjectMarketFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.ll_have_data)
    LinearLayout llHaveData;
    @BindView(R.id.iv_not_content)
    ImageView ivNotContent;

    private ProjectMarketAdapter adapter;
    public boolean isHaveData = true;
    private int pageIndex = 1;
    private String projectId;
    private LoadingDialog loadingDialog;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_market;
    }

    @Override
    public void initViews() {
        if(loadingDialog == null){
            loadingDialog=new LoadingDialog(getActivity());
        }
        setVerticalManager(rvReview);
        adapter = new ProjectMarketAdapter(getActivity());
        rvReview.setAdapter(adapter);
    }

    @Override
    public void onFirstUserVisible() {
//        loadingDialog.show();
        getLoadData();
    }


    @Override
    public void onItemClick(View view, int postion) {
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        projectId = ((ProjectActivity) context).getProjectId();
        loadingDialog = ((ProjectActivity) context).getloadingDialog();
    }
    public void onRefreshLayout(){
        pageIndex=1;
        getLoadData();
    }
    /**
     * @param :排毒方式     空时间   否则赞
     */
    public void getLoadData() {
        JSONObject node = new JSONObject();
        try {
            node.put("projectId", Integer.valueOf(projectId));
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_EXCHANGE_AND_TRAN_PAIR)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, ProjectMarketBase.class, new HttpCallBackImpl<ProjectMarketBase>() {
            @Override
            public void onCompleted(ProjectMarketBase bean) {
                ProjectMarketBase.DataBean.TransactionPairResponseBean detailsBean = bean.getData().getTransactionPairResponse();
                if (detailsBean == null) {
                    llHaveData.setVisibility(View.GONE);
                    ivNotContent.setVisibility(View.VISIBLE);
                    return;
                }
                if (detailsBean.getPageSize() == detailsBean.getCurPageNum()) {
                    if (refreshLayoutF != null) {
                        refreshLayoutF.finishLoadMoreWithNoMoreData();
                    }
                    isHaveData = false;
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    return;
                }
                llHaveData.setVisibility(View.VISIBLE);
                ivNotContent.setVisibility(View.GONE);
                List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> rows = detailsBean.getRows();
                if (pageIndex > 2) {
                    adapter.setAddData(rows);
                } else {
                    adapter.setData(rows);
                }
//                getHttpsData(rows,0);
            }

            @Override
            public void onFinish() {
                if(refreshLayoutF!=null){
                    if(refreshLayoutF.isEnableLoadMore()){
                        refreshLayoutF.finishLoadMore();
                    }
                    if(refreshLayoutF.isEnableRefresh()){
                        refreshLayoutF.finishRefresh();
                    }
                }
                loadingDialog.dismiss();
            }
        });
    }
    SmartRefreshLayout refreshLayoutF;
    public void setSmartRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        this.refreshLayoutF = smartRefreshLayout;
    }

    public void getHttpsData(final List<ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean> rows, final int index) {
        final ProjectMarketBase.DataBean.TransactionPairResponseBean.RowsBean usersBean = rows.get(index);
        RxHttpParams params = new RxHttpParams.Build()
                .url("https://data.block.cc/api/v1/ticker")
                .addQuery("market", usersBean.getExchangeName())
                .addQuery("symbol_pair", usersBean.getMainCode()+"_"+usersBean.getCoinpair())
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onError(String message) {
                usersBean.setOk(false);
            }
            @Override
            public void onCompleted(String bean) {
                try {
                    JSONObject data = new JSONObject(bean).getJSONObject("data");
                    usersBean.setOk(true);
                    usersBean.setTimestamps(data.getLong("timestamps"));
                    usersBean.setLast(data.getDouble("last"));
                    usersBean.setHigh(data.getDouble("high"));
                    usersBean.setLow(data.getDouble("low"));
                    usersBean.setBid(data.getDouble("bid"));
                    usersBean.setAsk(data.getDouble("ask"));
                    usersBean.setVol(data.getDouble("vol"));
                    usersBean.setBase_volume(data.getDouble("base_volume"));
                    usersBean.setChange_daily(data.getDouble("change_daily"));
                    usersBean.setMarket(data.getString("market"));
                    usersBean.setSymbol_name(data.getString("symbol_name"));
                    usersBean.setSymbol_pair(data.getString("symbol_pair"));
                    usersBean.setRating(data.getInt("rating"));
                    usersBean.setHas_kline(data.getBoolean("has_kline"));
                    usersBean.setUsd_rate(data.getDouble("usd_rate"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                if(index<rows.size()-1) {
                    getHttpsData(rows, index + 1);
                }else{
                    if (pageIndex > 2) {
                        adapter.setAddData(rows);
                    } else {
                        adapter.setData(rows);
                        loadingDialog.dismiss();
                    }
                    if(refreshLayoutF.isEnableLoadMore()){
                        refreshLayoutF.finishLoadMore();
                    }
                }
            }
        });
    }
}
