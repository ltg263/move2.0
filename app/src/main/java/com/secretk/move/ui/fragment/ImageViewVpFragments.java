package com.secretk.move.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.utils.GlideUtils;

/**
 * 引导页
 */
public class ImageViewVpFragments extends LazyFragment {
    public static ImageViewVpFragments getInstance(String url) {
        ImageViewVpFragments mf1 = new ImageViewVpFragments();
        Bundle bd = new Bundle();
        bd.putString("url", url);
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
        String url = getArguments().getString("url");
        GlideUtils.loadUrl(getActivity(),iv1,url);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onFirstUserVisible() {

    }
}
