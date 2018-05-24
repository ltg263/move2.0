package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.SysEvaluationModelBean;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.EvaluationSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    int projectId;
    SysEvaluationModelBean.DataBean.ModeDetailListBean sysEvaluationModel;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_professional));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_write, getString(R.string.evaluation_write), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_professional_item;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        sysEvaluationModel = getIntent().getParcelableExtra("sys_evaluation_model");
        String projectName = getIntent().getStringExtra("project_name");
        projectId = getIntent().getIntExtra("projectId",0);
        tvProjectName.setText(projectName);
        esViewa.setScore(sysEvaluationModel.getDetailWeight() / 10f);
        esViewa.setTvDimensionalityName(sysEvaluationModel.getDetailName());
        esViewa.setEsvBackground(R.color.app_background);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void OnToolbarRightListener() {
        JSONObject node = new JSONObject();
        try {
            node.put("modelId", sysEvaluationModel.getModelId());
            node.put("modelName", sysEvaluationModel.getDetailName());
            node.put("score", sysEvaluationModel.getTotalScore());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray array = new JSONArray();
        array.put(node);
        Intent intent = new Intent(this,EvaluationWriteActivity.class);
        intent.putExtra("professionalEvaDetail",array.toString());
        intent.putExtra("modelName",sysEvaluationModel.getDetailName());
        intent.putExtra("projectId",projectId);
        intent.putExtra("totalScore",esViewa.getTvEvaluationMun());
        intent.putExtra(Constants.ModelType.MODEL_TYPE,Constants.ModelType.MODEL_TYPE_PART);
        startActivity(intent);
    }
}
