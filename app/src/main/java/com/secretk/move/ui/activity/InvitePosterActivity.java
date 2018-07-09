package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.ShareView;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/10 09:05
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class InvitePosterActivity extends BaseActivity {
    @BindView(R.id.tv_bnt)
    TextView tvBnt;
    private String imgUrl;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getIntent().getStringExtra("imgName"));
        mMenuInfos.add(0, new MenuInfo(R.string.share, getResources().getString(R.string.share), R.drawable.ic_share));
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.temporary_iv;
    }

    @Override
    protected void OnToolbarRightListener() {
        LogUtil.w("-------------------------------------");
        ShareView.showShare1(null,imgUrl);
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        String imgName = getIntent().getStringExtra("imgName");
        imgUrl = getIntent().getStringExtra("imgUrl");
        ImageView iv = findViewById(R.id.iv);
        if (imgName.equals("邀请海报")) {
            GlideUtils.loadUrl(this, iv, imgUrl);
        }
        tvBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveBitmap();
            }
        });
    }

    private String path;
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            ImageUtils.galleryAddPic(InvitePosterActivity.this,path);
            loadingDialog.dismiss();
            ToastUtils.getInstance().show("保存成功");
        }
    };

    private void saveBitmap() {
        String pathDj = Environment.getExternalStorageDirectory()+ File.separator +"区分";
        path =pathDj+ File.separator + "find_" + System.currentTimeMillis() + ".png";
        if (StringUtil.isNotBlank(imgUrl)) {
            if (!new File(pathDj).exists()) {
                new File(pathDj).mkdirs();
            }
            loadingDialog.show();
            ImageUtils.downloadPicture(imgUrl, handler, path);
        }
    }

    @Override
    protected void initData() {

    }
}
