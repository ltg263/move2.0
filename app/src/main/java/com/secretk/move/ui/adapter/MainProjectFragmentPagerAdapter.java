package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.secretk.move.bean.ProjectTabBean;
import com.secretk.move.ui.fragment.MainProjectOneFragment;

import java.util.List;

/**
 * Created by zc on 2018/4/6.
 */

public class MainProjectFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private  List<ProjectTabBean.DataBean.TabsBean>  head_list;
    public MainProjectFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MainProjectOneFragment fragment = new MainProjectOneFragment();
        fragment.setTabId(head_list.get(position).getTabId());
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
        return head_list.get(position).getTabTitle();
    }

    public  void setData(List<ProjectTabBean.DataBean.TabsBean> head_list){
        this.head_list=head_list;
        notifyDataSetChanged();
    }
}
