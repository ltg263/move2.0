package com.secretk.move.ui.activity;

import android.os.Bundle;
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
 * 时间： 2018/4/24 17:02
 * 邮箱；ltg263@126.com
 * 描述：登陆首页
 */

public class LoginHomeActivity extends BaseActivity {

    @BindView(R.id.head_app_server)
    AppBarHeadView headAppServer;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_old_user_login)
    TextView tvOldUserLogin;
    @BindView(R.id.but_login)
    Button butLogin;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_home_login;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        AppBarHeadView mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        //mHeadView.setTitle(getString(R.string.registered_account));
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        StringUtil.etSearchChangedListener(etPhone,butLogin);
    }

    protected void initData() {

    }

    @OnClick({R.id.tv_old_user_login, R.id.but_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_old_user_login:
                IntentUtil.startActivity(LoginActivity.class);
                break;
            case R.id.but_login:
                String phoneNumber = etPhone.getText().toString().trim();
                if(StringUtil.isBlank(phoneNumber)){
                    ToastUtils.getInstance().show("手机号不能为空");
                    return;
                }
                if(!StringUtil.isMobileNO(phoneNumber)){
                    ToastUtils.getInstance().show("手机号格式不对");
                    return;
                }
                postHttpLogin(phoneNumber);
                break;
        }
    }

    private void postHttpLogin(final String phoneNumber) {
        JSONObject node = new JSONObject();
        try {
            node.put("phone", phoneNumber);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.PHONE_AVAILABLE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        //网络请求方式 默认为POST
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject jsonObject = new JSONObject(str);
                    int regIndex = jsonObject.getJSONObject("data").getInt("isRegister");
                    if(regIndex==0){
                        String key[]={"phone"};
                        String values[]={phoneNumber};
                        IntentUtil.startActivity(RegisterActivity.class,key,values);
                    }else{
                        IntentUtil.startActivity(LoginActivity.class);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
