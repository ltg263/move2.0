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
        loadingDialog.show();
        getLoadData("");
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

    /**
     * @param sort:排毒方式     空时间   否则赞
     */
    public void getLoadData(String sort) {
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
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayoutF != null) {
                    refreshLayoutF.finishLoadMore();
                }
                loadingDialog.dismiss();
            }
        });
    }
    SmartRefreshLayout refreshLayoutF;
    public void setSmartRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        this.refreshLayoutF = smartRefreshLayout;
    }
}
