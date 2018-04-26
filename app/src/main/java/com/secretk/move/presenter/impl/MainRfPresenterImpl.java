package com.secretk.move.presenter.impl;

import com.secretk.move.bean.returnbean.MainRfBean;
import com.secretk.move.contract.MainRfContract;
import com.secretk.move.interactor.MainRfInteractorImpl;
import com.secretk.move.utils.NetUtil;

import java.util.List;

/**
 * Created by zc on 2018/4/27.
 */

public class MainRfPresenterImpl implements MainRfContract.Presenter, MainRfContract.CallBack {
    MainRfContract.View view;
    MainRfContract.Interactor interactor;
    /**
     * 当前加载的角标
     * 初始化是加载头
     * 角标为0
     * 每次下来一下角标+1
     */
    private int current_position = 0;

    private int type;
    public MainRfPresenterImpl(MainRfContract.View view, int type) {
        this.view = view;
        interactor = new MainRfInteractorImpl(this);
        this.type = type;
    }

    @Override
    public void loadingHead() {
        if (NetUtil.isNetworkAvailable()) {
            view.showLoading();
            if (type==0){
                interactor.loadReCommendPageIndex(current_position);
            }else {
                interactor.loadfollowPageIndex(current_position);
            }
            current_position=0;
        } else {
            view.showMsg("请检查当前网络");
        }

    }

    @Override
    public void loadingFoot() {
        if (NetUtil.isNetworkAvailable()) {
            view.showLoading();
            if (type==0){
                interactor.loadReCommendPageIndex(current_position);
            }else {
                interactor.loadfollowPageIndex(current_position);
            }
        } else {
            view.showMsg("请检查当前网络");
        }

    }

    @Override
    public void loadPageIndexSuccess(int index, List<MainRfBean.Rows> list) {
        view.hideLoading();
        current_position++;
        if (index == 0) {
            view.onloadHeadSuccess(list);
        } else {
            view.onloadMoreSuccess(list);
        }

    }

    @Override
    public void loadFail(String str) {
        view.showMsg(str);
    }

    @Override
    public void detachView() {
        view = null;
        interactor.destroy();
        interactor = null;
    }
}
