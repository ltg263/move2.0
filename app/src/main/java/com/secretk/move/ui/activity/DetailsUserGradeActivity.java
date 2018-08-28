package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.bean.DetailsUserGradeBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.MainBlFragmentRecyclerAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/5/4 14:54
 * 邮箱；ltg263@126.com
 * 描述：项目评分
 */
public class DetailsUserGradeActivity extends BaseActivity implements ItemClickListener {

    @BindView(R.id.iv_project_icon)
    ImageView ivProjectIcon;
    @BindView(R.id.tv_project_code)
    TextView tvProjectCode;
    @BindView(R.id.tv_project_zw)
    TextView tvProjectZw;
    @BindView(R.id.tv_total_rater_num)
    TextView tvTotalRaterNum;
    @BindView(R.id.tv_total_score)
    TextView tvTotalScore;
    @BindView(R.id.pb_percent_5)
    ProgressBar pbPercent5;
    @BindView(R.id.tv_percent_5)
    TextView tvPercent5;
    @BindView(R.id.pb_percent_4)
    ProgressBar pbPercent4;
    @BindView(R.id.tv_percent_4)
    TextView tvPercent4;
    @BindView(R.id.pb_percent_3)
    ProgressBar pbPercent3;
    @BindView(R.id.tv_percent_3)
    TextView tvPercent3;
    @BindView(R.id.pb_percent_2)
    ProgressBar pbPercent2;
    @BindView(R.id.tv_percent_2)
    TextView tvPercent2;
    @BindView(R.id.pb_percent_1)
    ProgressBar pbPercent1;
    @BindView(R.id.tv_percent_1)
    TextView tvPercent1;
    @BindView(R.id.tv_not_discuss)
    TextView tvNotDiscuss;
    @BindView(R.id.rv_hot_discuss)
    RecyclerView rvHotDiscuss;
    @BindView(R.id.ll_hot_discus)
    LinearLayout llHotDiscuss;
    @BindView(R.id.tv_write_discuss)
    TextView tvWriteDiscuss;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    MainBlFragmentRecyclerAdapter hotDiscussAdapter;
    private Intent intent;
    private int id;
    String code = "";
    String chineseName = "";
    String icon = "";
    @Override
    protected int setOnCreate(){
        return R.layout.activity_details_user_grade;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("用户评分");
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
//        {"id","code","chineseName","icon"};
        initRefresh();
        intent = getIntent();
        if(intent!=null){
            id = Integer.valueOf(intent.getStringExtra("id"));
            code = intent.getStringExtra("code");
            chineseName = intent.getStringExtra("chineseName");
            icon = intent.getStringExtra("icon");
        }
        setVerticalManager(rvHotDiscuss);
        hotDiscussAdapter=new MainBlFragmentRecyclerAdapter(this);
        rvHotDiscuss.setAdapter(hotDiscussAdapter);
        tvWriteDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=0 && intent!=null){
                    IntentUtil.startProjectSimplenessActivity(id,icon,
                            chineseName,code);
                }
            }
        });
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageIndex = 1;
                initData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                simpleEvaluationList();
            }
        });
    }

    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", id);//查看的项目ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.EVA_STAT_SCORE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, DetailsUserGradeBean.class, new HttpCallBackImpl<DetailsUserGradeBean>() {
            @Override
            public void onCompleted(DetailsUserGradeBean bean) {
                initUiData(bean);
                simpleEvaluationList();
            }

            @Override
            public void onError(String message) {
                loadingDialog.dismiss();
            }
        });
    }
    int pageIndex = 1;
    private void simpleEvaluationList() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", id);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SIMPLE_EVALUATION_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getEvaluations();

                if(detailsBean==null){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }

                llHotDiscuss.setVisibility(View.VISIBLE);
                tvNotDiscuss.setText("热们评论("+detailsBean.getRowCount()+")");
                if (pageIndex > 2) {
                    hotDiscussAdapter.setAddData(detailsBean.getRows());
                } else {
                    hotDiscussAdapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    private void initUiData(DetailsUserGradeBean bean) {
//       projectInfo = bean.getData().getProject();
        GlideUtils.loadCircleProjectUrl(this,ivProjectIcon,Constants.BASE_IMG_URL+ icon);
        tvProjectCode.setText(StringUtil.getBeanString(code));
        tvProjectZw.setText(StringUtil.getBeanString("/"+chineseName));

        tvTotalRaterNum.setText("评分("+StringUtil.getBeanString(String.valueOf(bean.getData().getTotalRaterNum()))+")");
        tvTotalScore.setText(StringUtil.getBeanString(String.valueOf(bean.getData().getTotalScore())));

        List<DetailsUserGradeBean.DataBean.EvaGradeStatBean> statBeans = bean.getData().getEvaGradeStat();
        if(statBeans!=null){
            for(int i=0;i<statBeans.size();i++){
                switch (i){
                    case 0:
                        tvPercent1.setText(String.valueOf(statBeans.get(i).getPercent())+"%");
                        pbPercent1.setProgress(statBeans.get(i).getPercent());
                        break;
                    case 1:
                        tvPercent2.setText(String.valueOf(statBeans.get(i).getPercent())+"%");
                        pbPercent2.setProgress(statBeans.get(i).getPercent());
                        break;
                    case 2:
                        tvPercent3.setText(String.valueOf(statBeans.get(i).getPercent())+"%");
                        pbPercent3.setProgress(statBeans.get(i).getPercent());
                        break;
                    case 3:
                        tvPercent4.setText(String.valueOf(statBeans.get(i).getPercent())+"%");
                        pbPercent4.setProgress(statBeans.get(i).getPercent());
                        break;
                    case 4:
                        tvPercent5.setText(String.valueOf(statBeans.get(i).getPercent())+"%");
                        pbPercent5.setProgress(statBeans.get(i).getPercent());
                        break;
                }
            }
        }
    }


    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
