package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.EvaluationSliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/17 19:27
 * 邮箱；ltg263@126.com
 * 描述：简单测评
 */
public class EvaluationSimplenessActivity extends BaseActivity {

    @BindView(R.id.es_viewa)
    EvaluationSliderView esViewa;
    @BindView(R.id.tv_evaluation_state)
    TextView tvEvaluationState;
    @BindView(R.id.et_evaluation_content)
    EditText etEvaluationContent;
    int projectId;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_simpleness));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_professional, getString(R.string.evaluation_professional), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_simpleness;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

        projectId = getIntent().getIntExtra("projectId", 0);
        esViewa.setScore(4.5f);
        esViewa.setEsvBackground(R.color.app_background);
        tvEvaluationState.setText(StringUtil.getStateValueStr(4.5f));
    }

    public void setTvEvaluationState(String value) {
        tvEvaluationState.setText(value);
    }

    @Override
    protected void initData() {
        MoveApplication.getContext().addActivity(this);
    }

    @Override
    protected void OnToolbarRightListener() {
        Intent intent = new Intent(this, EvaluationProfessionalActivity.class);
        intent.putExtra("projectId", projectId);
        intent.putExtra("projectIcon", getIntent().getStringExtra("projectIcon"));
        intent.putExtra("projectName", getIntent().getStringExtra("projectName"));
        intent.putExtra("projectPay", getIntent().getStringExtra("projectPay"));
        startActivity(intent);
    }

    @OnClick(R.id.rl_submit)
    public void onViewClicked() {
        Float num = Float.valueOf(esViewa.getTvEvaluationMun());
        if (StringUtil.isBlank(etEvaluationContent.getText().toString().trim())) {
            ToastUtils.getInstance().show("评测内容不能为空");
            return;
        }
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            //针对某个项目发表文章
            node.put("projectId", getIntent().getIntExtra("projectId", 0));
            //1-简单评测；2-全面系统专业评测;3-部分系统专业评测；4-专业评测-自定义类型
            node.put("modelType", 1);
            //	 modelType=1对应为值为“简单评测", 2 为 "ALL-专业评测" 3 为 "PART—项目立项、核心团队" 4 为 "ALL-专业评测"
            node.put("postTitle", "简单评测");
            //精确到小数点1位。简单评测 和部分评测 需要给出此值；ALL-专业评测 可以不用给。
            node.put("totalScore", num);
            //包含 fileName,fileUrl,size,extension 信息的json数组,最多3个
//            node.put("discussImages", token);
            node.put("evauationContent", etEvaluationContent.getText().toString().trim());
            //包含 modelId , modelName, score 三项的json数组
//            node.put("discussImages", token);
            //包含 tagId,tagName 的json数组，数量最多3个
//            node.put("professionalEvaDetail", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_EVALUATION)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str);
                    int postId = object.getJSONObject("data").getInt("postId");
                    IntentUtil.startPublishSucceedActivity(String.valueOf(postId),
                            getString(R.string.evaluation_simpleness), getResources().getString(R.string.evaluation_succeed), Constants.PublishSucceed.EVALUATION);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }
}
