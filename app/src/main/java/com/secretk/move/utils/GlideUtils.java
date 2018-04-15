package com.secretk.move.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;

/**
 * Created by zc on 2018/4/5.
 */

public class GlideUtils {
    public static void load( ImageView img,String where){
        Glide.with(MoveApplication.getContext()).load(R.drawable.account_portrait).into(img);

    }

}
