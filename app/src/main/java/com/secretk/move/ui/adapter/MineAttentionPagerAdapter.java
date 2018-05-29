package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.secretk.move.ui.fragment.MineAttentionFragment;
import com.secretk.move.ui.fragment.MineAttentionUserFragment;

/**
 * Created by zc on 2018/4/6.
 */

public class MineAttentionPagerAdapter extends FragmentStatePagerAdapter {
    private  String [] head_list;
    public MineAttentionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public int getCount() {
        if (head_list==null)return  0;
        return head_list.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return head_list[position];
    }

    public  void setData(String [] head_list){
        this.head_list=head_list;
        notifyDataSetChanged();

    }
    private SparseArray<Fragment> fragments = new SparseArray<>();
    private Fragment getFragment(int position) {
        Fragment currentFragment = fragments.get(position);
        if (currentFragment == null) {
            switch (position) {
                case 0:
                    fragments.put(position,new MineAttentionFragment());
                    break;
                case 1:
                    fragments.put(position,new MineAttentionUserFragment());
                    break;
            }
            currentFragment=  fragments.get(position);
        }
        return currentFragment;
    }
}
