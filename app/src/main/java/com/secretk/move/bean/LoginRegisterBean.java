package com.secretk.move.bean;

/**
 * Created by zc on 2018/4/7.
 */

public class LoginRegisterBean {

    String phoneNum;
    String passWord;
    String verification;
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
