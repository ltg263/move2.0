package com.secretk.move.interactor.impl;

import com.secretk.move.bean.LoginRegisterBean;
import com.secretk.move.interactor.RegisterInteractor;
import com.secretk.move.listener.RegisterRequestCallBack;

/**
 * Created by zc on 2018/4/7.
 */

public class RegisterInteractorimpl implements RegisterInteractor {
    private RegisterRequestCallBack callBack;
    public RegisterInteractorimpl(RegisterRequestCallBack callBack) {
        this.callBack = callBack;
    }
    @Override
    public void getVerification() {

    }
    @Override
    public void register(LoginRegisterBean bean) {

    }


}
