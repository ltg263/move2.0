package com.secretk.move.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

/**
 * Created by zc on 2018/4/23.
 */

public class DemoActivity extends BaseActivity {
    Intent intent;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        return null;
    }

    public void main(View view){
        intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void http(View view){
        intent=new Intent(this,HttpActivity.class);
        startActivity(intent);
    }
    public void addlabel(View view){
        intent=new Intent(this,AddLabelActivity.class);
        startActivity(intent);
    }
}
