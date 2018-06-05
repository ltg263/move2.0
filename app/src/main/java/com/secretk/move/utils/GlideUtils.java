package com.secretk.move.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.customview.GlideCircleTransform;

/**
 *  加载图片工具类
 *  圆形使用 MoveApplication.getContext()
 *  方形 使用当前的Context()
 */
public class GlideUtils {
    public static void loadCircleUrl(ImageView img, String url) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.ic_head_silent).error(R.drawable.ic_head_silent)).
                load(url).into(img);
    }

    /**
     * 圆ImageView
     * 与用户有关ImageView
     */
    public static void loadCircleUserUrl(Context context,ImageView img, String url) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.ic_head_silent).error(R.drawable.ic_head_silent)).
                load(url).into(img);
    }
    /**
     * 圆ImageView
     * 与项目有关ImageView
     */
    public static void loadCircleProjectUrl(Context context,ImageView img, String url) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.ic_project_silent).error(R.drawable.ic_project_silent)).
                load(url).into(img);
    }

    /**
     *  方 ImageView
     *  加载矩形  大的默认
     */
    public static void loadSideMaxImage(Context context,ImageView img, String url) {
        LogUtil.w("方 ImageView:大url:"+url);
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_silent_plate_max).error(R.drawable.ic_silent_plate_max)).
                load(url).into(img);
    }
    /**
     *  方 ImageView
     *  加载矩形  小的默认
     */
    public static void loadSideMinImage(Context context,ImageView img, String url) {
        LogUtil.w("方 ImageView:小url:"+url);
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_silent_plate_mix).error(R.drawable.ic_silent_plate_mix)).
                load(url).into(img);
    }
}
