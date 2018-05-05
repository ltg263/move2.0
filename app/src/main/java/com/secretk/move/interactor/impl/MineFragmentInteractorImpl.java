package com.secretk.move.interactor.impl;

import com.google.gson.Gson;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.SharedUtils;

/**
 * Created by zc on 2018/4/6.
 */

public class MineFragmentInteractorImpl {
    public MineFragmentInteractorImpl() {
    }

    public UserLoginInfo.DataBean.UserBean getInfos(){
       String  userInfo = SharedUtils.singleton().get("userInfo","");
       LogUtil.w("userInfo:"+userInfo);
        Gson gson = new Gson();
        return gson.fromJson(userInfo, UserLoginInfo.DataBean.UserBean.class);
    }
}
