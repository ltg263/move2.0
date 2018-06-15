package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.fragment.ImageViewVpFragments;
import com.secretk.move.view.AppBarHeadView;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：com.juanct.iwork99.activity
 * 描    述：引导页
 * 作    者：yujian
 * 时    间：2016/8/18 11:18
 */
public class ImageViewVpAcivity extends BaseActivity {


    private ViewPager vp1;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private RadioGroup rg1;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_guide;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenuInfos) {
        return null;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        initFrament();
        vp1 =  findViewById(R.id.vp_1);
        rg1 = findViewById(R.id.rg_1);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        vp1.setAdapter(adapter);
        rg1.setOnCheckedChangeListener(myOnCheckedChangeListener);//为RadioGroup添加选择监听
        vp1.setOnPageChangeListener(myOnPageChangeListener);//为viewPager添加滑动监听
    }
//"[{"fileUrl":"/upload/posts/201805/1.jpg","fileName":"进度讨论","extension":"jpg"},
// {"fileUrl":"/upload/posts/201805/2.jpg","fileName":"进度讨论","extension":"jpg"},
// {"fileUrl":"/upload/posts/201805/3.jpg","fileName":"进度讨论","extension":"jpg"}]"
    //初始化Frament
    private void initFrament() {
        ImageViewVpFragments mf1 = ImageViewVpFragments.getInstance(0);
        ImageViewVpFragments mf2 = ImageViewVpFragments.getInstance(1);
        ImageViewVpFragments mf3 = ImageViewVpFragments.getInstance(2);
        fragments.add(mf1);
        fragments.add(mf2);
        fragments.add(mf3);
    }

    /**
     * RadioGroup添加选择监听
     */
    RadioGroup.OnCheckedChangeListener myOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == R.id.rb_1) {
                vp1.setCurrentItem(0);
            } else if (checkedId == R.id.rb_2) {
                vp1.setCurrentItem(1);
            } else if (checkedId == R.id.rb_3) {
                vp1.setCurrentItem(3);
            }
        }
    };
    /**
     * viewPager添加滑动监听
     */
    ViewPager.OnPageChangeListener myOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            RadioButton rb1 = (RadioButton) rg1.getChildAt(position);
            rb1.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void initData() {

    }

    /**
     * viewPager适配器
     */
    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return fragments.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return fragments.size();
        }

    }
}
