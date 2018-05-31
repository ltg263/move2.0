package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/30 14:27
 * 邮箱；ltg263@126.com
 * 描述：第一步提交身份证
 */
public class MineApproveSubmitiCertificateActivity extends BaseActivity {
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_number)
    EditText etUserNumber;
    @BindView(R.id.ll_not)
    LinearLayout llNot;
    @BindView(R.id.rl_under_review)
    RelativeLayout rlUnderReview;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_number)
    TextView tvUserNumber;
    @BindView(R.id.ll_yes_pass)
    LinearLayout llYesPass;
    @BindView(R.id.tv_defeated)
    TextView tvDefeated;
    @BindView(R.id.but_anew)
    Button butAnew;
    @BindView(R.id.ll_not_pass)
    LinearLayout llNotPass;
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.mine_title));
        return mHeadView;
    }
    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_approve_submiti_certificate;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERCARD_STATUS)
//                .addQuery("token", token)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    JSONObject data = obj.getJSONObject("data");

                    int praiseNum = data.getInt("status");
                    //praiseNum 1 审核成功 2 审核中 3 审核不通过 4 未提交审核
                    switch (praiseNum){
                        case 1:
                            if(StringUtil.isNotBlank(data.getString("uesrRealName"))){
                                tvUserName.setText(data.getString("uesrRealName"));
                            }
                            if(StringUtil.isNotBlank(data.getString("uesrcardNum"))){
                                tvUserNumber.setText(data.getString("uesrcardNum"));
                            }
                            llYesPass.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            rlUnderReview.setVisibility(View.VISIBLE);
                            break;
                        case 3:
                            if(StringUtil.isNotBlank(data.getString("reason"))){
                                tvDefeated.setText(data.getString("reason"));
                            }
                            llNotPass.setVisibility(View.VISIBLE);
                            break;
                        case 4:
                            llNot.setVisibility(View.VISIBLE);
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(String message) {
            }
        });
    }


    @OnClick({R.id.but_next, R.id.but_anew})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_next://未提交审核  下一步
                butAnew();
                break;
            case R.id.but_anew://审核不通过 重新認證
                llNotPass.setVisibility(View.GONE);
                llNot.setVisibility(View.VISIBLE);
                break;
        }
    }
    private void butAnew() {
        String name = etUserName.getText().toString().toString();
        String number = etUserNumber.getText().toString().toString();
        if (StringUtil.isBlank(name)) {
            ToastUtils.getInstance().show(getResources().getString(R.string.mine_input_name));
            return;
        }
        if (StringUtil.isBlank(number)) {
            ToastUtils.getInstance().show(getResources().getString(R.string.mine_input_number));
            return;
        }
        if(!StringUtil.isIdcard(number)){
            ToastUtils.getInstance().show(getResources().getString(R.string.mine_input_number_ok));
            return;
        }
        String key[] = {"name", "number"};
        String values[] = {name, number};
        IntentUtil.startActivity(MineApproveSubmitiPicActivity.class, key, values);
    }
}
