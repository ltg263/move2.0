package com.secretk.move.presenter.impl;

import com.secretk.move.bean.TopicBean;
import com.secretk.move.interactor.TopicFragmentInteractor;
import com.secretk.move.interactor.impl.TopicFragmentInteractorImpl;
import com.secretk.move.listener.TopicFragmentCallBack;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.view.TopicFragmentView;

import java.util.List;

public class TopicFragmentPresenterImpl implements TopicFragmentPresenter, TopicFragmentCallBack {
    private TopicFragmentView view;
    private TopicFragmentInteractor interactor;

    public TopicFragmentPresenterImpl(TopicFragmentView view) {
        this.view = view;
        interactor = new TopicFragmentInteractorImpl(this);
    }

    @Override
    public void initialized() {
        interactor.getDataFromLocal();
    }

    @Override
    public void isFollow(TopicBean bean) {
        Boolean isAvailble = NetUtil.isNetworkAvailable();
        if (!isAvailble) {
            view.showMessage("当前网络不可用");
            return;
        }
        Boolean isFollow = bean.getFollow();
        if (isFollow) {
            interactor.cancelFollow();
        } else {
            interactor.followNow();
        }
    }

    @Override
    public void onLoadDataSuccess(List<TopicBean> list, int type) {
        if (type == 0) {
            if (list == null || list.size() == 0) {
                interactor.getDataFromNet();
            } else {
                view.loadInfoSuccess(list);
            }
        } else {
            if (list == null || list.size() == 0) {
                view.showMessage("当前没有数据");
            } else {
                view.loadInfoSuccess(list);
            }
        }

    }

    @Override
    public void onCancelFollowSuccess() {

    }

    @Override
    public void onFollowSuccess() {

    }
}
