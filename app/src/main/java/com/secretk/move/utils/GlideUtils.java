package com.secretk.move.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.customview.GlideCircleTransform;
import com.secretk.move.customview.GlideRoundTransform;

/**
 *  加载图片工具类
 *  圆形使用 MoveApplication.getContext()
 *  方形 使用当前的Context()
 *  http://pic.qufen.top/4481528959156_.pic.jpg
 http://pic.qufen.top/4481528959156_.pic.jpg?imageView2/1/w/187
 http://pic.qufen.top/4481528959156_.pic.jpg?imageView2/1/w/342/h/187
 */
public class GlideUtils {
    static String ImgUrlMix = "?imageView2/1/w/374";
    static String ImgUrlMax = "?imageView2/1/w/684/h/374";

    static String ImgUrlMix_76 = "?imageView2/1/h/76";
    static String ImgUrlMax_135 = "?imageView2/1/w/316/h/135";
    /**
     * 加载本地图片
     */
    public static void loadUrl(Context context,ImageView img, String url) {
        Glide.with(context).load(url).into(img);
    }
    /**
     * 加载本地图片
     */
    public static void loadUrlDd(Context context,ImageView img, int url) {
        Glide.with(context).load(url).into(img);
    }

    /**
     * 圆ImageView
     * 与用户有关ImageView
     */
    public static void loadCircleUserUrl(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
                url=url+ImgUrlMix;
        }
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.ic_head_silent).error(R.drawable.ic_head_silent)).
                load(url).into(img);
    }
    /**
     * 圆ImageView
     * 与项目有关ImageView
     */
    public static void loadCircleProjectUrl(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
            url=url+ImgUrlMix;
        }
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.ic_project_silent).error(R.drawable.ic_project_silent)).
                load(url).into(img);
    }

    /**
     *  方 ImageView
     *  加载矩形  大的默认
     */
    public static void loadSideMaxImage(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
            url=url+ImgUrlMax;
        }
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_silent_plate_max).error(R.drawable.ic_silent_plate_max)).
                load(url).into(img);
    }
    /**
     *  方 ImageView
     *  加载矩形  小的默认
     */
    public static void loadSideMinImage(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
            url=url+ImgUrlMix;
        }
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_silent_plate_mix).error(R.drawable.ic_silent_plate_mix)).
                load(url).into(img);
    }
    /**
     *  方 ImageView
     *  加载矩形  小的默认
     */
    public static void loadSideMinImage_76(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
            url=url+ImgUrlMix_76;
        }
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideRoundTransform()).placeholder(R.drawable.ic_silent_plate_mix).error(R.drawable.ic_silent_plate_mix)).
                load(url).into(img);
    }
    /**
     *  方 ImageView
     *  加载矩形  小的默认
     */
    public static void loadSideMinImage_JYS_76(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
            url=url+ImgUrlMix_76;
        }
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideRoundTransform()).placeholder(R.drawable.ic_silent_jys_mix).error(R.drawable.ic_silent_jys_mix)).
                load(url).into(img);
    }

    /**
     *  方 ImageView
     *  加载矩形  大的默认
     */
    public static void loadSideMaxImage_135(Context context,ImageView img, String url) {
        if(url.contains("https:") || url.contains("http:") ){
            url=url+ImgUrlMax_135;
        }
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().transform(new GlideRoundTransform()).placeholder(R.drawable.ic_silent_plate_max).error(R.drawable.ic_silent_plate_max)).
                load(url).into(img);
    }
}
