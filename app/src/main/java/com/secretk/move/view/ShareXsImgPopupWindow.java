package com.secretk.move.view;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ZXingUtils;

import java.io.File;

/**
 *
 * @time 2014/11/5
 */
public class ShareXsImgPopupWindow extends PopupWindow {

    private View mMenuView;
    private Context mContext;

    String strPath = "";
    private ShareWindowUtils shareWindowUtils;
    private TextView tvFind;
    private TextView tvStatus;
    private TextView tvEndSm;
    private TextView tvEndTime;
    ScrollView svShare;
    ImageView ivImg;
    RelativeLayout rlIcon;
    String url;
    int postId;
    boolean isShowZz;

    public ShareXsImgPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow_share_img_xh, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        this.setClippingEnabled(false);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        setViewShow();
        setEvent();
    }

    private void setViewShow() {

        rlIcon = mMenuView.findViewById(R.id.rl_share);
        svShare = mMenuView.findViewById(R.id.sv_share);
        ivImg = mMenuView.findViewById(R.id.iv_img);
        tvFind = mMenuView.findViewById(R.id.tv_find);
        tvStatus = mMenuView.findViewById(R.id.tv_status);
        tvEndSm = mMenuView.findViewById(R.id.tv_end_sm);
        tvEndTime = mMenuView.findViewById(R.id.tv_end_time);
        View shareView = mMenuView.findViewById(R.id.item_share_img_popupwindow);
        shareWindowUtils = new ShareWindowUtils(mContext,shareView,this);
    }

    private void setEvent() {
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    public void setData(String findNum, String status, String imgPath,String url,String endSm,String endTime,int postId,boolean isShowZz){
        Bitmap bitmap = ZXingUtils.createQRImage(url, 600, 600, null);
        ivImg.setImageBitmap(bitmap);
        tvFind.setText(findNum);
        tvStatus.setText(status);
        tvEndSm.setText(endSm);
        tvEndTime.setText(endTime);
        this.url = url;
        shareDownloadImg(imgPath);
    }
    /**
     * 图片路劲
     * @param imgPath
     */
    public void shareDownloadImg(final String imgPath) {
        if(rlIcon!=null) {
        final String pathDj = Constants.LOCAL_PATH;
            strPath = pathDj+File.separator+imgPath+".png";
            File file = new File(pathDj+File.separator+imgPath+".png");
            if(file.exists() && file.length()>0){
                strPath = file.getPath();
            }else{
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap b = DialogUtils.getViewBitmap(rlIcon);
                        strPath = ImageUtils.saveBitmap(b, pathDj, imgPath+".png");
                    }
                }).start();
            }
            if(StringUtil.isNotBlank(strPath)){
                shareWindowUtils.setShareImg(strPath,url);
            }
        }
    }
    public void showShareView(){
        shareWindowUtils.showBctp(isShowZz);
    }
}
