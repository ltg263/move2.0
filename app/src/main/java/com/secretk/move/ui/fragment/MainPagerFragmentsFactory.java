package com.secretk.move.ui.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;


/**
 * Created by zc on 2018/3/29.
 */

public class MainPagerFragmentsFactory {
    private static SparseArray<Fragment> fragments = new SparseArray<Fragment>();
    public static Fragment getFragment(int position) {
        Fragment currentFragment = fragments.get(position);
        if (currentFragment == null) {
            switch (position) {
                case 0:
                    fragments.put(position,new MainRecommendFragment());
                    break;
                case 1:
                    fragments.put(position,new MainFollowFragment());
                    break;
                case 2:
                    fragments.put(position,new MainBlueSkyFragment());
                    break;

                default:
                    break;
            }
            currentFragment=  fragments.get(position);
        }
        return currentFragment;
    }

}
