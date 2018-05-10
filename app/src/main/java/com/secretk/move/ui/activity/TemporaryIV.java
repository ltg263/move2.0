package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

/**
 * 作者： litongge
 * 时间：  2018/5/10 09:05
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class TemporaryIV extends BaseActivity {
    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getIntent().getStringExtra("imgName"));
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.temporary_iv;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        ImageView iv = findViewById(R.id.iv);
        Glide.with(BaseManager.app).load(Constants.BASE_IMG_URL+getIntent().getStringExtra("imgUrl")).into(iv);
    }

    @Override
    protected void initData() {

    }

}
