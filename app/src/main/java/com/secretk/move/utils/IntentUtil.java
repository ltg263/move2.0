package com.secretk.move.utils;

import android.app.Activity;
import android.content.Intent;

import com.secretk.move.MoveApplication;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.ui.activity.DetailsArticleActivity;
import com.secretk.move.ui.activity.DetailsDiscussActivity;
import com.secretk.move.ui.activity.DetailsReviewAllActivity;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.ui.activity.ProjectActivity;
import com.secretk.move.ui.activity.PublishSucceedActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;


/**
 * 文件名：com.juanct.iwork99.manager
 * 描    述：跳转管理器
 * 作    者：litongge
 * 时    间：2017/8/29 17:18
 */
public class IntentUtil {

    /**
     * 跳入指定的类
     *
     * @param cls
     */
    public static void startActivity(Class<? extends Activity> cls) {
        startActivity(cls, null, null);
    }

    /**
     * 跳入指定的类
     * 带参数
     *
     * @param cls
     */
    public static void startActivity(Class<? extends Activity> cls, String[] keys, String[] values) {
        Intent intent = new Intent(BaseManager.app, cls);
        if (keys != null && values != null) {
            if (keys.length != values.length) {
                throw new IllegalArgumentException("keys's length must be equals values's length");
            }
            for (int i = 0; i < keys.length; i++) {
                if ("isAdd".equals(keys[i])) {
                    intent.putExtra(keys[i], false);
                } else {
                    intent.putExtra(keys[i], values[i]);
                }
            }
        }
        startActivity(intent);
    }

    public static void startActivity(Intent intent) {
        Activity currentTopActivity = null;
        try {
            currentTopActivity = getCurrentTopActivity();
        } catch (Exception e) {
        }
        if (currentTopActivity != null) {
            currentTopActivity.startActivity(intent);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseManager.app.startActivity(intent);
        }
    }

    /**
     * 跳入指定的类
     * 带回调
     *
     * @param cls
     */
    public static void startActivityForResult(Class<? extends Activity> cls, int requestCode) {
        startActivityForResult(cls, requestCode, null, null);
    }

    public static void startActivityForResult(Class<? extends Activity> cls, int requestCode, String[] keys,
                                              String[] values) {
        Intent intent = new Intent(BaseManager.app, cls);
        if (keys != null && values != null) {
            if (keys.length != values.length) {
                throw new IllegalArgumentException("keys's length must be equals values's length");
            }
            for (int i = 0; i < keys.length; i++) {
                intent.putExtra(keys[i], values[i]);
            }
        }
        Activity currentTopActivity = null;
        try {
            currentTopActivity = getCurrentTopActivity();
        } catch (Exception e) {
        }
        if (currentTopActivity != null) {
            currentTopActivity.startActivityForResult(intent, requestCode);
        }
    }

    public static Activity getCurrentTopActivity()
            throws ClassNotFoundException, IllegalArgumentException, SecurityException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException, NoSuchFieldException {
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
        Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
        activitiesField.setAccessible(true);
        Map<?, ?> activities = (Map<?, ?>) activitiesField.get(activityThread);
        for (Object activityRecord : activities.values()) {
            Class<?> activityRecordClass = activityRecord.getClass();
            Field pausedField = activityRecordClass.getDeclaredField("paused");
            pausedField.setAccessible(true);
            if (!pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                return activity;
            }
        }
        return null;
    }

    /**
     * 跳转到项目的Activity
     *
     * @param projectId
     */
    public static void startProjectActivity(int projectId) {
        String key[] = {"projectId"};
        String values[] = {String.valueOf(projectId)};
        IntentUtil.startActivity(ProjectActivity.class, key, values);
    }

    /**
     * 跳转到用户的Activity
     *
     * @param userId
     */
    public static void startHomeActivity(int userId) {
        String key[] = {"userId"};
        String values[] = {String.valueOf(userId)};
        IntentUtil.startActivity(HomeActivity.class, key, values);
    }
    /**
     * 发布测评文章讨论成功以后 跳转界面
     * @param
     */
    public static void startPublishSucceedActivity(String postId,String title,String name,String type) {
        String key[] = {Constants.PublishSucceed.PUBLISH_POST_ID,Constants.PublishSucceed.SUBMIT_TITLE,Constants.PublishSucceed.SUBMIT_TEXT,Constants.PublishSucceed.PUBLISH_TYPE};
        String values[] = {postId,title,name,type};
        IntentUtil.startActivity(PublishSucceedActivity.class, key, values);
    }

    public static void go2DetailsByType(int type,String postId) {
        Intent intent=null;
        switch (type) {
            case 1:
                intent=new Intent(MoveApplication.getContext(), DetailsReviewAllActivity.class);
                intent.putExtra("postId",postId);
                startActivity(intent);
                break;
            case 2:
                intent=new Intent(MoveApplication.getContext(), DetailsDiscussActivity.class);
                intent.putExtra("postId",postId);
                startActivity(intent);
                break;
            case 3:
                intent=new Intent(MoveApplication.getContext(), DetailsArticleActivity.class);
                intent.putExtra("postId",postId);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
}
