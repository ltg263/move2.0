package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/29 18:29
 * 邮箱；ltg263@126.com
 * 描述：锁定
 */
public class MineAssetCoinActivity extends BaseActivity {
    @BindView(R.id.tv_total_assets)
    TextView tvTotalAssets;
    @BindView(R.id.et_binding_num)
    EditText etBindingNum;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_coin;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.lock));
        return mHeadView;
    }

    @OnClick(R.id.but_next)
    public void onViewClicked() {
    }
}
