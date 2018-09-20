package com.secretk.move.view;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.ui.activity.MineApproveSubmitiCertificateActivity;
import com.secretk.move.ui.adapter.DiaLogListReportAdapter;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;

/**
 *
 * @time 2014/11/5
 */
public class ShareInfoImgPopupWindow extends PopupWindow {

    private View mMenuView;
    private Context mContext;

    TextView tvTitle;
    TextView tvTime;
    TextView tvNum;
    TextView tvContent;
    ScrollView svShare;
    RelativeLayout ivIcon;
    String strPath = "";
    private ShareWindowUtils shareWindowUtils;

    public ShareInfoImgPopupWindow(Context context) {
        super(context);
        this.mContext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow_share_img_info, null);
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
        ivIcon = mMenuView.findViewById(R.id.rl_share);
        svShare = mMenuView.findViewById(R.id.sv_share);
        tvTitle = mMenuView.findViewById(R.id.tv_title);
        tvTime = mMenuView.findViewById(R.id.tv_time);
        tvNum = mMenuView.findViewById(R.id.tv_num);
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

    public void setData(long time, final String title, String content,String imgPath){
        int awardToken = SharedUtils.singleton().get("awardToken", 0);
        tvNum.setText(awardToken+"w");
        tvContent = mMenuView.findViewById(R.id.tv_content);
        tvTitle.setText(title);
        tvTime.setText(StringUtil.getTimeToEhm(time));
        tvContent.setText(content);
        shareDownloadImg(imgPath);
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
                shareWindowUtils.setShareImg(strPath,"");
            }else{
                strPath = pathDj+File.separator+imgPath+".png";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap b = DialogUtils.getViewBitmap(ivIcon);
                        strPath = ImageUtils.saveBitmap(b, pathDj, imgPath+".png");
                        shareWindowUtils.setShareImg(strPath,"");
                    }
                }).start();
            }
        }
    }
    public void showShareView(){
        shareWindowUtils.concealBelow();
    }
}
