package com.secretk.move.interactor;

import com.secretk.move.bean.LoginRegisterBean;

/**
 * Created by zc on 2018/4/7.
 */

public interface RegisterInteractor {
    void register(LoginRegisterBean bean);
    void getVerification();
}
