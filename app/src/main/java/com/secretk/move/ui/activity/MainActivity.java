package com.secretk.move.ui.activity;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.customview.TabViewpager;
import com.secretk.move.presenter.MainPresenter;
import com.secretk.move.presenter.impl.MainPresenterImpl;
import com.secretk.move.ui.adapter.MainActivityPagerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.ActivityMainView;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements ActivityMainView {
    @BindView(R.id.vp_main)
    TabViewpager vp_main;
    MainActivityPagerAdapter adapter;
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this,  UiUtils.getColor(R.color.main_background), 0);
        adapter = new MainActivityPagerAdapter(getSupportFragmentManager());
        vp_main.setAdapter(adapter);
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
    }

    public void go2main(View view) {

        vp_main.setCurrentItem(0);
    }

    public void go2topic(View view) {
        vp_main.setCurrentItem(1);
    }

    public void go2message(View view) {
        vp_main.setCurrentItem(2);
    }

    public void go2mine(View view) {
        vp_main.setCurrentItem(3);
    }


    @Override
    public void showDialog(String str) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("检测到新版本,是否升级");
        if (!TextUtils.isEmpty(str)) {
            builder.setMessage(str);
        }
        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                presenter.downLoadApk();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#3F51B5"));
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#3F51B5"));
    }

    @Override
    public void showMessage(String error) {
        ToastUtils.getInstance().show(error);
    }

}
