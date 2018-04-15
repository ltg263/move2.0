package com.secretk.move.presenter.impl;

import android.text.TextUtils;

import com.secretk.move.R;
import com.secretk.move.bean.LoginRegisterBean;
import com.secretk.move.interactor.RegisterInteractor;
import com.secretk.move.interactor.impl.RegisterInteractorimpl;
import com.secretk.move.listener.RegisterRequestCallBack;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.RegisterUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.ActivityRegisterFogetView;

/**
 * Created by zc on 2018/4/7.
 */

public class RegisterPresenterImpl implements RegisterPresenter , RegisterRequestCallBack {
    private ActivityRegisterFogetView view;
    private RegisterInteractor interactor;
    public RegisterPresenterImpl(ActivityRegisterFogetView view) {
        this.view = view;
        interactor=new RegisterInteractorimpl(this);
    }
    @Override
    public void getVerification() {
        Boolean isNetworkAvailable = NetUtil.isNetworkAvailable();
        if (isNetworkAvailable==false){
            view.showMessage("当前网络不可用，请检查网络！");
            return;
        }
        interactor.getVerification();
    }
    @Override
    public void register() {
        Boolean isNetworkAvailable = NetUtil.isNetworkAvailable();
        if (isNetworkAvailable==false){
            view.showMessage("当前网络不可用，请检查网络！");
            return;
        }
        String num=view.phoneNume();
        Boolean isPhoneNum= RegisterUtils.VerificationPhoneNum(num);
        if (isPhoneNum==false){
            view.showMessage("手机号码不正确，请输入正确的手机号码");
            return;
        }
        String password=view.password();
        Boolean isPassword=RegisterUtils.VerificationPassword(password);
        if (isPhoneNum==false){
            view.showMessage("手机号码不正确，请输入正确的手机号码");
            return;
        }
        String verification=view.verification();
        if (TextUtils.isEmpty(verification)){
            view.showMessage("请输入验证码！");
            return;
        }
        LoginRegisterBean bean=new LoginRegisterBean();
        bean.setPhoneNum(num);
        bean.setPassWord(password);
        bean.setVerification(verification);
        interactor.register(bean);
    }



    @Override
    public void httpCallBack(int code) {
        if (code==200){

        }else {

        }
    }

    @Override
    public void verificationCountdown(int time) {
       if (time==0){
           String str= UiUtils.getString(R.string.get_verification);
           view.verificationCountdown(str);
       }else {
           view.verificationCountdown(time+"s");
       }
    }
}
