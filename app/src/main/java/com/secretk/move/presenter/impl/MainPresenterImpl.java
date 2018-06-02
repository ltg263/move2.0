package com.secretk.move.presenter.impl;

import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.VersionBean;
import com.secretk.move.contract.ActivityMainContract;
import com.secretk.move.interactor.impl.MainInteractorImpl;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.NetUtil;

/**
 * Created by zc on 2018/4/6.
 */

public class MainPresenterImpl implements ActivityMainContract.Presenter, ActivityMainContract.CallBack {
    private ActivityMainContract.View mainView;
    private ActivityMainContract.Interactor interactor;
    private String downLoadUrl="";
    public MainPresenterImpl(ActivityMainContract.View mainView) {
        this.mainView = mainView;
        interactor = new MainInteractorImpl(this);
    }

    @Override
    public void initialized() {
        Boolean isNetworkAvailable = NetUtil.isNetworkAvailable();
        if (isNetworkAvailable) {
           interactor.NetWorkVersion();
        } else {
          mainView.showMsg("当前网络不可用 ");
        }
    }

    @Override
    public void downLoadApk() {
        interactor.downLoadApk(downLoadUrl);
    }

    @Override
    public void requestFailed(String str) {
        mainView.showMsg(str);
    }

    @Override
    public void requestSuccess(VersionBean.DataBean bean) {
        if(bean.getForce()==1){//0普通更新，1强制更新
            downLoadUrl=bean.getUpgradeUrl();
            mainView.showDialog(bean.getUpExplain(),true);
        }else if(bean.getUpgrade()==1){//0不需更新，1需要更新
            downLoadUrl=bean.getGuideUrl();
            mainView.showDialog(bean.getUpExplain(),false);
        }else{
            LogUtil.w("没有最新版本");
        }
    }

    @Override
    public void isDownLoadSuccess(Boolean isSuccess) {
        if (isSuccess){
            mainView.showMsg("下载成功正在安装");
            interactor.install(Constants.APKPATH);
        }else {
            mainView.showMsg("当前网络不好，下载失败");
        }
    }

    @Override
    public void detachView() {
        mainView = null;
        interactor.destroy();
    }
}
