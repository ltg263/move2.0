package com.secretk.move.bean.base;


/**
 * 描    述：iwork99
 * 作    者：litongge
 * 时    间：2016/8/8 16:10
 * 版    权： 南海云
 */
public class BaseRes extends BaseInfo{

    /**
     * msg : Success
     * code : 0
     * token : 321313
     * data : {"dynamicCode":"480154"}
     * serverDatetime : 1524481019842
     */

    private String msg;
    private int code;
    private long serverDatetime;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public long getServerDatetime() {
        return serverDatetime;
    }

    public void setServerDatetime(long serverDatetime) {
        this.serverDatetime = serverDatetime;
    }


}
