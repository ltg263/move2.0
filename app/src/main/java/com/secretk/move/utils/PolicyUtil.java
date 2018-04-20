package com.secretk.move.utils;

import android.text.TextUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;


public class PolicyUtil {

    public static final String AES_KEY = "0987654321qazxcv";
    /***
     * 解密
     * @param policy
     * @return
     */
    public static String decryptPolicy(String policy) {
        try {
            if (!TextUtils.isEmpty(policy)) {
//                logger.info("decryptPolicy decode before : " + policy);
                policy = URLDecoder.decode(policy, "UTF-8");
//                logger.info("decryptPolicy decode after : " + policy);
                String result = AESUtil.decrypt(policy, AES_KEY);
//                logger.info("decrypt policy : " + result);
                return result;
            }
        } catch (Exception e) {
//            logger.error("decryptPolicy error : ", e);
        }
        return null;
    }
//
    /**
     * AES解密
     *
     * @param policy
     * @return
     */
    public static String decryptAES(String policy) {
        try {
            if (!TextUtils.isEmpty(policy)) {
//                logger.info("decryptAES decode before : " + policy);
                String result = AESUtil.decrypt(policy, AES_KEY);
//                logger.info("decrypt policy : " + result);
                return result;
            }
        } catch (Exception e) {
//            logger.error("decryptPolicy error : ", e);
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
            if (!TextUtils.isEmpty(policy)) {
                return URLEncoder.encode(AESUtil.encrypt(policy, AES_KEY), "UTF-8");
            }
        } catch (Exception e) {
//            logger.error("encryptPolicy error : ", e);
        }
        return null;
    }
}
