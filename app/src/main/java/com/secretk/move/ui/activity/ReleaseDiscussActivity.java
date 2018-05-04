package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.secretk.move.R;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/*发表讨论*/
public class ReleaseDiscussActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_discuss);
        ButterKnife.bind(this);

        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this,  UiUtils.getColor(R.color.main_background), 0);
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_release)
    public void tv_release() {

    }
}
