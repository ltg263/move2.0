package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/30 14:27
 * 邮箱；ltg263@126.com
 * 描述：提交照片
 */
public class MineApproveSubmitiPicActivity extends BaseActivity {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String number;
    private String name;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.mine_title));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_approve_submiti_pic;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        number = getIntent().getStringExtra("number");
        name = getIntent().getStringExtra("name");
        tvTitle.setText(Html.fromHtml("<u>"+getString(R.string.mine_submit_require_00)+"</u>"));
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtil.startWebViewActivity("https://pic.qufen.top/543245675432.jpeg","手写文案");
            }
        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.tv_top, R.id.tv_submit,R.id.iv_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top:
                MineApproveSubmitiPicActivity.this.finish();
                break;
            case R.id.iv_icon:
                String[] srt = {getString(R.string.mine_title), "从手机相册中选择", "拍照上传"};
                DialogUtils.ShowAlertDialog(this, srt, new DialogUtils.AlertDialogInterface() {
                    @Override
                    public void btnLineListener(int index) {
                        if(index==1){//从手机相册中选择
                            PicUtil.openPhoto(MineApproveSubmitiPicActivity.this);
                        }else if(index ==2){//拍照上传
                            PicUtil.openCamera(MineApproveSubmitiPicActivity.this);
                        }
                    }
                });
                break;
            case R.id.tv_submit:
                submit();
                break;
        }
    }

    private void submit() {
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        if (PicUtil.uritempFile == null) {
            ToastUtils.getInstance().show("请上传照片");
            return;
        }
        File file = new File(PicUtil.uritempFile.getPath());
        LogUtil.w("当前文件大小："+PicUtil.getPrintSize(file.length()));
        if(!file.exists()){
            ToastUtils.getInstance().show("照片上传失败，请重新上传");
            return;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.ID_CARD)
                .method(RxHttpParams.HttpMethod.POST)
                .addPart("upfile", StringUtil.getMimeType(file.getName()) ,file)
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    String picPath = obj.getJSONObject("data").getString("picPath");
                    if(StringUtil.isNotBlank(picPath)){
                        saveData(picPath);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                ToastUtils.getInstance().show("证件上传失败，请重新上传");
                loadingDialog.dismiss();
            }
        });
    }

    private void saveData(String picPath) {
        String url = Constants.UPLOAD_USER_CARD;
        if("yes".equals(getIntent().getStringExtra("isOrmAgain"))){
            url = Constants.UPLOAD_USER_CARD;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("userCardNum", number);
            node.put("userRealName", name);
            node.put("photoIviews", picPath);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .method(RxHttpParams.HttpMethod.POST)
                .addPart("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addPart("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                ToastUtils.getInstance().show("认证信息已进入审核");
                IntentUtil.startActivity(MainActivity.class);
//                try {
//                    JSONObject object = new JSONObject(str);
//                    int data = object.getJSONObject("data").getInt("status");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFinish() {
                 loadingDialog.dismiss();
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
            PicUtil.startPhotoZoom(Uri.fromFile(tempFile),MineApproveSubmitiPicActivity.this,false);
        }
    }
    private void onPicTrimResult() {
        GlideUtils.loadSideMaxImage(this,ivIcon,PicUtil.uritempFile.getPath());
    }
}
