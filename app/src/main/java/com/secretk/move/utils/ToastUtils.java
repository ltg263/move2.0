package com.secretk.move.utils;

import android.content.Context;
import android.widget.Toast;

import com.secretk.move.MoveApplication;



/**
 * Created by sunxx on 2016/8/3.
 */
public class ToastUtils {
    private static ToastUtils toastUtils;
    private ToastUtils() {
    }
    public static ToastUtils getInstance() {
        if (toastUtils==null){
            toastUtils=new ToastUtils();
        }
        return toastUtils;
    }

    public void show(String str) {
        Toast.makeText(MoveApplication.getContext(), str, Toast.LENGTH_LONG).show();
    }
    public static void show(Context context, String info) {
        Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
    }
}
