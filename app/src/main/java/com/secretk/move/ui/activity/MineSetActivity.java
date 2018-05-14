package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.soundcloud.android.crop.Crop;

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
    @BindView(R.id.iv_head_img)
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
        LogUtil.w("resultCode:"+resultCode);
        LogUtil.w("requestCode:"+requestCode);
        // 结果码不等于取消时候
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
//                case IMAGE_REQUEST_CODE:
//                    Uri tag = data.getData();
//                    int sdkV = Build.VERSION.SDK_INT;
//                    if (sdkV >= 19) {
//                        String path = UriUtils.getImageAbsolutePath(MyDataAcitivity.this, data.getData());
//                        tag = Uri.parse("file://" + path);
//                    }
//                    startPhotoZoom(tag);
//                    break;
                case PicUtil.CAMERA_REQUEST_CODE:
                    if (PicUtil.hasSdcard()) {
                        File tempFile = new File(Constants.LOCAL_PATH + File.separator + PicUtil.USER_NEME + ".jpg");
                        PicUtil.startPhotoZoom(this,Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                    }
                    break;
                case PicUtil.RESULT_REQUEST_CODE:
                    LogUtil.w("data:"+data);
                    if (data != null) {
                        PicUtil.getImageToView(data);
                    }
                    break;
                case Crop.REQUEST_PICK:
                    if (PicUtil.hasSdcard()) {
                        File tempFile = new File(Constants.LOCAL_PATH + File.separator + PicUtil.USER_NEME + ".jpg");
                        PicUtil.startPhotoZoom(this,Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(this, "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
                    }
                    break;
//
//                case REQUSET://对性别设置
//                    //requestCode标示请求的标示   resultCode表示有数据
//                    if (requestCode == MyDataAcitivity.REQUSET && resultCode == RESULT_OK) {
//                        String strHz = data.getStringExtra(MyGenderActivity.KEY_GENDER);
//                        tvDataGender.setText(strHz);
//                        submitData(getString(R.string.my_data_line04));
//                    }
//                    break;
//                case RESULT_NICK_NAME:
//                    if (resultCode ==RESULT_OK&&requestCode == MyDataAcitivity.RESULT_NICK_NAME) {
//                        String name = data.getStringExtra("newName");
//                        if (!TextUtils.isEmpty(name)) {
//                            tvDataNickname.setText(name);
//                        }
//                    }
//                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
