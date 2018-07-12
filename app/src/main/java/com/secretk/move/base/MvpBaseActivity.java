package com.secretk.move.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.igexin.sdk.PushManager;
import com.secretk.move.apiService.MoveIntentService;
import com.secretk.move.apiService.MovePushService;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by zc on 2018/4/25.
 */

public abstract class MvpBaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P presenter;
    Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Constants.IS_PUSH_SERVICE){
            PushManager.getInstance().initialize(this.getApplicationContext(), MovePushService.class);
            PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), MoveIntentService.class);
        }
        setContentView(setLayout());
        mUnBinder = ButterKnife.bind(this);
        presenter = initPresenter();
        initView();
        initData();
    }

    // 布局资源ID
    protected abstract int setLayout();

    // 初始化P层
    protected abstract P initPresenter();

    protected void initView() {
    }

    protected void initData() {
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.getInstance().show(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null)
            mUnBinder.unbind();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
