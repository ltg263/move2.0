package com.secretk.move;

import android.app.Application;
import android.content.Context;

import com.github.moduth.blockcanary.BlockCanary;
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

    }
    /**
     * 全局上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
