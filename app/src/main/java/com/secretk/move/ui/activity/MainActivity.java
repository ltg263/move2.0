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
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.customview.TabViewpager;
import com.secretk.move.presenter.MainPresenter;
import com.secretk.move.presenter.impl.MainPresenterImpl;
import com.secretk.move.ui.adapter.MainActivityPagerAdapter;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.ActivityMainView;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements ActivityMainView {
    @BindView(R.id.vp_main)
    TabViewpager vp_main;
    MainActivityPagerAdapter adapter;
    MainPresenter presenter;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        LogUtil.w("token:"+sharedUtils.get("token",""));
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

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
