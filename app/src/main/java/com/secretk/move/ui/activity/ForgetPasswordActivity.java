package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.ActivityRegisterFogetView;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/4/24 17:01
 * 邮箱；ltg263@126.com
 * 描述：忘记密码
 */
public class ForgetPasswordActivity extends BaseActivity{

    @BindView(R.id.ed_phone)
    EditText edPhone;
    @BindView(R.id.ed_verification)
    EditText edVerification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.ed_passwoord)
    EditText edPassword;
    @BindView(R.id.but_register)
    Button butRegister;

    String strPhone;
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
        etSearchChangedListener(edPhone,butRegister);
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
                strPhone = edPhone.getText().toString().trim();
                strYzm = edVerification.getText().toString().trim();
                strPsw = edPassword.getText().toString().trim();
                if (s.length() != 0 && et.getText().toString().trim().length() != 0) {
                    if(StringUtil.isNotBlank(strPhone)
                            && StringUtil.isNotBlank(strYzm) && StringUtil.isNotBlank(strPsw)){
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

    @OnClick({R.id.get_verification, R.id.but_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verification:
                break;
            case R.id.but_register:
                if(StringUtil.isBlank(strPhone) || StringUtil.isBlank(strYzm) || StringUtil.isBlank(strPsw)){
                    ToastUtils.getInstance().show("填写不完整");
                    return;
                }
                if(!StringUtil.isMobileNO(strPhone)){
                    ToastUtils.getInstance().show("手机号格式不对");
                    return;
                }
                if(strPsw.length()<5 || strPsw.length()>16){
                    ToastUtils.getInstance().show("请保持密码长度在6-16位");
                    return;
                }
                break;
        }
    }
}
