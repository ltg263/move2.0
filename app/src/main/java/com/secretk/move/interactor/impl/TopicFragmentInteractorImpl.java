package com.secretk.move.interactor.impl;

import com.secretk.move.bean.TopicBean;
import com.secretk.move.interactor.TopicFragmentInteractor;
import com.secretk.move.listener.TopicFragmentCallBack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopicFragmentInteractorImpl implements TopicFragmentInteractor {
    private TopicFragmentCallBack callBack;

    public TopicFragmentInteractorImpl(TopicFragmentCallBack callBack) {
        this.callBack = callBack;
    }

    @Override

    public void getDataFromLocal() {
        List<TopicBean> list = new ArrayList<TopicBean>();
        TopicBean bean = new TopicBean();
        bean.setName("阿里");
        bean.setSpell("ali");
        TopicBean bean1 = new TopicBean();
        bean1.setName("张飞");
        bean1.setSpell("zhangfei");
        TopicBean bean2 = new TopicBean();
        bean2.setName("阿飞");
        bean2.setSpell("afei");
        TopicBean bean3 = new TopicBean();
        bean3.setName("倍婴美1");
        bean3.setSpell("beiyingmei");
        TopicBean bean4 = new TopicBean();
        bean4.setName("倍婴美2");
        bean4.setSpell("beiyingmei");
        TopicBean bean5 = new TopicBean();
        bean5.setName("阿里巴巴");
        bean5.setSpell("ali");
        TopicBean bean6 = new TopicBean();
        bean6.setName("阿里巴巴1");
        bean6.setSpell("ali");

        list.add(bean3);
        list.add(bean);
        list.add(bean1);
        list.add(bean1);
        list.add(bean1);
        list.add(bean1);
        list.add(bean1);
        list.add(bean1);
        list.add(bean2);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        Collections.sort(list);
        callBack.onLoadDataSuccess(list, 0);

    }

    @Override
    public void getDataFromNet() {

    }

    @Override
    public void cancelFollow() {

    }

    @Override
    public void followNow() {

    }
}
