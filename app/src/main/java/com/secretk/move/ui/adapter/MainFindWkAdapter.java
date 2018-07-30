package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.secretk.move.ui.fragment.FindKwFragment;

import java.util.List;

/**
 * Created by zc on 2018/4/6.
 */

public class MainFindWkAdapter extends FragmentStatePagerAdapter {
    private  List<String>  head_list;
    FindKwFragment mCurrentFragment;
    public MainFindWkAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        FindKwFragment fragment = new FindKwFragment();
        //0-进行中，1-已结束
        if(position==0){
            fragment.setState(0);
        }else{
            fragment.setState(1);
        }
        return fragment;
//        head_list.get(position).getTabId()

//        if(position==0){
//        }
//        if(position==1){
//            return new MainProjectOneFragment();
//        }
//        if (position==2){
//            return new MainProjectOneFragment();
//        }
//        return new MainBlueSkyFragment();

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

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = (FindKwFragment) object;
        super.setPrimaryItem(container, position, object);
    }

    public FindKwFragment getmCurrentFragment() {
        return mCurrentFragment;
    }
}
