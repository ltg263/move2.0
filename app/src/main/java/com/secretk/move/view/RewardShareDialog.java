package com.secretk.move.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.ZXingUtils;

import java.io.File;


/**
 * @author lixiaogang
 * @Description
 * @date 2018/2/23
 */

public class RewardShareDialog extends AlertDialog {

    private Context context;
    ScrollView svShare;
    ImageView ivImg;
    RelativeLayout ivIcon;
    String strPath = "";
    long time;
    String title;
    String content;
    String imgPath;
    String url;
    boolean isShowZz;
    public RewardShareDialog(Context context) {
        super(context);
        this.context = context;
    }

    public RewardShareDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_reward_share_img, null);
        ivIcon = view.findViewById(R.id.rl_share);
        svShare = view.findViewById(R.id.sv_share);
        ivImg = view.findViewById(R.id.iv_img);
        setContentView(view);
        Bitmap bitmap = ZXingUtils.createQRImage("https://blog.csdn.net/pxcz110112/article/details/80234997", 600, 600, null);
        ivImg.setImageBitmap(bitmap);
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        shareDownloadImg(imgPath);
    }
    public void setData(long time, final String title, String content,String imgPath,String url,boolean isShowZz){
        this.time = time;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.url = url;
        this.isShowZz = isShowZz;
    }

    /**
     * 图片路劲
     * @param imgPath
     */
    public void shareDownloadImg(final String imgPath) {
        if(ivIcon!=null){
            final String pathDj = Constants.LOCAL_PATH;
            File file = new File(pathDj+File.separator+imgPath+".png");
            if(file.exists() && file.length()>0){
                strPath = file.getPath();
            }else{
                strPath = pathDj+File.separator+imgPath+".png";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap b = DialogUtils.getViewBitmap(ivIcon);
                        strPath = ImageUtils.saveBitmap(b, pathDj, imgPath+".png");
                    }
                }).start();
            }
        }
    }
    public void shareUi(){
        SharePopupWindow popupWindow = new SharePopupWindow(context);
        popupWindow.setShareImg(url);
        if(isShowZz){
            popupWindow.showZzhd();
        }
        popupWindow.showAtLocation(ivImg, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
