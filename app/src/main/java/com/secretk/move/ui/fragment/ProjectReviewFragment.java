package com.secretk.move.ui.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.bean.ProjectHomeBean;
import com.secretk.move.bean.RowsBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.adapter.ProjectRecommendAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.ProgressBarStyleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:03
 * 邮箱；ltg263@126.com
 * 描述：项目主页--测评
 */

public class ProjectReviewFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.rv_review_top)
    RecyclerView rvReviewTop;
    @BindView(R.id.pb_comprehensive_evaluation)
    ProgressBarStyleView pbComprehensiveEvaluation;//综合评分
    @BindView(R.id.pb_project_location)
    ProgressBarStyleView pbProjectLocation;//项目定位
    @BindView(R.id.pb_technical_framework)
    ProgressBarStyleView pbTechnicalFramework;//技术框架
    @BindView(R.id.pb_team_strength)
    ProgressBarStyleView pbTeamStrength;//团队实力
    @BindView(R.id.pb_project_schedule)
    ProgressBarStyleView pbProjectSchedule;//项目进度
    @BindView(R.id.pb_speculative_risk)
    ProgressBarStyleView pbSpeculativeRisk;//投资风险
    @BindView(R.id.ll_zxpc_top)
    LinearLayout llZxpcTop;
    @BindView(R.id.ll_zxpc)
    LinearLayout llZxpc;
    private ProjectRecommendAdapter adapter;
    int pageIndex = 1;
    public boolean isHaveData = true;
    private String projectId;
    private LoadingDialog loadingDialog;
    private ProjectRecommendAdapter adapterTop;
    private List<RowsBean> newData;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_review;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReview);
        setVerticalManager(rvReviewTop);
        adapter = new ProjectRecommendAdapter(getActivity());
        rvReview.setAdapter(adapter);
        adapterTop = new ProjectRecommendAdapter(getActivity());
        rvReviewTop.setAdapter(adapterTop);
    }

    @Override
    public void onFirstUserVisible() {
        if(newData!=null && newData.size()>0){
            llZxpcTop.setVisibility(View.VISIBLE);
            adapterTop.setData(newData);
        }
        loadingDialog.show();
        getLoadData(null);
    }
    public void getLoadData(final RefreshLayout refreshlayout) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", Integer.valueOf(projectId));
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_EVALUATION_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getEvaluations();
                if(detailsBean.getPageSize()==detailsBean.getCurPageNum()){
                    isHaveData=false;
                }
                if(detailsBean.getRows()==null ||detailsBean.getRows().size()==0){
                    return;
                }
                llZxpc.setVisibility(View.VISIBLE);
                if(pageIndex>2){
                    adapter.setAddData(detailsBean.getRows());
                }else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if(refreshlayout!=null){
                    refreshlayout.finishLoadmore();
                }
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }
    public void initUiData(List<RowsBean> rows) {
        this.newData = rows;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        projectId = ((ProjectActivity)context).getProjectId();
        loadingDialog = ((ProjectActivity)context).getloadingDialog();
    }

    @Override
    public void onItemClick(View view, int postion) {
        Toast.makeText(getActivity(), "评测揭秘那  我是第：" + postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }


    public void initUiDate(ProjectHomeBean data) {
        List<ProjectHomeBean.DataBean.ProjectEvaStatBean> beans = data.getData().getProjectEvaStat();
        ProjectHomeBean.DataBean.ProjectBean project = data.getData().getProject();
        if(project!=null){
            pbComprehensiveEvaluation.setTvOne(getResources().getString(R.string.comprehensive_evaluation),0,
                    getResources().getColor(R.color.title_gray));
            pbComprehensiveEvaluation.setTvTwo("("+project.getTotalRaterNum()+"人)",0,0);
            pbComprehensiveEvaluation.setTvThree(project.getTotalScore(),16,R.color.app_background);
            pbComprehensiveEvaluation.setPbProgressMaxVisible();
        }
        if(beans!=null && beans.size()>0){
            getActivity().findViewById(R.id.ll_not).setVisibility(View.VISIBLE);
            //设置评分样式
            for(int postion=0;postion<beans.size();postion++){
                ProjectHomeBean.DataBean.ProjectEvaStatBean bean = beans.get(postion);
                String detailName = bean.getDetailName();
//            if(getString(R.string.project_location).equals(detailName)){
                if(postion==0){
                    pbProjectLocation.setVisibility(View.VISIBLE);
                    pbProjectLocation.setProgressDrawable(R.drawable.pb_view_xmdw,R.color.xmdw);
                    pbProjectLocation.setAllTv(detailName,"/ "+bean.getDetailWeight()+"% ("+bean.getRaterNum()+"人)",bean.getTotalScore());
                }
//            if(getString(R.string.technical_framework).equals(detailName)){
                if(postion==1){
                    pbTechnicalFramework.setVisibility(View.VISIBLE);
                    pbTechnicalFramework.setProgressDrawable(R.drawable.pb_view_jskj,R.color.jskj);
                    pbTechnicalFramework.setAllTv(detailName,"/ "+bean.getDetailWeight()+"% ("+bean.getRaterNum()+"人)",bean.getTotalScore());
                }
//            if(getString(R.string.team_strength).equals(detailName)){
                if(postion==2){
                    pbTeamStrength.setVisibility(View.VISIBLE);
                    pbTeamStrength.setProgressDrawable(R.drawable.pb_view_tdsl,R.color.tdsl);
                    pbTeamStrength.setAllTv(detailName,"/ "+bean.getDetailWeight()+"% ("+bean.getRaterNum()+"人)",bean.getTotalScore());
                }
//            if(getString(R.string.project_schedule).equals(detailName)){
                if(postion==3){
                    pbProjectSchedule.setVisibility(View.VISIBLE);
                    pbProjectSchedule.setProgressDrawable(R.drawable.pb_view_xmjd,R.color.xmjd);
                    pbProjectSchedule.setAllTv(detailName,"/ "+bean.getDetailWeight()+"% ("+bean.getRaterNum()+"人)",bean.getTotalScore());
                }
//            if(getString(R.string.speculative_risk).equals(detailName)){
                if(postion==4){
                    pbSpeculativeRisk.setVisibility(View.VISIBLE);
                    pbSpeculativeRisk.setProgressDrawable(R.drawable.pb_view_tzfx,R.color.tzfx);
                    pbSpeculativeRisk.setAllTv(detailName,"/ "+bean.getDetailWeight()+"% ("+bean.getRaterNum()+"人)",bean.getTotalScore());
                }
            }
        }
    }
}
