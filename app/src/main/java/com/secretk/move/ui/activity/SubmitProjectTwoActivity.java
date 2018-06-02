package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/6/1 11:12
 * 邮箱；ltg263@126.com
 * 描述：提交項目第二步
 */
public class SubmitProjectTwoActivity extends BaseActivity {

    @BindView(R.id.et_input_01)
    EditText etInput01;
    @BindView(R.id.tv_sort_name)
    TextView tvSortName;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    @BindView(R.id.ll)
    LinearLayout ll;
    private Intent data;
    private boolean isAlter = false;
    List<ProjectTypeListBean.DataBean.ProjectTypesBean> projectTypes;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.submit_project_title));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_next, getString(R.string.evaluation_next), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_submit_project_two;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        data = getIntent();
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

    @Override
    protected void OnToolbarRightListener() {
        if(isAlter){
            return;
        }
        if (StringUtil.isBlank(etInput01.getText().toString().trim())
                || StringUtil.isBlank(etContact.getText().toString().trim())
                || getString(R.string.submit_project_66).equals(tvSortName.getText().toString())) {
            ToastUtils.getInstance().show("请完善信息");
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectIcon", data.getStringExtra("projectIcon"));
            node.put("projectCode", data.getStringExtra("projectCode"));
            node.put("projectEnglishName", data.getStringExtra("projectEnglishName"));
            node.put("projectChineseName", data.getStringExtra("projectChineseName"));
            node.put("websiteUrl", data.getStringExtra("websiteUrl"));
            node.put("issueNum", Integer.valueOf(data.getStringExtra("issueNum")));
            node.put("issueDateStr", data.getStringExtra("issueDateStr"));
            node.put("listed", Integer.valueOf(data.getStringExtra("listed")));
            node.put("whitepaperUrl", etInput01.getText().toString().trim());
            node.put("projectTypeId", projectTypes.get(postion).getProjectTypeId());//项目分类ID
            node.put("projectTypeName", projectTypes.get(postion).getProjectTypeName());//项目分类名称
            node.put("projectDesc", etContact.getText().toString().trim());
            node.put("submitUserType", sharedUtils.get(Constants.USER_TYPE, 1));//1-普通用户；2-项目方；3-评测机构；4-机构用户
            node.put("submitUserContactInfo", sharedUtils.get(Constants.MOBILE,""));//提交人联系信息   手机号
            node.put("submitReason", "无");//推荐理由
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SUBMIT_PROJECT)//PROJECT_TYPE_LIST
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                isAlter=true;
                findViewById(R.id.submit_ok).setVisibility(View.VISIBLE);
                rlTopTheme.setVisibility(View.VISIBLE);
                ll.setVisibility(View.GONE);
                tvName.setText(getResources().getString(R.string.submit_project_jg));
                tvSubmit.setText(getResources().getString(R.string.submit_project_back));
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }
    int postion=-1;
    @OnClick({R.id.tv_submit, R.id.ll_sort_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                IntentUtil.startActivity(MainActivity.class);
                break;
            case R.id.ll_sort_name:
                if(projectTypes!=null && projectTypes.size()>0){
                    DialogUtils.showListView(this, projectTypes, new DialogUtils.ListDialogInterface() {
                        @Override
                        public void btnConfirm(int postion) {
                            SubmitProjectTwoActivity.this.postion=postion;
                            tvSortName.setText(projectTypes.get(postion).getProjectTypeName());
                        }
                    });
                }else{
                    ToastUtils.getInstance().show("获取项目分类失败");
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(isAlter){
            IntentUtil.startActivity(MainActivity.class);
           return;
        }
        super.onBackPressed();
    }
}
