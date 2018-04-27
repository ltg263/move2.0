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
    /**
     * 应用名称
     */
    String APPNAME_ENGLISH = "move";
    /**
     *  token_key
     */
    String TOKEN_KEY = "token";
    /**
     *  登录的状态
     */
    String IS_LOGIN_KEY = "isLogin";

    /**apk 在本地的存放地址*/
    String APKPATH = MoveApplication.getContext().getExternalFilesDir(null).getAbsolutePath() + "/move.apk";
    /**
     * 是否开启调试模式
     */
    boolean DEBUG = true;
    String BASE_URL = "http://47.98.197.101/tzg-rest/";

    //发送验证码通用接口
    String DYNAMIC_VALIDATE_CODE_SEND = BASE_URL+"kff/dynamicValidateCode/send";
    //校验验证码接口。
    String DYNAMIC_VALIDATE_CODE_VERIFY = BASE_URL+"kff/dynamicValidateCode/verify";
    //验证手机号是否已经存在
    String PHONE_AVAILABLE = BASE_URL+"kff/user/register/phoneAvailable";
    //用户注册
    String USER_REGISTER = BASE_URL+"kff/user/register";
    //用户登录
    String USER_LOGIN = BASE_URL+"kff/user/login";
    //用户登录
    String FORGET_PASSWORD = BASE_URL+"kff/user/forgetPassword";
    //用户主页
    String USERHOME_INDEX = "kff/userhome/index";
}
