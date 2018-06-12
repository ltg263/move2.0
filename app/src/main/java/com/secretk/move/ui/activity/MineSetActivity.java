package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.secretk.move.bean.VersionBean;
import com.secretk.move.utils.DownloadService;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
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
    @BindView(R.id.tv_user_find)
    TextView tvUserFind;
    @BindView(R.id.tv_wechat)
    TextView tvWechat;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.ll_reset_passwords)
    LinearLayout llResetPasswords;
    @BindView(R.id.ll_my_head)
    LinearLayout llMyHead;
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    private int userCardStatus;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("设置");
        mMenuInfos.add(0, new MenuInfo(R.string.save, getString(R.string.save), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_set;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        //接收对象
        UserLoginInfo.DataBean.UserBean infos = getIntent().getParcelableExtra(Constants.USER_INFOS);
        GlideUtils.loadCircleUserUrl(this, ivHeadImg, Constants.BASE_IMG_URL + StringUtil.getBeanString(infos.getIcon()));
        tvUserName.setText(StringUtil.getBeanString(infos.getUserName()));
        if (infos.getSex() == 1) {
            tvSex.setText("男");
        } else if (infos.getSex() == 2) {
            tvSex.setText("女");
        }
        //userCardStatus
        tvCurrentVersion.setText("V "+StringUtil.getVersionCode());
        tvAreaName.setText(StringUtil.getBeanString(infos.getAreaName()));
        tvMobile.setText(StringUtil.getBeanString(infos.getMobile()));
        //  "userType":1, //用户类型，数字，用户类型:1-普通用户；2-项目方；3-评测机构；4-机构用户
        userCardStatus = infos.getUserType();
        tvUserDegree.setText(StringUtil.getUserType(infos.getUserType()));
        // 1  待审核  2   审核通过  3   未通过审核  4   未提交   身份验证  和账号验证的审核状态
        tvUserFind.setText("");
        tvUserSignature.setText(StringUtil.getBeanString(infos.getUserSignature()));
        if (StringUtil.isNotBlank(infos.getEmail())) {
            tvEmail.setText(infos.getEmail());
        }
        if (StringUtil.isNotBlank(infos.getWechat())) {
            tvWechat.setText(infos.getWechat());
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.ll_user_name, R.id.ll_sex, R.id.ll_area_name, R.id.ll_user_signature,R.id.tv_current_version,
            R.id.tv_user_degree, R.id.tv_mobile, R.id.tv_wechat, R.id.tv_email,
            R.id.ll_reset_passwords, R.id.ll_my_head,R.id.tv_esc_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_head:
                openChangeHeadDialog();
                break;
            case R.id.ll_user_name:
                if(userCardStatus!=1){
                    DialogUtils.showDialogHint(this, "已认证用户无法修改",true, new DialogUtils.ErrorDialogInterface() {
                        @Override
                        public void btnConfirm() {

                        }
                    });
                    return;
                }
                DialogUtils.showEditTextDialog(this, getString(R.string.set_my_name),
                        tvUserName.getText().toString().trim(), new DialogUtils.EditTextDialogInterface() {
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
                        tvUserSignature.getText().toString().trim(), new DialogUtils.EditTextDialogInterface() {
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
                break;
            case R.id.tv_esc_login:
                DialogUtils.showDialogHint(this, "您确定要退出吗？",false, new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        sharedUtils.clear();
                        IntentUtil.startActivity(LoginHomeActivity.class);
                        finish();
                    }
                });
                break;
            case R.id.tv_current_version:
                updataVersion();
                break;
        }
    }

    private void updataVersion() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.singleton().get("token",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPGRADE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, VersionBean.class, new HttpCallBackImpl<VersionBean>() {
            @Override
            public void onCompleted(VersionBean str) {
//                callBack.requestSuccess(str.getData());
                boolean force;
                VersionBean.DataBean bean = str.getData();
                if(bean.getForce()==1){//0普通更新，1强制更新
                    dialogUpdata(bean,true);
                }else if(bean.getUpgrade()==1){//0不需更新，1需要更新
                    dialogUpdata(bean,false);
                }else{
                    LogUtil.w("没有最新版本");
                }
            }
        });
    }

    private void dialogUpdata(final VersionBean.DataBean str, final boolean force) {
        DialogUtils.showDialogAppUpdate(this, force, str.getUpExplain(), new DialogUtils.ErrorDialogInterface() {
            @Override
            public void btnConfirm() {
                Intent service = new Intent(MineSetActivity.this, DownloadService.class);
                if(force){
                    service.putExtra("Url",str.getGuideUrl());
                }else{
                    service.putExtra("Url",str.getUpgradeUrl());
                }
                startService(service);
            }
        });
    }

    private void setAreaName() {
        new PicPopupWindow(this, tvAreaName, new PicPopupWindow.OnItemClick() {
            @Override
            public void onclick(String result) {
                tvAreaName.setText(result);
            }
        });

    }

    public String getMimeType(String fileName) {
        String result = "";
        int extPos = fileName.lastIndexOf(".");
        if (extPos != -1) {
            String ext = fileName.substring(extPos + 1);
            result = MimeTypeMap.getSingleton().getMimeTypeFromExtension(ext);
        }
        if (TextUtils.isEmpty(result)) {
            result = "application/octet-stream";
        }

        return result;
    }

    @Override
    protected void OnToolbarRightListener() {
        if (PicUtil.uritempFile == null) {
            saveData();
            return;
        }
        File file = new File(PicUtil.uritempFile.getPath());
        LogUtil.w("file.exists(:" + file.exists());
        if (!file.exists()) {
            saveData();
            return;
        }
        String[] split = file.getPath().split("\\.");
        String suffix = split[split.length - 1];
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPLOAD_USER_ICON_FILE)
                .addPart("token", token)
                .addPart("uploadfile", getMimeType(file.getName()), file)
                .addPart("imgtype", Constants.UPLOADIMG_TYPE.USER_ICON)
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                saveData();
            }

            @Override
            public void onError(String message) {
                if (loadingDialog.isShowing()) {
                    loadingDialog.dismiss();
                }
            }
        });
    }

    protected void saveData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        String sex = tvSex.getText().toString().trim();
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("userName", tvUserName.getText().toString().trim());
            node.put("sex", sex == "男" ? 1 : 2);
            node.put("userSignature", tvUserSignature.getText().toString().trim());
            node.put("areaName", tvAreaName.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                    sharedUtils.put(Constants.USER_INFOS, userInfo.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtra(Constants.REQUEST_CODE, true);
                setResult(RESULT_OK, intent);
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
                if (index == 1) {//从手机相册中选择
                    PicUtil.openPhoto(MineSetActivity.this);
                } else if (index == 2) {//拍照上传
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
                if (index == 1) {//从手机相册中选择
                    tvSex.setText("男");
                } else if (index == 2) {//拍照上传
                    tvSex.setText("女");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PicUtil.CODE_CAMERA_PIC) {
            Object o = PicUtil.filePath;
            if (o != null && o.toString().length() > 0) {
                onPicResult(o.toString());
            }
        } else if (requestCode == PicUtil.CODE_SELECT_PIC) {
            Object o = PicUtil.onActivityResult(this, requestCode, resultCode, data);
            if (o != null && o.toString().length() > 0) {
                onPicResult(o.toString());
            }
        } else if (requestCode == PicUtil.CODE_CROP_PIC) {
            onPicTrimResult();
        }
    }

    private void onPicResult(String picPath) {
        if (StringUtil.isNotBlank(picPath)) {
            File tempFile = new File(picPath);
            PicUtil.startPhotoZoom(Uri.fromFile(tempFile), MineSetActivity.this, true);
        }
    }

    private void onPicTrimResult() {
        GlideUtils.loadCircleUserUrl(this, ivHeadImg, PicUtil.uritempFile.getPath());
    }
}
