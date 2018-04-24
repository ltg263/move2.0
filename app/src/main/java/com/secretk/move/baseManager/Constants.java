package com.secretk.move.baseManager;

import com.secretk.move.MoveApplication;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 * 描述：常用的常量
 */
public interface Constants {
    /**
     * AES 加密的KEY
     */
    String AES_KEY = "0987654321qazxcv";

    /**apk 在本地的存放地址*/
    String APKPATH = MoveApplication.getContext().getExternalFilesDir(null).getAbsolutePath() + "/move.apk";
    /**
     * 是否开启调试模式
     */
    boolean DEBUG = true;
    String BASE_URL = "http://47.98.197.101/tzg-rest/";

    //发送验证码通用接口
    String DYNAMIC_VALIDATE_CODE_SEND = BASE_URL+"kff/dynamicValidateCode/send";
    //验证手机号是否已经存在
    String PHONE_AVAILABLE = BASE_URL+"kff/user/register/phoneAvailable";
    //用户注册
    String USER_REGISTER = BASE_URL+"/kff/user/register";
}
