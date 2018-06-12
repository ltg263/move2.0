package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.SysEvaluationModelBean;
import com.secretk.move.ui.adapter.EvaluationCompileAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ProgressBarStyleView;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/19 14:42
 * 邮箱；ltg263@126.com
 * 描述：专业评测-完整版
 */
public class EvaluationCompileListActivity extends BaseActivity {
    @BindView(R.id.pb_comprehensive_evaluation)
    ProgressBarStyleView pbComprehensiveEvaluation;
    @BindView(R.id.rv_evaluation_list)
    RecyclerView rvEvaluationList;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.rsv)
    RecycleScrollView rsv;
    private EvaluationCompileAdapter adapter;
    int projectId;
    List<SysEvaluationModelBean.DataBean.ModeDetailListBean> sysEvaluationModel;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_next, getString(R.string.evaluation_next), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_compile_list;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        projectId = getIntent().getIntExtra("projectId",0);
        setVerticalManager(rvEvaluationList);
        adapter = new EvaluationCompileAdapter(this);
        rvEvaluationList.setAdapter(adapter);
        rsv.setFocusable(true);
        rsv.setFocusableInTouchMode(true);
        sysEvaluationModel =
                getIntent().getParcelableArrayListExtra("sys_evaluation_model");
        String projectName = getIntent().getStringExtra("projectName");
        String projectPay = getIntent().getStringExtra("projectPay");
        mHeadView.setTitle(projectPay+"-完整版"+getString(R.string.evaluation_professional));
        tvProjectName.setText(projectPay+"/"+projectName);

        pbComprehensiveEvaluation.setTvOne(getResources().getString(R.string.comprehensive_evaluation), 0,
                getResources().getColor(R.color.title_gray));
        pbComprehensiveEvaluation.setTvThree(getComprehensiveGrade(), 16, R.color.app_background);
        pbComprehensiveEvaluation.setPbProgressMaxVisible();

        adapter.setData(sysEvaluationModel);

    }

    @Override
    protected void OnToolbarRightListener() {
        JSONArray professionalEvaDetail = new JSONArray();
        for(int i=0;i<sysEvaluationModel.size();i++){
            JSONObject node = new JSONObject();
            float score = sysEvaluationModel.get(i).getTotalScore();
            String strValue;
            if(score==(int)score){
                strValue=String.valueOf((int)score);
            }else{
                strValue=String.valueOf(score);
            }
            try {
                node.put("modelId", sysEvaluationModel.get(i).getModelId());
                node.put("modelName", sysEvaluationModel.get(i).getDetailName());
                node.put("modelWeight", sysEvaluationModel.get(i).getDetailWeight());
                node.put("score", strValue);
                professionalEvaDetail.put(node);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        IntentUtil.startProjectCompileActivity(String.valueOf(Constants.ModelType.MODEL_TYPE_ALL),
                String.valueOf(projectId),getIntent().getStringExtra("projectPay"),
                professionalEvaDetail.toString(),pbComprehensiveEvaluation.getTotalScore(),"");
    }

    @Override
    protected void initData() {

    }

    //综合分数
    private double getComprehensiveGrade() {
        float comprehensiveGrade = 0;
        for (int i = 0; i < sysEvaluationModel.size(); i++) {
            float grade = sysEvaluationModel.get(i).getTotalScore();
            float weight = sysEvaluationModel.get(i).getDetailWeight()/100f;
            comprehensiveGrade += grade * weight;
        }
        if (comprehensiveGrade == (int) comprehensiveGrade) {
            return (int) comprehensiveGrade;
        }
        return Double.valueOf(String.format("%.1f", comprehensiveGrade));
    }

    public void setComprehensiveGrade(String tvEvaluationName, float value) {
        for (int i = 0; i < sysEvaluationModel.size(); i++) {
            SysEvaluationModelBean.DataBean.ModeDetailListBean bean = sysEvaluationModel.get(i);
            if (bean.getDetailName().equals(tvEvaluationName)) {
                bean.setTotalScore(value);
            }
        }
        pbComprehensiveEvaluation.setTvThree(getComprehensiveGrade(), 16, R.color.app_background);
    }
}
