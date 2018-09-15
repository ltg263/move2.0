package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.RewardSquareVpAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.MagicIndicatorUtils;
import com.secretk.move.view.ViewPagerFixed;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/9/4 11:47
 * 邮箱；ltg263@126.com
 * 描述：悬赏广场
 */
public class RewardSquareActivity extends BaseActivity {


    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.vp_main_children)
    ViewPagerFixed vpMainChildren;
    private RewardSquareVpAdapter adapter;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("悬赏广场");
        mHeadView.getImageView().setVisibility(View.VISIBLE);
        mMenuInfos.add(0, new MenuInfo(R.string.rule, getResources().getString(R.string.rule), R.drawable.ic_share));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startWebViewActivity(Constants.REGULATION,"悬赏规则");
    }

    @Override
    protected int setOnCreate() {
        return R.layout.actvity_reward_square;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        adapter = new RewardSquareVpAdapter(getSupportFragmentManager());
        vpMainChildren.setAdapter(adapter);
        List<String> list = new ArrayList<>();
        list.add("最新悬赏");
        list.add("高额悬赏");
        list.add("精彩回复");
        vpMainChildren.setOffscreenPageLimit(list.size());
        MagicIndicatorUtils.initMagicIndicatorTitle(this,list,vpMainChildren,magicIndicatorTitle);
        adapter.setData(list);
    }

    @Override
    protected void initData() {

    }
}
