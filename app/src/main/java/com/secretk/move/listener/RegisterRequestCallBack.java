package com.secretk.move.listener;

/**
 * Created by zc on 2018/4/7.
 */

public interface RegisterRequestCallBack {
     void httpCallBack(int code);
     void verificationCountdown(int time);
}
