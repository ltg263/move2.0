package com.secretk.move.ui.activity;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;

import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.ActivityRegisterFogetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/8.
 */

public class VerificationLoginActivity extends BaseActivity implements ActivityRegisterFogetView {
    @BindView(R.id.get_verification)
    TextView get_verification;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_verification)
    EditText ed_verification;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_login);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar.setNavigationIcon(R.drawable.toobar_back);
    }

    /**
     * 登陆
     */
    @OnClick(R.id.but_login)
    public void login() {

    }
    @OnClick(R.id.toolbar)
    public void goback() {
        finish();
    }
    @Override
    public void verificationCountdown(String str) {
        get_verification.setText(str);
    }

    @Override
    public String phoneNume() {
        return ed_phone.getText().toString().trim();
    }

    @Override
    public String password() {
        return "";
    }

    @Override
    public String verification() {
        return ed_verification.getText().toString().trim();
    }

    @Override
    public void showMessage(String str) {
        ToastUtils.getInstance().show(str);
    }
}
