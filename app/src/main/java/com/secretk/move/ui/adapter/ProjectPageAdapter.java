package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/6.
 */

public class ProjectPageAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
    private  List<String>  head_list;
    public ProjectPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
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
