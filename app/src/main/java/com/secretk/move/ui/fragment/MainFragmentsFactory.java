package com.secretk.move.ui.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;


/**
 * Created by zc on 2018/3/29.
 */

public class MainFragmentsFactory {
    private static SparseArray<Fragment> fragments = new SparseArray<>();
    public static Fragment getFragment(int position) {
        Fragment currentFragment = fragments.get(position);
        if (currentFragment == null) {
            switch (position) {
                case 0:
                    fragments.put(position,new MainPagerFragment());
                    break;
                case 1:
                    fragments.put(position,new ProjectFragment());
                    break;
                case 2:
                    fragments.put(position,new FindFragment());
                    break;
                case 3:
//                    fragments.put(position,new MessageFragment());
                    fragments.put(position,new InfoFragment());
                    break;
                case 4:
                    fragments.put(position,new MineFragment());
                    break;
                default:
                    break;
            }
            currentFragment=  fragments.get(position);
        }
        return currentFragment;
    }

}
