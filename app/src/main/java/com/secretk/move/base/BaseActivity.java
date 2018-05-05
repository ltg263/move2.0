package com.secretk.move.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.activity.HomeActivity;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 作者： litongge
 * 时间： 2018/4/27 13:40
 * 邮箱；ltg263@126.com
 * 描述：基类的activity
 */

public abstract class BaseActivity extends AppCompatActivity {
    public AppBarHeadView mHeadView;
    protected List<MenuInfo> mMenuInfos = new ArrayList<>();
    protected SharedUtils sharedUtils;
    protected Boolean isLoginUi = false;
    protected Boolean isLoginZt = false;//登录状态
    protected String token ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setOnCreate());
        ButterKnife.bind(this);
        sharedUtils = SharedUtils.singleton();
        isLoginZt = sharedUtils.get(Constants.IS_LOGIN_KEY,false);
        if(isLoginZt){
            token = sharedUtils.get(Constants.TOKEN_KEY,"");
        }else{
            token = "";
        }
        initUI(savedInstanceState);
        initData();

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initHead();
    }

    public void setVerticalManager(RecyclerView rcv) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcv.setLayoutManager(manager);
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
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        for (MenuInfo item : mMenuInfos) {
            menu.add(0, item.getId(), 0, item.getName()).setIcon(item.getIcon()).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
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
            if (isLoginUi) {
                mHeadView.getToolbar().setNavigationIcon(R.drawable.toobar_back_white);
            } else {
                StatusBarUtil.setLightMode(this);
                StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);
                mHeadView.getToolbar().setNavigationIcon(R.drawable.toobar_back_blue);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }else if(item.getItemId() == R.string.share){
            IntentUtil.showShare();
            Toast.makeText(this, "您点击了分享", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
