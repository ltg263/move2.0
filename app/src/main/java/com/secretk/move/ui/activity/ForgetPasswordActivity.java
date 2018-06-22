package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
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
 * 时间： 2018/4/24 17:01
 * 邮箱；ltg263@126.com
 * 描述：忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_verification)
    EditText edVerification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.iv_visible)
    ImageView ivVisible;
    @BindView(R.id.but_login)
    Button butLogin;

    String strPhone;
    String strYzm;
    String strPsw;
    @Override
    protected int setOnCreate() {
        return R.layout.activity_forget_password;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        AppBarHeadView mHeadView = findViewById(R.id.head_app_server);
        isLoginUi=true;
        mHeadView.setTitle("忘记密码");
        mHeadView.setTitleColor(R.color.white);
        mHeadView.setHeadBackShow(true);
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        edPhone.setText(getIntent().getStringExtra("phone"));
        StringUtil.etSearchChangedListener(edPhone,butLogin,etChangListener);
        StringUtil.etSearchChangedListener(edVerification,butLogin, etChangListener);
        StringUtil.etSearchChangedListener(edPassword,butLogin, etChangListener);

    }
    private StringUtil.EtChange etChangListener = new StringUtil.EtChange() {
        @Override
        public void etYes() {
            strPhone = edPhone.getText().toString();
            strYzm = edVerification.getText().toString();
            strPsw = edPassword.getText().toString();
            if (StringUtil.isNotBlank(strPhone)
                    && StringUtil.isNotBlank(strYzm) && StringUtil.isNotBlank(strPsw)) {
                butLogin.setSelected(true);
            } else {
                butLogin.setSelected(false);
            }
        }
    };

    @Override
    protected void initData() {

    }

    private int recLen = -1;
    @OnClick({R.id.get_verification, R.id.but_login,R.id.iv_visible})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verification:
                strPhone = edPhone.getText().toString();
                if (StringUtil.isBlank(strPhone)) {
                    ToastUtils.getInstance().show("手机号不能为空");
                    return;
                }
                if (!StringUtil.isMobileNO(strPhone)) {
                    ToastUtils.getInstance().show("手机号格式不对");
                    return;
                }
                if(getVerification.getText().toString().equals(getString(R.string.get_verification))
                        || getVerification.getText().toString().equals(getString(R.string.anew_get))){
                    recLen=60;
                    new Thread(new ForgetPasswordActivity.MyThread()).start();
                    sendVerification();
                }
                break;
            case R.id.but_login:
                strPhone = edPhone.getText().toString();
                strYzm = edVerification.getText().toString();
                strPsw = edPassword.getText().toString();
                if (StringUtil.isBlank(strPhone) || StringUtil.isBlank(strYzm) || StringUtil.isBlank(strPsw)) {
                    ToastUtils.getInstance().show("填写不完整");
                    return;
                }
                if (!StringUtil.isMobileNO(strPhone)) {
                    ToastUtils.getInstance().show("手机号格式不对");
                    return;
                }
                if(strPsw.length()>5 && strPsw.length()<16){
                    resetPassword();
                }else{
                    ToastUtils.getInstance().show("请保持密码长度在6-16位");
                }
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

    /**
     *  重设密码
     */
    private void resetPassword() {
        //FORGET_PASSWORD
        JSONObject node = new JSONObject();
        try {
            node.put("phone",strPhone);
            node.put("code", strYzm);
            node.put("password", strPsw);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.FORGET_PASSWORD)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                Toast.makeText(ForgetPasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFinish() {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }

    /**
     * 倒计时
     */
    public class MyThread implements Runnable {
        @Override
        public void run() {
            while (recLen>=0) {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (Exception e) {

                }
            }
        }
    }
    final Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what) {
                case 1:
                    if(recLen>0){
                        recLen--;
                        getVerification.setText("倒计时:" + recLen);
                    }else if(recLen==0){
                        recLen=-1;
                        getVerification.setText(getString(R.string.anew_get));
                    }
            }
            super.handleMessage(msg);
        }
    };
    /**
     * 发送验证码
     */
    private void sendVerification() {
        JSONObject node = new JSONObject();
        try {
            node.put("phone",edPhone.getText().toString());
            node.put("module", "forgetPassword");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.DYNAMIC_VALIDATE_CODE_SEND)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String contactsBean) {
//                LogUtil.w(contactsBean.getMsg());
//                ToastUtils.getInstance().show(contactsBean.getData().getDynamicCode());
            }
        });
    }
}
