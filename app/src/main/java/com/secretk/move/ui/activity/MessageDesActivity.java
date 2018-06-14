package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/6/12 19:44
 * 邮箱；ltg263@126.com
 * 描述：消息詳情
 */
public class MessageDesActivity extends BaseActivity {
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_message_des;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        tv.setText(getIntent().getStringExtra("message"));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("消息詳情");
        mHeadView.setHeadBackShow(true);
        return mHeadView;
    }
}
