package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.UserLoginInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;
import com.secretk.move.view.PicPopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

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

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("设置");
        mMenuInfos.add(0,new MenuInfo(R.string.save, getString(R.string.save), R.drawable.ic_share));
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
        GlideUtils.loadCircleUrl(ivHeadImg,Constants.BASE_IMG_URL+infos.getIcon());
        tvUserName.setText(infos.getUserName());
        if(infos.getSex()==1){
            tvSex.setText("男");
        }else if(infos.getSex()==2){
            tvSex.setText("女");
        }
        tvAreaName.setText(infos.getAreaName());
        tvMobile.setText(infos.getMobile());
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


    @OnClick({R.id.ll_user_name, R.id.ll_sex, R.id.ll_area_name, R.id.ll_user_signature,
            R.id.tv_user_degree, R.id.tv_mobile, R.id.tv_wechat, R.id.tv_email, R.id.ll_reset_passwords, R.id.ll_my_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_head:
                openChangeHeadDialog();
                break;
            case R.id.ll_user_name:
                DialogUtils.showEditTextDialog(this, getString(R.string.set_my_name),
                        tvUserName.getText().toString().trim(),new DialogUtils.EditTextDialogInterface() {
                    @Override
                    public void btnConfirm(String season) {
                        tvUserName.setText(season);
                    }
                });
                break;
            case R.id.ll_sex:
                openChangeSexDialog();
                break;
            case R.id.ll_area_name:
                setAreaName();
                break;
            case R.id.ll_user_signature:
                DialogUtils.showEditTextDialog(this, getString(R.string.set_my_signature),
                        tvUserSignature.getText().toString().trim(),new DialogUtils.EditTextDialogInterface() {
                            @Override
                            public void btnConfirm(String season) {
                                tvUserSignature.setText(season);
                            }
                        });
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
                toolbarTopRightBtn1();
                break;
        }
    }

    private void setAreaName() {
       new PicPopupWindow(this,tvAreaName, new PicPopupWindow.OnItemClick() {
            @Override
            public void onclick(String result) {
                tvAreaName.setText(result);
            }
        });

    }
    public String getMimeType(String fileName) {
        String result = "";
        int extPos = fileName.lastIndexOf(".");
        if(extPos != -1) {
            String ext = fileName.substring(extPos + 1);
            result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        if(TextUtils.isEmpty(result)){
            result = "application/octet-stream";
        }

        return result;
    }
    @Override
    protected void toolbarTopRightBtn() {
        File file = new File(PicUtil.uritempFile.getPath());
        LogUtil.w("file.exists(:"+file.exists());
        if(!file.exists()){
            return;
        }
        imgBase64 = ImageUtils.getImageBase64(PicUtil.uritempFile.getPath());
//        JSONObject node = new JSONObject();
//        try {
//            node.put("token", token);
//            node.put("imgdata", imgBase64);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        LogUtil.w("file:"+file);
        String[] split = file.getPath().split("\\.");
        String suffix = split[split.length - 1];
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPLOAD_USER_ICON_FILE)
                .addPart("token", token)
                .addPart("uploadfile",getMimeType(file.getName()) ,file)
//                .addPart("uploadfile",suffix)
                .build();
//        File file = new File(path);
//        String[] split = path.split("\\.");
//        String suffix = split[split.length - 1];
//        String mimeType = getMimeType(file.getName());
//        params.addPart("file", mimeType,file);
//        params.addPart("suffix",suffix);
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
            }
        });
    }
    protected void toolbarTopRightBtn1() {
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        String sex = tvSex.getText().toString().trim();
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("userName", tvUserName.getText().toString().trim());
            node.put("sex", sex=="男"?1:2);
            node.put("userSignature", tvUserSignature.getText().toString().trim());
            node.put("areaName", tvAreaName.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadingDialog.show();
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPDATE_USER_INFO)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str);
                    JSONObject userInfo = object.getJSONObject("data").getJSONObject("user");
                    sharedUtils.put("userInfo",userInfo.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.REQUEST_CODE,true);
                setResult(RESULT_OK,intent);
                finish();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });

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
    String imgBase64;
    private void onPicTrimResult() {
       GlideUtils.loadImage(ivHeadImg,PicUtil.uritempFile.getPath());
    }

    //=========================================================================


}
