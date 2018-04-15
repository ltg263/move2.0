package com.secretk.move.base;



/**
 * Created by Administrator on 2016/9/8 0008.
 *
 *
 */
public interface BaseResponseView {
    /**
     * 显示加载进度条
     * */
    void showLoading();
    /**
     * 隐藏加载的进度条
     * */
    void hideLoading();
    /**
     * 报错的返回
     * */
    void showMessage(String error);
}
