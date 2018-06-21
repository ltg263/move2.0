package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.ProjectTypeListBean;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/6/1 11:12
 * 邮箱；ltg263@126.com
 * 描述：提交項目第三步
 */
public class SubmitProjectThreeActivity extends BaseActivity {

    @BindView(R.id.et_input_pone)
    EditText etInputPone;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.tv_length)
    TextView tvLength;
    @BindView(R.id.tv_three_1)
    TextView tvThree1;
    @BindView(R.id.tv_three_2)
    TextView tvThree2;
    @BindView(R.id.tv_three_3)
    TextView tvThree3;
    private Intent data;
    List<ProjectTypeListBean.DataBean.ProjectTypesBean> projectTypes;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.submit_project_title));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_submit_project_three;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        data = getIntent();
        StringUtil.etSearchChangedListener(etContact, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                tvLength.setText(etContact.getText().toString().length() + "/3000");
            }

            @Override
            public void etNo() {
                tvLength.setText("0/3000");
            }
        });
    }

    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PROJECT_TYPE_LIST)//
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, ProjectTypeListBean.class, new HttpCallBackImpl<ProjectTypeListBean>() {
            @Override
            public void onCompleted(ProjectTypeListBean str) {
                projectTypes = str.getData().getProjectTypes();
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }
    int userType=1;

    @OnClick({R.id.tv_three_1, R.id.tv_three_2, R.id.tv_three_3, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_three_1:
                userType=1;
                tvThree1.setTextColor(getResources().getColor(R.color.white));
                tvThree1.setBackgroundColor(getResources().getColor(R.color.app_background));

                tvThree2.setTextColor(getResources().getColor(R.color.app_background));
                tvThree2.setBackgroundColor(getResources().getColor(R.color.white));
                tvThree3.setTextColor(getResources().getColor(R.color.app_background));
                tvThree3.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_three_2:
                userType=3;
                tvThree2.setTextColor(getResources().getColor(R.color.white));
                tvThree2.setBackgroundColor(getResources().getColor(R.color.app_background));

                tvThree1.setTextColor(getResources().getColor(R.color.app_background));
                tvThree1.setBackgroundColor(getResources().getColor(R.color.white));
                tvThree3.setTextColor(getResources().getColor(R.color.app_background));
                tvThree3.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_three_3:
                userType=4;
                tvThree3.setTextColor(getResources().getColor(R.color.white));
                tvThree3.setBackgroundColor(getResources().getColor(R.color.app_background));

                tvThree2.setTextColor(getResources().getColor(R.color.app_background));
                tvThree2.setBackgroundColor(getResources().getColor(R.color.white));
                tvThree1.setTextColor(getResources().getColor(R.color.app_background));
                tvThree1.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_submit:
                submitData();
                break;
        }
    }
    private void submitData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectIcon", data.getStringExtra("projectIcon"));
            node.put("projectCode", data.getStringExtra("projectCode"));
            node.put("projectEnglishName", data.getStringExtra("projectEnglishName"));
            node.put("projectChineseName", data.getStringExtra("projectChineseName"));
            node.put("websiteUrl", data.getStringExtra("websiteUrl"));
            node.put("issueNum", Long.valueOf(data.getStringExtra("issueNum")));
            node.put("issueDateStr", data.getStringExtra("issueDateStr"));
            node.put("listed", Integer.valueOf(data.getStringExtra("listed")));
            node.put("whitepaperUrl", data.getStringExtra("whitepaperUrl"));
            node.put("projectTypeId", Integer.valueOf(data.getStringExtra("projectTypeId")));//项目分类ID
            node.put("projectTypeName", data.getStringExtra("projectTypeName"));//项目分类名称
            node.put("projectDesc", data.getStringExtra("projectDesc"));
            node.put("projectSignature", data.getStringExtra("projectSignature"));//项目简介
            node.put("submitUserType", userType);//1-普通用户;2-利益相关;3-项目方;4-投资者
            node.put("submitUserContactInfo", etInputPone.getText().toString().trim());//提交人联系信息   手机号
            node.put("submitReason", etContact.getText().toString().trim());//推荐理由
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SUBMIT_PROJECT)//PROJECT_TYPE_LIST
                .addPart("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addPart("sign", MD5.Md5(node.toString()))
                .build();
        params.setMethod(RxHttpParams.HttpMethod.POST);
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                IntentUtil.startPublishSucceedActivity(String.valueOf(0),
                        getString(R.string.submit_project_title), getResources().getString(R.string.submit_project_jg),
                        getResources().getString(R.string.not_go_look), Constants.PublishSucceed.PUBLISH_PROJECT);
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }
}
