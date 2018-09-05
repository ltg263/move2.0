package com.secretk.move.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;

import com.secretk.move.ui.fragment.SearchContentFragment;
import com.secretk.move.ui.fragment.SearchProjectFragment;
import com.secretk.move.ui.fragment.SearchTopicFragment;
import com.secretk.move.ui.fragment.SearchUserFragment;

import java.util.List;

/**
 * Created by zc on 2018/4/6.
 */

public class SearchAllContentVpAdapter extends FragmentStatePagerAdapter {
    private  List<String>  head_list;
    public SearchAllContentVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
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
    String searchTxt;
    public  void setData(List<String> head_list, String searchTxt){
        this.searchTxt = searchTxt;
        this.head_list=head_list;
        notifyDataSetChanged();
    }

    private SparseArray<Fragment> fragments = new SparseArray<>();
    private Fragment getFragment(int position) {
        Fragment currentFragment = fragments.get(position);
        if (currentFragment == null) {
            switch (position) {
                case 0:
                    fragments.put(position,new SearchProjectFragment());
                    break;
                case 1:
                    fragments.put(position,new SearchContentFragment());
                    break;
                case 2:
                    fragments.put(position,new SearchUserFragment());
                    break;
                case 3:
                    fragments.put(position,new SearchTopicFragment());
                    break;
            }
            currentFragment=  fragments.get(position);
        }
        return currentFragment;
    }
}
