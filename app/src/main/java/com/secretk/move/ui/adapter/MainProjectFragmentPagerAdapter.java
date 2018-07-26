package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.secretk.move.ui.fragment.MainBlueSkyFragment;
import com.secretk.move.ui.fragment.MainProjectOneFragment;

import java.util.List;

/**
 * Created by zc on 2018/4/6.
 */

public class MainProjectFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private  List<String>  head_list;
    public MainProjectFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new MainProjectOneFragment();
        }
        if(position==1){
            return new MainProjectOneFragment();
        }
        if (position==2){
            return new MainProjectOneFragment();
        }
        return new MainBlueSkyFragment();

    }

    @Override
    public int getCount() {
        if (head_list==null)return  0;
        return head_list.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return head_list.get(position);
    }

    public  void setData(List<String> head_list){
        this.head_list=head_list;
        notifyDataSetChanged();

    }
}
