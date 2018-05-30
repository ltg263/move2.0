package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/30 09:54
 * 邮箱；ltg263@126.com
 * 描述：提取FInd
 */
public class MineAssetFindExtractActivity extends BaseActivity {

    @BindView(R.id.tv_wallet_site)
    TextView tvWalletSite;
    @BindView(R.id.et_binding_num)
    EditText etBindingNum;
    @BindView(R.id.tv_all_find)
    TextView tvAllFind;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.extract_find_title));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_find_extract;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_wallet_change, R.id.tv_all_extract, R.id.but_next, R.id.tv_extract_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wallet_change://给换錢包
                break;
            case R.id.tv_all_extract://全部提取
                break;
            case R.id.but_next://确认提取
                break;
            case R.id.tv_extract_record://提取记录
                IntentUtil.startActivity(MineAssetExtractRecordActivity.class);
                break;
        }
    }
}
