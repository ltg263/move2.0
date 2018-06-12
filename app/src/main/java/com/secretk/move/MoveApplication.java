package com.secretk.move;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

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
import com.secretk.move.utils.CrashHandler;

import java.util.Stack;


/**
 * Created by zc on 2018/4/5.
 */

public class MoveApplication extends Application {
    /**
     * 上下文对象
     */
    private static MoveApplication mContext;
    private static Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CrashHandler.getInstance().init(mContext);
//        BlockCanary.install(mContext, new AppBlockCanaryContext()).start();
        AppConfig.initAppConfig(this);
        initDao();
        MobSDK.init(this,"25fecb277347b","32ee70fd96c34aeb83b639f7a06ae04");
    }

    private void initDao() {

    }

    /**
     * 全局上下文
     */
   /* public static Context getContext() {
        return mContext;
    }*/
    public static MoveApplication getContext() {
        return mContext;
    }

    /**
     * add Activity 添加Activity到栈
     */
    public void addActivity(Activity activity){
        if(activityStack ==null){
            activityStack =new Stack<>();
        }
        if(!activityStack.contains(activity)){
            activityStack.add(activity);
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
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
