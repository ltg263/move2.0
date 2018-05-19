package com.secretk.move.ui.activity;

import android.os.Bundle;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.EvaluationSliderView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/17 19:27
 * 邮箱；ltg263@126.com
 * 描述：简单测评
 */
public class EvaluationProfessionalItemActivity extends BaseActivity {

    @BindView(R.id.es_viewa)
    EvaluationSliderView esViewa;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle(getString(R.string.evaluation_professional));
        mMenuInfos.add(0,new MenuInfo(R.string.evaluation_write, getString(R.string.evaluation_write), 0));
        return mHeadView;
    }
    @Override
    protected int setOnCreate() {
        return R.layout.activity_evaluation_professional_item;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        esViewa.setScore(4.5f);
        esViewa.setEsvBackground(R.color.app_background);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startActivity(EvaluationProfessionalActivity.class);
    }
}
