package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.EvaluationNewBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.EvaluationNewAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AddDimensionalityPopupWindow;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.EvaluationSliderView;
import com.secretk.move.view.ProgressBarStyleView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/19 17:15
 * 邮箱；ltg263@126.com
 * 描述：专业自制新建评测
 */
public class EvaluationNewActivity extends BaseActivity {

    @BindView(R.id.rv_msg_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.pbs_comprehensive)
    ProgressBarStyleView pbsComprehensive;
    @BindView(R.id.tv_evaluation_object)
    TextView tvEvaluationObject;
    @BindView(R.id.zh_esv)
    EvaluationSliderView zhEsv;
    private EvaluationNewAdapter adapter;
    int projectId;


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
        return R.layout.activity_evaluation_new;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        projectId = getIntent().getIntExtra("projectId", 0);
        pbsComprehensive.setPbProgressMaxVisible();
        pbsComprehensive.setTvOne(getResources().getString(R.string.comprehensive_evaluation), 0,
                getResources().getColor(R.color.title_gray));
        pbsComprehensive.setTvThree(0, 16, R.color.app_background);


        zhEsv.setScore(8);
        zhEsv.setTvDimensionalityName(getResources().getString(R.string.comprehensive_evaluation));
        zhEsv.setEsvBackground(R.color.app_background);

        String projectName = getIntent().getStringExtra("projectName");
        String projectPay = getIntent().getStringExtra("projectPay");
        tvEvaluationObject.setText(projectPay + "/" + projectName);

        mHeadView.setTitle(projectPay + "-完整版自建模型评测");
        setVerticalManager(mRecyclerView);
        adapter = new EvaluationNewAdapter(this);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    List<EvaluationNewBean> list = new ArrayList<>();

    @OnClick(R.id.tv_add_dimensionality)
    public void onViewClicked() {

        if (list.size() > 8) {
            ToastUtils.getInstance().show("自建评测模型最多8个");
            return;
        }

        showPopupWindow(-1);

    }

    public void setCompileOnClick(int position) {
        showPopupWindow(position);
    }

    public void setDeleteOnClick(int position) {
        list.remove(position);
        adapter.notifyDataSetChanged();
        String zGrade = getComprehensiveGrade();
        zhEsv.setScore(Float.valueOf(zGrade));
        pbsComprehensive.setTvThree(Double.valueOf(zGrade), 16, R.color.app_background);
        if(list.size()==0){
            zhEsv.setScore(8);
            zhEsv.setSetSlide(true);
        }
    }

    private void showPopupWindow(final int position) {
        AddDimensionalityPopupWindow window = new AddDimensionalityPopupWindow(this, list, position, new AddDimensionalityPopupWindow.PopupOnClickListener() {
            @Override
            public void popupOnClick(View view, String name, float weight, float grade) {
                if (position != -1) {
                    EvaluationNewBean newBean = list.get(position);
                    newBean.setModelName(name);
                    newBean.setScore(grade);
                    newBean.setModelWeight((int) (weight * 100));
                    adapter.notifyDataSetChanged();
                } else {
                    EvaluationNewBean bean = new EvaluationNewBean();
                    bean.setScore(grade);
                    bean.setModelName(name);
                    bean.setModelWeight((int) (weight * 100));
                    list.add(bean);
                    adapter.setData(list);
                }
                if(list.size()>0){
                    zhEsv.setSetSlide(false);
                }
                String zGrade = getComprehensiveGrade();
                zhEsv.setScore(Float.valueOf(zGrade));
                pbsComprehensive.setTvThree(Double.valueOf(zGrade), 16, R.color.app_background);
            }
        });
        if (position != -1) {
            EvaluationNewBean newBean = list.get(position);
            window.setEtValue(newBean.getModelName(), newBean.getScore(), newBean.getModelWeight() / 100f);
        }
//        window.setSoftInputMode(1);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        window.showAtLocation(findViewById(R.id.head_app_server),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                0); // 设置layout在PopupWindow中显示的位置
    }

    @Override
    protected void OnToolbarRightListener() {
//        if (list.size() < 3) {
//            ToastUtils.getInstance().show("评测维度最少3个");
//            return;
//        }
        if(list.size()>0){
            int weight = 0;
            for (int i = 0; i < list.size(); i++) {
                weight += list.get(i).getModelWeight();
            }
            if (weight != 100) {
                ToastUtils.getInstance().show("权重占比和必须为1");
                return;
            }
        }

        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectId", projectId);//帖子ID
            node.put("professionalEvaDetail", list.toString());//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SAVE_EVALUATION_MODEL)
                .method(RxHttpParams.HttpMethod.POST)
                .addPart("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addPart("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String bean) {
//                pbsComprehensive.getTotalScore()
                IntentUtil.startProjectCompileActivity(String.valueOf(Constants.ModelType.MODEL_TYPE_ALL_NEW),
                        String.valueOf(projectId), getIntent().getStringExtra("projectPay"),
                        list.toString(), zhEsv.getTvEvaluationMun(), "");
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }

    //综合分数
    private String getComprehensiveGrade() {
        float comprehensiveGrade = 0;
        for (int i = 0; i < list.size(); i++) {
            float grade = list.get(i).getScore();
            float weight = list.get(i).getModelWeight() / 100f;
            comprehensiveGrade += grade * weight;
        }
        return String.format("%.1f", comprehensiveGrade);
    }
}
