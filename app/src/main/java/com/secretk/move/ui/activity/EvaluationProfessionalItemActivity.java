package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.SysEvaluationModelBean;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.EvaluationSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/17 19:27
 * 邮箱；ltg263@126.com
 * 描述：专业评测--单项评测
 */
public class EvaluationProfessionalItemActivity extends BaseActivity {

    @BindView(R.id.es_viewa)
    EvaluationSliderView esViewa;
    @BindView(R.id.tv_project_name)
    TextView tvProjectName;
    @BindView(R.id.tv_detail_desc)
    TextView tvDetailDesc;
    int projectId;
    SysEvaluationModelBean.DataBean.ModeDetailListBean sysEvaluationModel;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_write, getString(R.string.evaluation_write), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_professional_item;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        sysEvaluationModel = getIntent().getParcelableExtra("sys_evaluation_model");
        String projectName = getIntent().getStringExtra("projectName");
        String projectPay = getIntent().getStringExtra("projectPay");
        tvProjectName.setText(projectPay+"/"+projectName);
        projectId = getIntent().getIntExtra("projectId",0);
        mHeadView.setTitle(projectPay+"-"+sysEvaluationModel.getDetailName()+"评测");
//        esViewa.setScore(sysEvaluationModel.getDetailWeight() / 10f);
        esViewa.setScore(sysEvaluationModel.getTotalScore());
        esViewa.setTvDimensionalityName(sysEvaluationModel.getDetailName());
        esViewa.setEsvBackground(R.color.app_background);
        tvDetailDesc.setText(sysEvaluationModel.getDetailDesc());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void OnToolbarRightListener() {
        JSONObject node = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            node.put("modelId", sysEvaluationModel.getModelId());
            node.put("modelName", sysEvaluationModel.getDetailName());
            node.put("score", esViewa.getTvEvaluationMun());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(node);
        IntentUtil.startProjectCompileActivity(String.valueOf(Constants.ModelType.MODEL_TYPE_PART),
                String.valueOf(projectId),getIntent().getStringExtra("projectPay"),
                array.toString(),esViewa.getTvEvaluationMun(),sysEvaluationModel.getDetailName());
    }
}
