package com.secretk.move;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.secretk.move.baseManager.AppConfig;
import com.umeng.commonsdk.UMConfigure;

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
//        CrashHandler.getInstance().init(mContext);
//        BlockCanary.install(mContext, new AppBlockCanaryContext()).start();
        AppConfig.initAppConfig(this);
        initSDK();
    }

    private void initSDK() {
        //分享
        MobSDK.init(this,getString(R.string.mob_sdk_key),getString(R.string.mob_sdk_var));
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(this, "5b4716e08f4a9d5392000089", "FIND", UMConfigure.DEVICE_TYPE_PHONE, "");

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

    /*static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //指定为经典Header，默认是 贝塞尔雷达Header
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }*/
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
                layout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
                layout.setEnableOverScrollBounce(false);//关闭越界回弹功能
                layout.setEnableLoadMoreWhenContentNotFull(false);
                layout.setEnableAutoLoadMore(true);
                layout.setPrimaryColorsId(R.color.background_gray, R.color.title_gray_66);//全局设置主题颜色
                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.FixedBehind);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.FixedBehind);
//                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}
