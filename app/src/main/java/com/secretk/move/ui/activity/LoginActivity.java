package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zc on 2018/4/5.
 */

public class LoginActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        toolbar.setNavigationIcon(R.drawable.toobar_back);
        toolbar.inflateMenu(R.menu.activity_login_menu);
        toolbar.setOnMenuItemClickListener(this);
    }

    private void initData() {

    }

    /**
     * 返回
     */
    @OnClick(R.id.toolbar)
    public void goback() {
        finish();
    }

    /**
     * 验证码登陆
     */
    @OnClick(R.id.tv_verification_code_login)
    public void verification_code_login() {
      Intent intent=new Intent(this,VerificationLoginActivity.class);
      startActivity(intent);
    }

    /**
     * 忘记密码
     */
    @OnClick(R.id.tv_forget_password)
    public void registered_account() {
        Intent intent = new Intent(this, ForgetPasswordActivity.class);
        startActivity(intent);
    }

    /**
     * 登陆
     */
    @OnClick(R.id.but_login)
    public void login() {
        ToastUtils.getInstance().show("but_login");
    }

    /**
     * 注册账号
     */
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        return false;
    }
}
