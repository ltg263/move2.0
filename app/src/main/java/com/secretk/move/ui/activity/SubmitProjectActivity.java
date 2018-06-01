package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
 * 时间：  2018/5/31 17:59
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class SubmitProjectActivity extends BaseActivity {
    @BindView(R.id.myProgressBar)
    ProgressBar myProgressBar;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.et_input_01)
    EditText etInput01;
    @BindView(R.id.et_input_02)
    EditText etInput02;
    @BindView(R.id.et_input_03)
    EditText etInput03;
    @BindView(R.id.et_input_04)
    EditText etInput04;
    @BindView(R.id.et_input_05)
    EditText etInput05;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.tv_selected_yes)
    TextView tvSelectedYes;
    @BindView(R.id.tv_selected_no)
    TextView tvSelectedNo;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_submit_project;
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

    @OnClick({R.id.iv_icon, R.id.ll_selector_time, R.id.tv_selected_yes, R.id.tv_selected_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                break;
            case R.id.ll_selector_time:
                break;
            case R.id.tv_selected_yes:
                break;
            case R.id.tv_selected_no:
                break;
        }
    }
}
