package com.secretk.move.ui.activity;


import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import com.secretk.move.R;
import com.secretk.move.base.MvpBaseActivity;
import com.secretk.move.contract.ActivityMainContract;
import com.secretk.move.customview.TabViewpager;
import com.secretk.move.presenter.impl.MainPresenterImpl;
import com.secretk.move.ui.adapter.MainActivityPagerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;


import butterknife.BindView;


public class MainActivity extends MvpBaseActivity<MainPresenterImpl> implements ActivityMainContract.ActivityMainView {
    @BindView(R.id.vp_main)
    TabViewpager vp_main;
    MainActivityPagerAdapter adapter;
    ActivityMainContract.MainPresenter presenter;
    @Override
    protected int setLayout() {
         return R.layout.activity_main;
    }
    @Override
    protected MainPresenterImpl initPresenter() {
        return new MainPresenterImpl(this);
    }
    public void go2main(View view) {
        vp_main.setCurrentItem(0);
    }
    @Override
    protected void initView() {
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


}
