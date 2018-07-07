package com.secretk.move.ui.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
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
 * 时间： 2018/4/25 11:03
 * 邮箱；ltg263@126.com
 * 描述：验证码登录
 */
public class VerificationLoginActivity extends BaseActivity {

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_verification)
    EditText edVerification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.but_login)
    Button butLogin;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        AppBarHeadView mHeadView = findViewById(R.id.head_app_server);
        isLoginUi=true;
        mHeadView.setHeadBackShow(true);
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_verification_login;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        edPhone.setText(getIntent().getStringExtra("phone"));
        StringUtil.etSearchChangedListener(edPhone,butLogin,etChangListener);
        StringUtil.etSearchChangedListener(edVerification,butLogin, etChangListener);
    }
    private StringUtil.EtChange etChangListener = new StringUtil.EtChange() {
        @Override
        public void etYes() {
            if (StringUtil.isNotBlank(edPhone.getText().toString())
                    &&  StringUtil.isNotBlank(edVerification.getText().toString())) {
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
    @OnClick({R.id.get_verification, R.id.but_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verification:
                if(StringUtil.isBlank(edPhone.getText().toString())){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(getVerification.getText().toString().equals(getString(R.string.get_verification))
                        || getVerification.getText().toString().equals(getString(R.string.anew_get))){
                    recLen=60;
                    new Thread(new VerificationLoginActivity.MyThread()).start();
                    sendVerification();
                }
                break;
            case R.id.but_login:
                if(StringUtil.isBlank(edPhone.getText().toString())){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(StringUtil.isBlank(edVerification.getText().toString())){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                verificatilonLogin();
                break;
        }
    }

    /**
     * 校验验证码
     * 进行登录
     */
    private void verificatilonLogin() {
        JSONObject node = new JSONObject();
        try {
            node.put("phone", edPhone.getText().toString());
            node.put("module", "login");
            node.put("code", edVerification.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.DYNAMIC_VALIDATE_CODE_VERIFY)
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

    /**
     * 发送验证码
     */
    private void sendVerification() {
        JSONObject node = new JSONObject();
        try {
            node.put("phone", edPhone.getText().toString());
            node.put("module", "login");
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
}
