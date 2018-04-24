package com.secretk.move.apiService;


import com.secretk.move.utils.LogUtil;

/**
 * 作者： litongge
 * 时间：  2017/4/21 13:52
 * 邮箱；ltg263@126.com
 * 描述：http 请求返回结果
 * T：对象
 */

public abstract class HttpCallBackImpl<T> {
    /**
     * 请求返回的对象
     * @param t：对象
     */
    public abstract void onCompleted(T t);

    /**
     * 请求完成
     */
    public void onFinish() {
    }

    /**
     *  请求失败
     * @param message
     */
    public void onError(String message) {
        LogUtil.w("请求失败:"+message);
    }

}
