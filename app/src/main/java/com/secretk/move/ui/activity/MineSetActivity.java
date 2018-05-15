package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/5/5 13:27
 * 邮箱；ltg263@126.com
 * 描述：我的设置
 */
public class MineSetActivity extends BaseActivity {
    @BindView(R.id.head_app_server)
    AppBarHeadView headAppServer;
    @BindView(R.id.iv_head_img_a)
    ImageView ivHeadImg;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_area_name)
    TextView tvAreaName;
    @BindView(R.id.tv_user_signature)
    TextView tvUserSignature;
    @BindView(R.id.tv_user_degree)
    TextView tvUserDegree;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.ll_reset_passwords)
    LinearLayout llResetPasswords;
    @BindView(R.id.ll_my_head)
    LinearLayout llMyHead;
    String userName;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("设置");
        mMenuInfos.add(0,new MenuInfo(R.string.share, "保存", 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        //接收对象
        UserLoginInfo.DataBean.UserBean infos=getIntent().getParcelableExtra("userInfos");
        userName=infos.getUserName();
        tvUserName.setText(infos.getUserName());
        if(infos.getSex()==1){
            tvSex.setText("男");
        }else if(infos.getSex()==2){
            tvSex.setText("女");
        }
        tvAreaName.setText(infos.getAreaName());
        tvMobile.setText(infos.getMemo());
        tvUserDegree.setText(String.valueOf(infos.getUserDegree()));
        tvUserSignature.setText(infos.getUserSignature());
        if(StringUtil.isNotBlank(infos.getEmail())){
            tvEmail.setText(infos.getEmail());
        }
        if(StringUtil.isNotBlank(infos.getWechat())){
            tvWechat.setText(infos.getWechat());
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.tv_user_name, R.id.ll_sex, R.id.tv_area_name, R.id.tv_user_signature,
            R.id.tv_user_degree, R.id.tv_mobile, R.id.tv_wechat, R.id.tv_email, R.id.ll_reset_passwords, R.id.ll_my_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_head:
                openChangeHeadDialog();
                break;
            case R.id.tv_user_name:

                break;
            case R.id.ll_sex:
                openChangeSexDialog();
                break;
            case R.id.tv_area_name:
                break;
            case R.id.tv_user_signature:
                break;
            case R.id.tv_user_degree:
                break;
            case R.id.tv_mobile:
                break;
            case R.id.tv_wechat:
                break;
            case R.id.tv_email:
                break;
            case R.id.ll_reset_passwords:
                break;
        }
    }
    private void openChangeHeadDialog() {
        String[] srt = {"修改头像", "从手机相册中选择", "拍照上传"};
        DialogUtils.ShowAlertDialog(this, srt, new DialogUtils.AlertDialogInterface() {
            @Override
            public void btnLineListener(int index) {
                if(index==1){//从手机相册中选择
                    PicUtil.openPhoto(MineSetActivity.this);
                }else if(index ==2){//拍照上传
                     PicUtil.openCamera(MineSetActivity.this);
                }
            }
        });
    }
    //打开修改性别对话框
    private void openChangeSexDialog() {
        String[] srt = {"修改性别", "男", "女", tvSex.getText().toString()};
        DialogUtils.ShowAlertDialog(this, srt, new DialogUtils.AlertDialogInterface() {
            @Override
            public void btnLineListener(int index) {
                if(index==1){//从手机相册中选择
                    tvSex.setText("男");
                }else if(index ==2){//拍照上传
                    tvSex.setText("女");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PicUtil.CODE_CAMERA_PIC){
            Object o = PicUtil.filePath;
            if (o != null && o.toString().length()>0) {
                onPicResult(o.toString());
            }
        }else if(requestCode==PicUtil.CODE_SELECT_PIC){
            Object o = PicUtil.onActivityResult(this, requestCode, resultCode, data);
            if (o != null && o.toString().length()>0) {
                onPicResult(o.toString());
            }
        }else if(requestCode== PicUtil.CODE_CROP_PIC){
            onPicTrimResult();
        }
    }
    private void onPicResult(String picPath) {
        if(StringUtil.isNotBlank(picPath)){
            File tempFile = new File(picPath);
            PicUtil.startPhotoZoom(Uri.fromFile(tempFile),MineSetActivity.this);
        }
    }

    private void onPicTrimResult() {
       GlideUtils.loadImage(ivHeadImg,PicUtil.uritempFile.getPath());
    }
}
