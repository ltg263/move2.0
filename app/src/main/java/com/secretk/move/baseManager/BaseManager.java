package com.secretk.move.baseManager;

import android.app.Application;
import android.os.Handler;

import com.secretk.move.MoveApplication;


/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 */
public class BaseManager {

    public static MoveApplication app;
    public static Handler baseHandler;
    public static void init(Application context) {
        app = (MoveApplication) context;
        baseHandler = new Handler();
    }
}
