package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MineAssetPinlessAdapter;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.view.AppBarHeadView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/29 19:42
 * 邮箱；ltg263@126.com
 * 描述：解锁中
 */
public class MineAssetPinlessActivity extends BaseActivity {

    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    private MineAssetPinlessAdapter adapter;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.unlock_the));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_pinless;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MineAssetPinlessAdapter(this);
        rvReview.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add("hah啊："+i);
        }
        LogUtil.w("list:"+list.size());
        adapter.setData(list);
    }
}
