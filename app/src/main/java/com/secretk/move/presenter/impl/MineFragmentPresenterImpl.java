package com.secretk.move.presenter.impl;


import android.widget.Toast;

import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.interactor.impl.MineFragmentInteractorImpl;
import com.secretk.move.presenter.MineFragmentPresenter;
import com.secretk.move.view.FragmentMineView;

/**
 * Created by zc on 2018/4/6.
 */

public class MineFragmentPresenterImpl implements MineFragmentPresenter {
    private FragmentMineView view;
    private MineFragmentInteractorImpl interactor;
    public MineFragmentPresenterImpl(FragmentMineView view) {
        this.view=view;
        interactor = new MineFragmentInteractorImpl();
    }
    @Override
    public void initialized() {
        UserLoginInfo.DataBean.UserBean infors = interactor.getInfos();
        if (infors==null){
            Toast.makeText(BaseManager.app, "登录信息有误", Toast.LENGTH_SHORT).show();
        }else {
            view.loadInfoSuccess(infors);
        }
    }
}
