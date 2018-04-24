package com.secretk.move.utils;


import com.secretk.move.baseManager.Constants;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by wj.song on 2016/5/4 0004.
 */
public class PolicyUtil {
    /***
     * 解密
     * @param policy
     * @return
     */
    public static String decryptPolicy(String policy) {
        try {
            if (StringUtil.isNotBlank(policy)) {
                LogUtil.w("decryptPolicy decode before : " + policy);
                policy = URLDecoder.decode(policy, "UTF-8");
                LogUtil.w("decryptPolicy decode after : " + policy);
                String result = AESUtil.decrypt(policy, Constants.AES_KEY);
                LogUtil.w("decrypt policy : " + result);
                return result;
            }
        } catch (Exception e) {
            LogUtil.w("decryptPolicy error : ", e);
        }
        return null;
    }

    /**
     * AES解密
     * @param policy
     * @return
     */
    public static String decryptAES(String policy){
        try {
            if (StringUtil.isNotBlank(policy)) {
                LogUtil.w("decryptAES decode before : " + policy);
                String result = AESUtil.decrypt(policy, Constants.AES_KEY);
                LogUtil.w("decrypt policy : " + result);
                return result;
            }
        } catch (Exception e) {
            LogUtil.w("decryptPolicy error : ", e);
        }
        return null;
    }

    /***
     * 加密
     * @param policy
     * @return
     */
    public static String encryptPolicy(String policy) {
        try {
            if (StringUtil.isNotBlank(policy)) {
                return URLEncoder.encode(AESUtil.encrypt(policy, Constants.AES_KEY), "UTF-8");
            }
        } catch (Exception e) {
            LogUtil.w("encryptPolicy error : ", e);
        }
        return null;
    }

    public static void main(String[] args) {

        String encryptStr = decryptPolicy("g%2FLBEh%2B3vndEGJK0PQ6W8lhhAgy%2BCV2cAFBwQaD87bmwrhsbzFKKBGeD7kK4SfIRrLJb5K3Sj9j94HI56ZbseCg%2F3o%2BBFi4Jl4%2BjnjacGD1V%2Fa171DRw51XQlnBQAQZsBu5JSSDZEMyOFlzAl6ivJKTRc6FrI9jCmDgL3uW4OasmA1dtMEGqa1OXnntdUcxCbo3uW1pN90%2BQED8Enl9Q7k%2B6YjPWj%2FEIw39XJRxSCn4TaobRoKw1xYEyciKUQChX");
        System.out.println(encryptStr);

        String str = "{\"phone\":\"15967158669\",\"module\":\"register\"}";
        String AES = encryptPolicy(str);
        String aea="";
        try {
            aea = AESUtil.encrypt(AES,"0987654321qazxcv");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(aea);
    }
}
