package com.secretk.move.presenter.impl;

import android.text.TextUtils;

import com.secretk.move.bean.PersonInfors;
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
    }
    @Override
    public void initialized() {
        PersonInfors infors= interactor.getInfos();
        if (TextUtils.isEmpty(infors.getName())){

        }else {
            view.loadInfoSuccess(infors);
        }
    }
}
