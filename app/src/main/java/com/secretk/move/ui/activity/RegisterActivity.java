package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.presenter.impl.RegisterPresenter;
import com.secretk.move.presenter.impl.RegisterPresenterImpl;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.ActivityRegisterFogetView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/5.
 */

public class RegisterActivity extends AppCompatActivity implements ActivityRegisterFogetView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.get_verification)
    TextView get_verification;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_passwoord)
    EditText ed_passwoord;
    @BindView(R.id.ed_verification)
    EditText ed_verification;
    RegisterPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar.setNavigationIcon(R.drawable.toobar_back);
        presenter=new RegisterPresenterImpl(this);

    }

    @OnClick(R.id.toolbar)
    public void goback() {

        finish();
    }

    @OnClick(R.id.get_verification)
    public void get_verification() {

        presenter.getVerification();
    }

    public void register(View view) {
        presenter.register();
    }
   /** 验证码倒计时*/
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
        return ed_passwoord.getText().toString().trim();
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
