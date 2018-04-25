package com.secretk.move.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.secretk.move.R;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.view.AppBarHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 *
 * Created by zc on 2018/4/5.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public AppBarHeadView mHeadView;
    protected List<MenuInfo> mMenuInfos = new ArrayList<>();
    protected SharedUtils sharedUtils;
    protected boolean isLoginUi = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setOnCreate());
        ButterKnife.bind(this);
        sharedUtils = SharedUtils.singleton();
        initUI(savedInstanceState);
        initData();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initHead();
    }


    /**
     * 重写onCreate
     */
    protected abstract int setOnCreate();
    /**
     * 初始化控件
     */
    protected abstract void initUI(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    protected abstract void initData();
    /**
     * 每个activity自动添加标题
     */
    public void initHead() {
        mHeadView = initHeadView(mMenuInfos);
        if (mHeadView != null) {
            mHeadView.setTitleSize(17);
            setSupportActionBar(mHeadView.getToolbar());
            setHeadBackShow(mHeadView.isHeadBackShow());
        }
    }
    protected abstract AppBarHeadView initHeadView(List<MenuInfo> mMenus);
    /**
     * 是否显示回退键
     *
     * @param isShow
     */
    public void setHeadBackShow(boolean isShow) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
        getSupportActionBar().setHomeButtonEnabled(isShow);
        if (mHeadView != null && isShow) {
            if(isLoginUi){
                mHeadView.getToolbar().setNavigationIcon(R.drawable.call_back);
            }else {
                mHeadView.getToolbar().setNavigationIcon(R.drawable.toobar_back_blue);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
