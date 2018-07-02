package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.PostDataInfo;
import com.secretk.move.ui.fragment.ImageViewVpFragments;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/7/2 16:32
 * 邮箱；ltg263@126.com
 * 描述：轮播图
 */
public class ImageViewVpAcivity extends BaseActivity {


    @BindView(R.id.vp_1)
    ViewPager vp1;
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rb_4)
    RadioButton rb4;
    @BindView(R.id.rb_5)
    RadioButton rb5;
    @BindView(R.id.rb_6)
    RadioButton rb6;
    @BindView(R.id.rb_7)
    RadioButton rb7;
    @BindView(R.id.rb_8)
    RadioButton rb8;
    @BindView(R.id.rb_9)
    RadioButton rb9;
    @BindView(R.id.rg_1)
    RadioGroup rg1;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private List<PostDataInfo> lists;
    private int position;

    @Override
    protected int setOnCreate() {
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_guide;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenuInfos) {
        return null;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        lists = getIntent().getParcelableArrayListExtra("lists");
        position = getIntent().getIntExtra("position", 0);

        initFrament();
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        vp1.setAdapter(adapter);

//        rg1.setOnCheckedChangeListener(myOnCheckedChangeListener);//为RadioGroup添加选择监听
        vp1.addOnPageChangeListener(myOnPageChangeListener);//为viewPager添加滑动监听
        vp1.setCurrentItem(position);
    }

    //初始化Frament
    private void initFrament() {
        if(lists.size()>1){
            rg1.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < lists.size(); i++) {
            String url = lists.get(i).getUrl();
            ImageViewVpFragments mf = ImageViewVpFragments.getInstance(StringUtil.getBeanString(url));
            fragments.add(mf);
            if(i==0){
                rb1.setVisibility(View.VISIBLE);
            }else if(i==1){
                rb2.setVisibility(View.VISIBLE);
            }else if(i==2){
                rb3.setVisibility(View.VISIBLE);
            }else if(i==3){
                rb4.setVisibility(View.VISIBLE);
            }else if(i==4){
                rb5.setVisibility(View.VISIBLE);
            }else if(i==5){
                rb6.setVisibility(View.VISIBLE);
            }else if(i==6){
                rb7.setVisibility(View.VISIBLE);
            }else if(i==7){
                rb8.setVisibility(View.VISIBLE);
            }else if(i==8){
                rb9.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * RadioGroup添加选择监听
     */
    RadioGroup.OnCheckedChangeListener myOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
//            if (checkedId == R.id.rb_1) {
//                vp1.setCurrentItem(0);
//            } else if (checkedId == R.id.rb_2) {
//                vp1.setCurrentItem(1);
//            } else if (checkedId == R.id.rb_3) {
//                vp1.setCurrentItem(3);
//            }
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
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}
