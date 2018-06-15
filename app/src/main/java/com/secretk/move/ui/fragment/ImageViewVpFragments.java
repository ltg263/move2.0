package com.secretk.move.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;

/**
 * 引导页
 */
public class ImageViewVpFragments extends LazyFragment {
    public static ImageViewVpFragments getInstance(int k) {
        ImageViewVpFragments mf1 = new ImageViewVpFragments();
        Bundle bd = new Bundle();
        bd.putInt("k", k);
        mf1.setArguments(bd);
        return mf1;
    }

    @Override
    public int setFragmentView() {
        return R.layout.image_viewpager;
    }

    @Override
    public void initViews() {
        ImageView iv1 = convertView.findViewById(R.id.iv_viewpager);
        int k = getArguments().getInt("k");
        if (k == 0) {
//            iv1.setImageResource(R.drawable.yindao1);
//        } else if (k == 1) {
//            iv1.setImageResource(R.drawable.yindao2);
//        } else if (k == 2) {
//            iv1.setImageResource(R.drawable.yindao3);
        }
    }

    @Override
    public void onFirstUserVisible() {

    }
}
