package com.secretk.move.baseManager;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.secretk.move.MoveApplication;

import java.util.Stack;


/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 */
public class BaseManager {

    public static MoveApplication app;
    public static Handler baseHandler;

    private static Stack<Activity> activityStack;
    public static void init(Application context) {
        app = (MoveApplication) context;
        baseHandler = new Handler();
    }
    public static MoveApplication getInstance() {
        return app;
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
}
