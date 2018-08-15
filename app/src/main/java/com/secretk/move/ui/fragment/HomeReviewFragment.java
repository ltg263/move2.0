package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.adapter.MineProjectListAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.LoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:03
 * 邮箱；ltg263@126.com
 * 描述：我的主页--测评
 */

public class HomeReviewFragment extends LazyFragment{
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.iv_not_content)
    ImageView ivNotContent;
    private MineProjectListAdapter adapter;
    int pageIndex = 1;//
    public Boolean isHaveData = true;//是否还有数据
    String userId;
    private LoadingDialog loadingDialog;
    private SmartRefreshLayout smartRefreshLayout;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_home;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        adapter = new MineProjectListAdapter(getActivity());
        rvReview.setAdapter(adapter);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        userId = ((HomeActivity)context).getUserId();
        loadingDialog = ((HomeActivity) context).getLoadingDialog();
    }

    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        getLoadData();

    }
    public void getLoadData(){
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            if(StringUtil.isNotBlank(userId)){
                node.put("userId", userId);
            }
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERHOME_EVALUATION_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getEvaluations();
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
                    refreshLayouF.finishLoadMore();
                }
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }

            }
        });
    }
    SmartRefreshLayout refreshLayouF;
    public void setSmartRefreshLayout(SmartRefreshLayout smartRefreshLayout) {
        this.refreshLayouF = smartRefreshLayout;
    }
}
