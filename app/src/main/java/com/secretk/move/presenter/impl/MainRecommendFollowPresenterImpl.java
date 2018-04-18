package com.secretk.move.presenter.impl;

import com.secretk.move.interactor.MainRecommendFollowInteractor;
import com.secretk.move.interactor.impl.MainRecommendFollowInteractorImpl;
import com.secretk.move.presenter.MainRecommendFollowPresenter;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.view.UiListView;

/**
 * Created by zc on 2018/4/17.
 */

public class MainRecommendFollowPresenterImpl implements MainRecommendFollowPresenter {
    UiListView view;
    MainRecommendFollowInteractor interactor;
    public MainRecommendFollowPresenterImpl(UiListView view) {
        this.view = view;
        interactor=new MainRecommendFollowInteractorImpl();
    }

    @Override
    public void init() {
        if (NetUtil.isNetworkAvailable()){
            interactor.getData();
        }else {
            view.showMessage("当前网络不可用！");
        }

    }

    @Override
    public void upvote() {
        if (NetUtil.isNetworkAvailable()){
            interactor.getData();
        }else {
            view.showMessage("当前网络不可用,请检查网络");
        }
    }

    @Override
    public void comment() {

    }
}
