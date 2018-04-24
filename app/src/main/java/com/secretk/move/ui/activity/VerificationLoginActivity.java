package com.secretk.move.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.ActivityRegisterFogetView;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;
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
    protected int setOnCreate() {
        return R.layout.activity_verification_login;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        toolbar.setNavigationIcon(R.drawable.toobar_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
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
