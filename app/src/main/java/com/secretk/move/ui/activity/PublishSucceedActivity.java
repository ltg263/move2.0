package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/6/4 15:36
 * 邮箱；ltg263@126.com
 * 描述：发布测评 文章 讨论成功后所用
 */
public class PublishSucceedActivity extends BaseActivity {

    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    private String publishType;
    private String postId;
    @Override
    protected int setOnCreate() {
        return R.layout.activity_publish_succeed;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        MoveApplication.getContext().addActivity(this);
        publishType = getIntent().getStringExtra(Constants.PublishSucceed.PUBLISH_TYPE);
        postId = getIntent().getStringExtra(Constants.PublishSucceed.PUBLISH_POST_ID);
        rlTopTheme.setVisibility(View.VISIBLE);
        tvName.setText(getIntent().getStringExtra(Constants.PublishSucceed.SUBMIT_TEXT));
        mHeadView.setTitle(getIntent().getStringExtra(Constants.PublishSucceed.SUBMIT_TITLE));
        tvSubmit.setText(getIntent().getStringExtra(Constants.PublishSucceed.PUBLISH_BTN_TEXT));
    }

    @Override
    protected void initData() {
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }


    @OnClick(R.id.tv_submit)
    public void onViewClicked() {
        switch (publishType){
            case Constants.PublishSucceed.EVALUATION://评测
                IntentUtil.go2DetailsByType(1,postId);
                MoveApplication.getContext().finishAllActivity();
                break;
            case Constants.PublishSucceed.DISCUSS://评测
                IntentUtil.go2DetailsByType(2,postId);
                MoveApplication.getContext().finishAllActivity();
                break;
            case Constants.PublishSucceed.ARTICLE://评测
                IntentUtil.go2DetailsByType(3,postId);
                MoveApplication.getContext().finishAllActivity();
                break;
            case Constants.PublishSucceed.PUBLISH_PROJECT://评测
                IntentUtil.startActivity(MainActivity.class);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(publishType.equals(Constants.PublishSucceed.PUBLISH_PROJECT)){
            IntentUtil.startActivity(MainActivity.class);
            return;
        }
        MoveApplication.getContext().finishAllActivity();
        super.onBackPressed();
    }
}
