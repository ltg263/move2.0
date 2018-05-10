package com.secretk.move.utils;

import android.app.Activity;
import android.content.Intent;

import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.ui.activity.HomeActivity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import cn.sharesdk.onekeyshare.OnekeyShare;


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
     * 跳转到用户的Activity
     * @param userId
     */
    public static  void startHomeActivity(int userId){
        String key[]={"userId"};
        String values[]={String.valueOf(userId)};
        IntentUtil.startActivity(HomeActivity.class,key,values);
    }


    public static void  showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("标题");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(BaseManager.app);
    }
}
