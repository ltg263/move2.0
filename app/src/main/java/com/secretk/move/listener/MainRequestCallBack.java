package com.secretk.move.listener;

import com.secretk.move.bean.VersionBean;

/**
 * Created by zc on 2018/4/6.
 */

public interface MainRequestCallBack{
    void requestFailed(String str);
    void requestSuccess(VersionBean data);
    void isDownLoadSuccess(Boolean isSuccess);
}
