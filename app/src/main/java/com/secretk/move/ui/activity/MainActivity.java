package com.secretk.move.ui.activity;


import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.secretk.move.R;
import com.secretk.move.base.MvpBaseActivity;
import com.secretk.move.contract.ActivityMainContract;
import com.secretk.move.customview.TabViewpager;
import com.secretk.move.presenter.impl.MainPresenterImpl;
import com.secretk.move.ui.adapter.MainActivityPagerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;

import butterknife.BindView;


public class MainActivity extends MvpBaseActivity<MainPresenterImpl> implements ActivityMainContract.View {
    @BindView(R.id.vp_main)
    TabViewpager vp_main;
    @BindView(R.id.rg_main_parent)
    RadioGroup rg_main_parent;
    MainActivityPagerAdapter adapter;
    ActivityMainContract.Presenter presenter;
    @Override
    protected int setLayout() {
         return R.layout.activity_main;
    }
    @Override
    protected MainPresenterImpl initPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this,  UiUtils.getColor(R.color.main_background), 0);
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        vp_main.setAdapter(adapter);
        vp_main.setOffscreenPageLimit(4);
        presenter = new MainPresenterImpl(this);
        presenter.initialized();
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==3){
                    StatusBarUtil.setDarkMode(MainActivity.this);
                    StatusBarUtil.setColor(MainActivity.this,  UiUtils.getColor(R.color.app_background), 0);
                }else {
                    StatusBarUtil.setLightMode(MainActivity.this);
                    StatusBarUtil.setColor(MainActivity.this,  UiUtils.getColor(R.color.main_background), 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rg_main_parent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main:
                        vp_main.setCurrentItem(0);
                        break;
                    case R.id.rb_topic:
                        vp_main.setCurrentItem(1);
                        break;
                    case R.id.rb_message:
                        vp_main.setCurrentItem(2);
                        break;
                    case R.id.rb_mine:
                        vp_main.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }



    @Override
    public void showDialog(String str,boolean force) {
//        DialogUtils.showDialogAppUpdate(this, force, str, new DialogUtils.ErrorDialogInterface() {
//            @Override
//            public void btnConfirm() {
//                presenter.downLoadApk();
//            }
//        });
    }


}
