package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.secretk.move.ui.fragment.MainFragmentsFactory;

/**
 * Created by zc on 2018/4/6.
 */

public class MainActivityPagerAdapter extends FragmentStatePagerAdapter {
    public MainActivityPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       return MainFragmentsFactory.getFragment(position);

    }

    @Override
    public int getCount() {
        return 5;
    }
}
