package com.secretk.move.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import java.io.File;


/**
 * @author lixiaogang
 * @Description
 * @date 2018/2/23
 */

public class CustomDialog extends Dialog {

    private Context context;
    TextView tvTitle;
    TextView tvTime;
    TextView tvNum;
    TextView tvContent;
    ScrollView svShare;
    RelativeLayout ivIcon;
    String strPath = "";
    long time;
    String title;
    String content;
    String imgPath;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share_img, null);
        ivIcon = view.findViewById(R.id.rl_share);
        svShare = view.findViewById(R.id.sv_share);
        tvTitle = view.findViewById(R.id.tv_title);
        tvTime = view.findViewById(R.id.tv_time);
        tvNum = view.findViewById(R.id.tv_num);
        int awardToken = SharedUtils.singleton().get("awardToken", 0);
        tvNum.setText(awardToken+"w");
        tvContent = view.findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tvTime.setText(StringUtil.getTimeToEhm(time));
        tvContent.setText(content);
        shareDownloadImg(imgPath);
        setContentView(view);
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
    public void setData(long time, final String title, String content,String imgPath){
        this.time = time;
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
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
        popupWindow.setShareImg(strPath);
        popupWindow.showAtLocation(tvTime, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
