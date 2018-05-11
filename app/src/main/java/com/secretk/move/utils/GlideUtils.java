package com.secretk.move.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.customview.GlideCircleTransform;

/**
 * Created by zc on 2018/4/5.
 */

public class GlideUtils {
    public static void loadCircle(ImageView img, int where) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.account_portrait).error(R.drawable.account_portrait)).
                load(where).into(img);
    }
    public static void loadCircleUrl(ImageView img, String url) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().transform(new GlideCircleTransform()).placeholder(R.drawable.account_portrait).error(R.drawable.account_portrait)).
                load(url).into(img);
    }
    public static void loadImage(Context context,ImageView img, String url) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.account_portrait).error(R.drawable.account_portrait)).
                load(url).into(img);
    }
    public static void loadImage(ImageView img, String url) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.account_portrait).error(R.drawable.account_portrait)).
                load(url).into(img);
    }
    public static void loadImage(ImageView img, int url) {
        Glide.with(MoveApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.account_portrait).error(R.drawable.account_portrait)).
                load(url).into(img);
    }
}
