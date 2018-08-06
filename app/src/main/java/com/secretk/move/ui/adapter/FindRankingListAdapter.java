package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.secretk.move.ui.fragment.FindRankingUserFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zc on 2018/4/6.
 */

public class FindRankingListAdapter extends FragmentStatePagerAdapter {
    private  List<String>  head_list;
    private ArrayList<Fragment> mFragments;
    public FindRankingListAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragments.add(new FindRankingUserFragment());
        mFragments.add(new FindRankingUserFragment());
        mFragments.add(new FindRankingUserFragment());
    }

    @Override
    public Fragment getItem(int position) {
        FindRankingUserFragment fragment = (FindRankingUserFragment) mFragments.get(position);
        fragment.setTabName(position);
        return fragment;
    }

    @Override
    public int getCount() {
        if (head_list==null)return  0;
        return head_list.size();
//        return Integer.MAX_VALUE;
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
