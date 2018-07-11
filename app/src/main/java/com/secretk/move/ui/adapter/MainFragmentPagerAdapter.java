package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.secretk.move.ui.fragment.MainBlueBlFragment;
import com.secretk.move.ui.fragment.MainBlueFxFragment;
import com.secretk.move.ui.fragment.MainBlueGzFragment;
import com.secretk.move.ui.fragment.MainBlueSkyFragment;

/**
 * Created by zc on 2018/4/6.
 */

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private    String [] head_list;
    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new MainBlueFxFragment();
        }
        if(position==1){
            return new MainBlueGzFragment();
        }
        if (position==2){
            return new MainBlueBlFragment();
        }
        return new MainBlueSkyFragment();

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
}
