package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.UserLoginInfo;
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
 * 时间： 2018/4/25 9:58
 * 邮箱；ltg263@126.com
 * 描述：老用户登录界面
 */

public class LoginActivity extends BaseActivity {
    AppBarHeadView mHeadView;
    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.tv_verification_code_login)
    TextView tvVerificationCodeLogin;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;
    @BindView(R.id.iv_visible)
    ImageView ivVisible;
    @BindView(R.id.but_login)
    Button butLogin;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_login;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        isLoginUi=true;
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        //mHeadView.setTitle(getString(R.string.registered_account));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        StringUtil.etSearchChangedListener(edPassword,butLogin,etChangListener);
        StringUtil.etSearchChangedListener(edPhone,butLogin,etChangListener);
        String phone = getIntent().getStringExtra("phone");
        if(StringUtil.isNotBlank(phone)){
            edPhone.setText(phone);
        }
    }
    private StringUtil.EtChange etChangListener = new StringUtil.EtChange() {
        @Override
        public void etYes() {
            if(StringUtil.isNotBlank(edPassword.getText().toString())
                    && StringUtil.isNotBlank(edPhone.getText().toString())){
                butLogin.setSelected(true);
            }else{
                butLogin.setSelected(false);
            }
        }
    };

    protected void initData() {

    }


    @OnClick({R.id.tv_verification_code_login, R.id.tv_forget_password, R.id.but_login,R.id.iv_visible})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_verification_code_login:
                IntentUtil.startActivity(VerificationLoginActivity.class);
                break;
            case R.id.tv_forget_password:
                IntentUtil.startActivity(ForgetPasswordActivity.class);
                break;
            case R.id.but_login:
                login();
                break;
            case R.id.iv_visible:
                if(ivVisible.isSelected()){
                    edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivVisible.setSelected(false);
                }else{
                    edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivVisible.setSelected(true);
                }
                break;
        }
    }

    private void login() {
        JSONObject node = new JSONObject();
        try {
            node.put("loginName", edPhone.getText().toString());
            node.put("password", edPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USER_LOGIN)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, UserLoginInfo.class, new HttpCallBackImpl<UserLoginInfo>() {
            @Override
            public void onCompleted(UserLoginInfo userInfo) {
                //将token存入Shared中
                sharedUtils.put(Constants.TOKEN_KEY,userInfo.getData().getToken());
                //登录的状态
                sharedUtils.put(Constants.IS_LOGIN_KEY,true);
                ////用户信息
//                UserLoginInfo.DataBean dataBean = userInfo.getData();
                sharedUtils.put(Constants.USER_INFOS,userInfo.getData().getUser().toString());
                sharedUtils.put(Constants.USER_TYPE,userInfo.getData().getUser().getUserType());
                sharedUtils.put(Constants.MOBILE,userInfo.getData().getUser().getMobile());
                sharedUtils.put(Constants.USER_ID,userInfo.getData().getUser().getUserId());
                IntentUtil.startActivity(MainActivity.class);
            }

            @Override
            public void onFinish() {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }
}
