package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.adapter.ProjectRecommendAdapter;
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
 * 描述：项目主页--讨论
 */

public class ProjectDiscussFragment extends LazyFragment {
    @BindView(R.id.rv_review_hot)
    RecyclerView rvReviewHot;
    @BindView(R.id.rv_review_newest)
    RecyclerView rvReviewNewest;
    @BindView(R.id.ll_hot)
    LinearLayout llHot;
    @BindView(R.id.ll_new)
    LinearLayout llNew;
    @BindView(R.id.iv_not_content)
    ImageView ivNotContent;
    @BindView(R.id.tv_sort)
    TextView tvSort;

    private ProjectRecommendAdapter adapterNot;
    private ProjectRecommendAdapter adapterNew;
    public boolean isHaveData = true;
    public int pageIndex = 1;
    private String projectId;
    private List<RowsBean> newData;
    private LoadingDialog loadingDialog;

    SmartRefreshLayout refreshLayout;
    public void setRefreshLayout(SmartRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }
    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_discuss;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReviewHot);
        setVerticalManager(rvReviewNewest);
        adapterNot = new ProjectRecommendAdapter(getActivity());
        rvReviewHot.setAdapter(adapterNot);
        adapterNew = new ProjectRecommendAdapter(getActivity());
        rvReviewNewest.setAdapter(adapterNew);
        tvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sort = tvSort.getText().toString();
                pageIndex = 1;
                loadingDialog.show();
                if (sort.equals(getString(R.string.sort_love))) {
                    tvSort.setText(getString(R.string.sort_time));
                } else {
                    tvSort.setText(getString(R.string.sort_love));
                }
                getLoadData(tvSort.getText().toString().trim());
            }
        });
        if(loadingDialog == null){
            loadingDialog=new LoadingDialog(getActivity());
        }
    }

    @Override
    public void onFirstUserVisible() {
        if(newData!=null && newData.size()>0){
            ivNotContent.setVisibility(View.GONE);
            llHot.setVisibility(View.VISIBLE);
            adapterNot.setData(newData);
        }
        loadingDialog.show();
        getLoadData("");
    }

    public void getLoadData(String sort) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", Integer.valueOf(projectId));
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);

            if (sort.equals(getString(R.string.sort_love))) {
                node.put("sortField", "praise_num");//需要按点赞数倒序排序的话 增加传入参数ortField 值为字符串“praise_num”
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_DISCUSS_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getDiscusses();
                if (detailsBean.getPageSize() == detailsBean.getCurPageNum()) {
                    if(refreshLayoutF!=null){
                        refreshLayoutF.finishLoadMoreWithNoMoreData();
                    }
                    isHaveData = false;
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    return;
                }
                ivNotContent.setVisibility(View.GONE);
                llNew.setVisibility(View.VISIBLE);
                if (pageIndex > 2) {
                    adapterNew.setAddData(detailsBean.getRows());
                } else {
                    adapterNew.setData(detailsBean.getRows());
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        projectId = ((ProjectActivity) context).getProjectId();
        loadingDialog = ((ProjectActivity) context).getloadingDialog();
    }

    public void initUiData(List<RowsBean> rows) {
        this.newData = rows;
    }
    SmartRefreshLayout refreshLayoutF;
    public void setSmartRefreshLayout(SmartRefreshLayout smartRefreshLayout) {

        this.refreshLayoutF = smartRefreshLayout;
    }

}
