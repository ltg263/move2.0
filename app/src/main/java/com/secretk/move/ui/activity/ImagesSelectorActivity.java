package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/7/5 20:35
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ImagesSelectorActivity extends BaseActivity {
    @Override
    protected int setOnCreate() {
        return R.layout.activity_images_selector;
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

    @OnClick({R.id.bnt_1, R.id.bnt_2, R.id.bnt_3, R.id.bnt_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bnt_1:
                //单选
                break;
            case R.id.bnt_2:
                //限数量的多选(比喻最多9张)
                break;
            case R.id.bnt_3:
                //不限数量的多选
                break;
            case R.id.bnt_4:
                //单选并剪裁
                break;
        }
    }
}
