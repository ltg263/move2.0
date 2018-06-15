package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/10 09:05
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class TemporaryIV extends BaseActivity {
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.iv_img)
    ImageView ivImg;

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
        String imgName = getIntent().getStringExtra("imgName");
        ImageView iv = findViewById(R.id.iv);
        if (imgName.equals("邀请海报")) {
            GlideUtils.loadSideMaxImage(this, iv, getIntent().getStringExtra("imgUrl"));
        } else {
            rl.setVisibility(View.GONE);
            ivImg.setVisibility(View.VISIBLE);
            Glide.with(this).load(Constants.BASE_IMG_URL + getIntent().getStringExtra("imgUrl")).into(ivImg);
//            GlideUtils.loadSideMaxImage(this, ivImg, Constants.BASE_IMG_URL + getIntent().getStringExtra("imgUrl"));
        }
    }

    @Override
    protected void initData() {

    }
}
