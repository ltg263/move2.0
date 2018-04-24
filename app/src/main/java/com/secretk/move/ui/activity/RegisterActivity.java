package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DynamicValidateCodeSend;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.ed_verification)
    EditText edVerification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.but_register)
    Button butRegister;

    String strYzm;
    String strPsw;
    @Override
    protected int setOnCreate() {
        return R.layout.activity_register;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        AppBarHeadView mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        etSearchChangedListener(edVerification,butRegister);
        etSearchChangedListener(edPassword,butRegister);
    }

    /**
     * 监听输入框输的变化
     */
    private void etSearchChangedListener(final EditText et, final Button btn) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                strYzm = edVerification.getText().toString().trim();
                strPsw = edPassword.getText().toString().trim();
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    if(StringUtil.isNotBlank(strYzm) && StringUtil.isNotBlank(strPsw)){
                        btn.setSelected(true);
                    }else{
                        btn.setSelected(false);
                    }
                }
                if (et.getText().toString().trim().length() == 0) {
                    btn.setSelected(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void initData() {

    }

    private int recLen = -1;
    @OnClick({R.id.get_verification, R.id.but_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verification:
                if(getVerification.getText().toString().equals(getString(R.string.get_verification))){
                    recLen=60;
                    new Thread(new MyThread()).start();
                    sendVerification();
                }
                break;
            case R.id.but_register:
                if(StringUtil.isBlank(strYzm) || StringUtil.isBlank(strPsw)){
                    ToastUtils.getInstance().show("填写不完整");
                    return;
                }
                if(strPsw.length()<5 || strPsw.length()>16){
                    ToastUtils.getInstance().show("请保持密码长度在6-16位");
                    return;
                }
                userRegister();
                break;
        }
    }

    /**
     * 发送验证码
     */
    private void sendVerification() {
        JSONObject node = new JSONObject();
        try {
            node.put("phone", getIntent().getStringExtra("phone"));
            node.put("module", "register");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.DYNAMIC_VALIDATE_CODE_SEND)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, DynamicValidateCodeSend.class, new HttpCallBackImpl<DynamicValidateCodeSend>() {
            @Override
            public void onCompleted(DynamicValidateCodeSend contactsBean) {
                LogUtil.w(contactsBean.getMsg());
                ToastUtils.getInstance().show(contactsBean.getData().getDynamicCode());
            }
        });
    }

    /**
     * 用户注册
     */
    private void userRegister() {
        JSONObject node = new JSONObject();
        try {
            node.put("phoneNumber", getIntent().getStringExtra("phone"));
            node.put("password", strPsw);
            node.put("dynamicVerifyCode", strYzm);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USER_REGISTER)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    String token = jsonObject.getJSONObject("data").getString("token");

                } catch (JSONException e) {
                    e.printStackTrace();
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
                        getVerification.setText(getString(R.string.get_verification));
                    }
            }
            super.handleMessage(msg);
        }
    };
}
