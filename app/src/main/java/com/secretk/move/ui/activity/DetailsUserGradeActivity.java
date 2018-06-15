package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DetailsUserGradeBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.ProjectHotDiscussAdapter;
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
    ProjectHotDiscussAdapter hotDiscussAdapter;
    private DetailsUserGradeBean.DataBean.ProjectBean projectInfo;
    private Intent intent;
    private int id;
    String code = "";
    String chineseName = "";
    String icon = "";
    @Override
    protected int setOnCreate() {
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
        intent = getIntent();
        if(intent!=null){
            id = Integer.valueOf(intent.getStringExtra("id"));
            code = intent.getStringExtra("code");
            chineseName = intent.getStringExtra("chineseName");
            icon = intent.getStringExtra("icon");
        }
        setVerticalManager(rvHotDiscuss);
        hotDiscussAdapter=new ProjectHotDiscussAdapter(this);
        rvHotDiscuss.setAdapter(hotDiscussAdapter);
        tvWriteDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=0){
                    IntentUtil.startProjectSimplenessActivity(id,icon,
                            chineseName,code);
                }
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
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
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
        List<DetailsUserGradeBean.DataBean.HotDiscussBean> hotDiscuss = bean.getData().getHotDiscuss();
        if(hotDiscuss!=null && hotDiscuss.size()!=0){
            llHotDiscuss.setVisibility(View.VISIBLE);
            tvNotDiscuss.setText("热们评论("+hotDiscuss.size()+")");
            hotDiscussAdapter.setData(hotDiscuss);
        }
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
