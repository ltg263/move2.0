package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.SysEvaluationModelBean;
import com.secretk.move.ui.adapter.EvaluationTypeAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.Clickable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/17 19:27
 * 邮箱；ltg263@126.com
 * 描述：专业测评
 */
public class EvaluationProfessionalActivity extends BaseActivity {


    @BindView(R.id.rv_type_lists)
    RecyclerView rvTypeLists;
    EvaluationTypeAdapter adapter;
    @BindView(R.id.tv_status)
    TextView textView;
    @BindView(R.id.tv_zw_name)
    TextView tvZwName;
    @BindView(R.id.tv_yw_mane)
    TextView tvYwMane;
    private List<SysEvaluationModelBean.DataBean.ModeDetailListBean> listBeans;
    private int projectId;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_professional));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_simpleness, getString(R.string.evaluation_simpleness), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_professional;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        projectId = getIntent().getIntExtra("projectId",0);
        setVerticalManager(rvTypeLists);
        adapter = new EvaluationTypeAdapter(this);
        rvTypeLists.setAdapter(adapter);

        String tagAll = "经过科学筛选，系统提供以下几个评测纬度，可完整评测，" +
                "也可部分评测；您也可以自己新建模型进行测评。"
                + getResources().getString(R.string.evaluation_state);
        String tagOnly[] = new String[1];
        tagOnly[0] = getResources().getString(R.string.evaluation_state);
        Clickable.getSpannableString(tagAll, tagOnly, textView, new Clickable.ClickListener() {
            @Override
            public void setOnClick(String name) {
                ToastUtils.getInstance().show(name);
            }
        });
    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_SYSEVALUATION_MODEL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SysEvaluationModelBean.class, new HttpCallBackImpl<SysEvaluationModelBean>() {
            @Override
            public void onCompleted(SysEvaluationModelBean str) {
                listBeans = str.getData().getModeDetailList();
                adapter.setData(listBeans);
            }

            @Override
            public void onFinish() {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });

    }


    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startActivity(EvaluationSimplenessActivity.class);
    }

    @OnClick({R.id.ll_project, R.id.btn_new, R.id.btn_compile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_project:
                IntentUtil.startProjectActivity(projectId);
                break;
            case R.id.btn_new:
                Intent intents = new Intent(this,EvaluationNewActivity.class);
                intents.putExtra("projectId",projectId);
                startActivity(intents);
                break;
            case R.id.btn_compile:
                if(listBeans==null || listBeans.size()==0){
                    ToastUtils.getInstance().show("有没系统模板");
                    return;
                }
                Intent intent = new Intent(this,EvaluationCompileListActivity.class);
                intent.putExtra("projectId",projectId);
                intent.putParcelableArrayListExtra("sys_evaluation_model", (ArrayList<? extends Parcelable>) listBeans);
                startActivity(intent);
                break;
        }
    }

    public String getProjectName() {
        return tvZwName.getText().toString() + tvYwMane.getText().toString();
    }
    public int getProjectId() {
        return projectId;
    }
}
