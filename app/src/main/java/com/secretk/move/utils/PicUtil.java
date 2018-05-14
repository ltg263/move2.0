package com.secretk.move.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * 作者： litongge
 * 时间：  2018/5/14 11:29
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PicUtil {

    public static final int CAMERA_REQUEST_CODE = 1;//相机
    public static final int RESULT_REQUEST_CODE = 2;//结果
    public static final String USER_NEME = "move";//相机

    public static void openPhoto(Context context) {
        //Crop.pickImage((Activity) context);
    }


    public static void openCamera(final Context context) {
        File file = new File(Constants.LOCAL_PATH);
        LogUtil.w("file:"+file);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            PermissionsManager.get().checkPermissions((Activity) context, Manifest.permission.CAMERA, new PermissionsManager.CheckCallBack() {
                @Override
                public void onSuccess(String permission) {
                    Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 判断存储卡是否可以用，可用进行存储
                    if (hasSdcard()) {
                        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Constants.LOCAL_PATH, USER_NEME + ".jpg")));
                    }
                    ((Activity)context).startActivityForResult(intentFromCapture,CAMERA_REQUEST_CODE);
                }

                @Override
                public void onError(String permission) {

                }
            });
        } else {
            Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // 判断存储卡是否可以用，可用进行存储
            if (hasSdcard()) {
                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Constants.LOCAL_PATH, USER_NEME + ".jpg")));
            }
            ((Activity)context).startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
        }
    }
    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public static void startPhotoZoom(Context context,Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        ((Activity)context).startActivityForResult(intent, RESULT_REQUEST_CODE);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     */
    @SuppressWarnings("deprecation")
    public static void getImageToView(Intent data) {
        Bundle extras = data.getExtras();
        LogUtil.w("data:"+data);
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            try {
                saveBitmap(photo, USER_NEME + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 将剪切后的图片存储到本地
     *
     * @param bitmap
     * @param bitName
     * @throws IOException
     */
    public static void saveBitmap(Bitmap bitmap, String bitName) throws IOException {
        String path = ImageUtils.saveBitmap(bitmap, Constants.LOCAL_PATH, bitName);
        String BasePath = ImageUtils.getImageBase64(path);
        loadDataImage(BasePath);
    }
    /**
     * 上传头像
     *
     * @param imgdata
     */
    private static void loadDataImage(final String imgdata) {
        //UPLOAD_USER_ICON
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("imgdata", imgdata);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPLOAD_USER_ICON)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {

            }

            @Override
            public void onError(String message) {
            }
        });

    }
    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }


}
