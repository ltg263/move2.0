package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PicUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.TimeToolUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        tvSelectedYes.setSelected(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.submit_project_title));
        mMenuInfos.add(0, new MenuInfo(R.string.evaluation_next, getString(R.string.evaluation_next), 0));
        return mHeadView;
    }

    @OnClick({R.id.iv_icon, R.id.ll_selector_time, R.id.tv_selected_yes, R.id.tv_selected_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                String[] srt = {getString(R.string.submit_project_logo), getString(R.string.selector_photo_1), getString(R.string.selector_photo_2)};
                DialogUtils.ShowAlertDialog(this, srt, new DialogUtils.AlertDialogInterface() {
                    @Override
                    public void btnLineListener(int index) {
                        if(index==1){//从手机相册中选择
                            PicUtil.openPhoto(SubmitProjectActivity.this);
                        }else if(index ==2){//拍照上传
                            PicUtil.openCamera(SubmitProjectActivity.this);
                        }
                    }
                });
                break;
            case R.id.ll_selector_time:
                setSendRQ();
                break;
            case R.id.tv_selected_yes:
                tvSelectedYes.setSelected(true);
                tvSelectedNo.setSelected(false);
                break;
            case R.id.tv_selected_no:
                tvSelectedNo.setSelected(true);
                 tvSelectedYes.setSelected(false);
                break;
        }
    }
    private void setSendRQ() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        long minTime = 0;
        long currentTime = 0;
        try {
            minTime = new SimpleDateFormat("yyyy-MM-dd").parse((year - 50) + "-01-01").getTime();
            if(!getString(R.string.submit_project_66).equals(tvSendTime.getText().toString())){
               currentTime = new SimpleDateFormat("yyyy-MM-dd").parse(tvSendTime.getText().toString()).getTime();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        long currentTime = System.currentTimeMillis();
        TimeToolUtils.showTimeView(SubmitProjectActivity.this, Type.YEAR_MONTH_DAY, minTime, currentTime, true, new TimeToolUtils.OnTimeChangedListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                tvSendTime.setText(TimeToolUtils.getMilltoTime(millseconds, "yyyy-MM-dd"));
            }
        });
    }


    @Override
    protected void OnToolbarRightListener() {

        if(PicUtil.uritempFile==null
                || StringUtil.isBlank(PicUtil.uritempFile.getPath())
                || StringUtil.isBlank(etInput01.getText().toString().trim())
                || StringUtil.isBlank(etInput02.getText().toString().trim())
                || StringUtil.isBlank(etInput03.getText().toString().trim())
                || StringUtil.isBlank(etInput04.getText().toString().trim())
                || StringUtil.isBlank(etInput05.getText().toString().trim())
                || getString(R.string.submit_project_66).equals(tvSendTime.getText().toString())){
            ToastUtils.getInstance().show("请完善信息");
            return;
        }

        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        final File file = new File(PicUtil.uritempFile.getPath());
        if(!file.exists()){
            ToastUtils.getInstance().show("照片上传失败，请重新上传");
            return;
        }
        NetUtil.getQiniuToken(new NetUtil.SaveCommendationImp() {
            @Override
            public void finishCommendation(String userId,String QiToken, boolean status) {
                if(!status){
                    ToastUtils.getInstance().show("服务器出错了");
                    loadingDialog.dismiss();
                    return;
                }
                NetUtil.sendQiniuImgUrl(file, QiToken, NetUtil.getQiniuImgName("avatars",userId,0), new NetUtil.QiniuImgUpload() {
                    @Override
                    public void uploadStatus(String str, boolean status) {
                        if(status){
                            saveData(str);
                        }else{
                            ToastUtils.getInstance().show("证件上传失败，请重新上传");
                            loadingDialog.dismiss();
                        }
                    }
                });
            }
        });
    }

    private void saveData(String picPath) {
        String listed = "0";
        if(tvSelectedYes.isSelected()){
            listed = "1";
        }
        String key[] = {"projectIcon","projectCode","projectEnglishName","projectChineseName","websiteUrl","issueNum","issueDateStr","listed"};
        String values[] = {picPath,etInput01.getText().toString().trim(),etInput02.getText().toString().trim(),etInput03.getText().toString().trim(),
                etInput04.getText().toString().trim(),etInput05.getText().toString().trim(),tvSendTime.getText().toString().trim(),listed};
        IntentUtil.startActivity(SubmitProjectTwoActivity.class, key, values);
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
            PicUtil.startPhotoZoom(Uri.fromFile(tempFile),SubmitProjectActivity.this,true);
        }
    }
    private void onPicTrimResult() {
        GlideUtils.loadCircleProjectUrl(this,ivIcon,PicUtil.uritempFile.getPath());
    }
}
