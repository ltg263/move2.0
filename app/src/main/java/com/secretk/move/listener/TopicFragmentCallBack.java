package com.secretk.move.listener;

import com.secretk.move.bean.TopicBean;

import java.util.List;

public interface TopicFragmentCallBack {
    void onLoadDataSuccess(List<TopicBean> list,int type);
    void onCancelFollowSuccess();
    void onFollowSuccess();
}
