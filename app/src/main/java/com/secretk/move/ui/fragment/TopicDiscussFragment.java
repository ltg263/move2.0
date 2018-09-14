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
import com.secretk.move.bean.SearchContentBean;
import com.secretk.move.ui.activity.TopicActivity;
import com.secretk.move.ui.adapter.MainBlFragmentRecyclerAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：话题--讨论
 */

public class TopicDiscussFragment extends LazyFragment{
    @BindView(R.id.rv_review)
    RecyclerView rvReview;

    @BindView(R.id.iv_not_content)
    ImageView ivNotContent;
    @BindView(R.id.tv_jx)
    TextView tvJx;
    @BindView(R.id.tv_zx)
    TextView tvZx;
    private int pageIndex=1;
    // 如果以项目为主就用 MineProjectListBlAdapter
    private MainBlFragmentRecyclerAdapter adapter;
    public Boolean isHaveData = true;//是否还有数据
    private int tagId;
    private LoadingDialog loadingDialog;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_topic;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        adapter = new MainBlFragmentRecyclerAdapter(getActivity());
        rvReview.setAdapter(adapter);
        if(loadingDialog==null){
            loadingDialog=new LoadingDialog(getActivity());
        }
        tvJx.setTextColor(getResources().getColor(R.color.theme_title));
        tvZx.setTextColor(getResources().getColor(R.color.app_background));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        tagId = ((TopicActivity)context).getTagId();
        loadingDialog = ((TopicActivity) context).getLoadingDialog();
    }

    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        getLoadData();

    }
    public void getLoadData(){
        JSONObject node = new JSONObject();
        try {
            node.put("token",token);
            node.put("tagId", tagId);
            node.put("type", 2);
            if(isSelectJx){
                node.put("sort",1);
            }else{
                node.put("sort",2);
            }
            node.put("pageIndex", pageIndex++);
            node.put("pageSize",  Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_POST_INFO_WITH_TAGS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchContentBean.class, new HttpCallBackImpl<SearchContentBean>() {
            @Override
            public void onCompleted(SearchContentBean bean) {
                SearchContentBean.DataBean detailsBean = bean.getData();
                if(detailsBean.getPageSize()==detailsBean.getCurPageNum()){
                    if(refreshLayouF!=null){
                        refreshLayouF.finishLoadMoreWithNoMoreData();
                    }
                    isHaveData=false;
                }
                if(detailsBean.getRows()==null ||detailsBean.getRows().size()==0){
                    return;
                }
                ivNotContent.setVisibility(View.GONE);
                rvReview.setVisibility(View.VISIBLE);
                if(pageIndex>2){
                    adapter.setAddData(detailsBean.getRows());
                }else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if(refreshLayouF!=null){
                    if(refreshLayouF.isEnableLoadMore()){
                        refreshLayouF.finishLoadMore();
                    }
                    if(refreshLayouF.isEnableRefresh()){
                        refreshLayouF.finishRefresh();
                    }
                }
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onError(String message) {
                if(message.equals("暂无数据") && !(pageIndex > 2)){
                    isHaveData=false;
                    rvReview.setVisibility(View.GONE);
                    ivNotContent.setVisibility(View.VISIBLE);
                    refreshLayouF.finishLoadMoreWithNoMoreData();
                }
            }
        });
    }

    SmartRefreshLayout refreshLayouF;
    public void setSmartRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        this.refreshLayouF = smartRefreshLayout;
    }

    public void onRefreshLayout() {
        isHaveData = true;
        refreshLayouF.setNoMoreData(false);
        pageIndex=1;
        isSelectJx=true;
        tvJx.setTextColor(getResources().getColor(R.color.app_background));
        tvZx.setTextColor(getResources().getColor(R.color.theme_title));
        getLoadData();
    }
    boolean isSelectJx = false;
    @OnClick({R.id.ll_jx, R.id.ll_zx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_jx:
                if(!isSelectJx){
                    selectType();
                }
                break;
            case R.id.ll_zx:
                if(isSelectJx){
                    selectType();
                }
                break;
        }
    }

    private void selectType() {
        if(isSelectJx){
            isSelectJx=false;
            tvJx.setTextColor(getResources().getColor(R.color.theme_title));
            tvZx.setTextColor(getResources().getColor(R.color.app_background));
        }else{
            isSelectJx=true;
            tvJx.setTextColor(getResources().getColor(R.color.app_background));
            tvZx.setTextColor(getResources().getColor(R.color.theme_title));
        }
        pageIndex = 1;
        getLoadData();
    }

}
