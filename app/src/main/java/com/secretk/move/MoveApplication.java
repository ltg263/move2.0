package com.secretk.move;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.github.moduth.blockcanary.BlockCanary;
import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.secretk.move.baseManager.AppConfig;
import com.secretk.move.http.Network;
import com.secretk.move.utils.AppBlockCanaryContext;
import com.secretk.move.utils.CrashHandler;


/**
 * Created by zc on 2018/4/5.
 */

public class MoveApplication extends Application {
    /**
     * 上下文对象
     */
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashHandler.getInstance().init(mContext);
        BlockCanary.install(mContext, new AppBlockCanaryContext()).start();
        Network.initOkhttp(mContext);
        AppConfig.initAppConfig(this);
//        MobSDK.init(this);
        MobSDK.init(this,"wx10b942072a71089d","02bcd92a7a4c82fd6a48bb46121f6d03");
        initDao();
    }

    private void initDao() {


    }

    /**
     * 全局上下文
     */
    public static Context getContext() {
        return mContext;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }
}
