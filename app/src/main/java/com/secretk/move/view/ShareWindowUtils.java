package com.secretk.move.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.BitmapUtil;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


public class ShareWindowUtils implements PlatformActionListener {

    @BindView(R.id.tv_share_wechat)
    TextView tvShareWechat;
    @BindView(R.id.tv_share_wechat_moments)
    TextView tvShareWechatMoments;
    @BindView(R.id.tv_share_sina_weibo)
    TextView tvShareSinaWeibo;
    @BindView(R.id.tv_share_qzone)
    TextView tvShareQzone;
    @BindView(R.id.tv_share_qq)
    TextView tvShareQq;
    @BindView(R.id.tv_share_link)
    TextView tvShareLink;
    @BindView(R.id.tv_share_report)
    TextView tvShareReport;
    @BindView(R.id.tv_share_zzhd)
    TextView tvShareZzhd;
    @BindView(R.id.tv_share_bctp)
    TextView tvShareBctp;
    @BindView(R.id.tv_share_kw)
    TextView tvShareKw;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.ll_below)
    LinearLayout llBelow;
    int postId= 0;
    private Context mContext;
    private String imgPath = "";
    private ShareTypeListener mShareTypeListener;
    private  PopupWindow shareImgPopupWindow;
    String url = "";

    public ShareWindowUtils(Context context, View mMenuView, PopupWindow shareImgPopupWindow) {
        this.shareImgPopupWindow = shareImgPopupWindow;
        mContext = context;
        ButterKnife.bind(this, mMenuView);
        tvCancel.setOnClickListener(tvOnClickListener);
        tvShareWechat.setOnClickListener(tvOnClickListener);
        tvShareWechatMoments.setOnClickListener(tvOnClickListener);
        tvShareSinaWeibo.setOnClickListener(tvOnClickListener);
        tvShareQzone.setOnClickListener(tvOnClickListener);
        tvShareQq.setOnClickListener(tvOnClickListener);
        tvShareLink.setOnClickListener(tvOnClickListener);
        tvShareReport.setOnClickListener(tvOnClickListener);
        tvShareZzhd.setOnClickListener(tvOnClickListener);
        tvShareBctp.setOnClickListener(tvOnClickListener);
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    /**
     * 海报分享
     * @param imgPath
     */
    public void setShareImg( final String imgPath,String url) {
        this.imgPath = imgPath;
        this.url = url;
    }

    OnClickListener tvOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            shareImgPopupWindow.dismiss();
            Platform platform;
            switch (view.getId()) {
                case R.id.tv_share_wechat:
                    platform = ShareSDK.getPlatform(Wechat.NAME);
                    shareImg(platform);
                    break;
                case R.id.tv_share_wechat_moments:
                    platform = ShareSDK.getPlatform(WechatMoments.NAME);
                    shareImg(platform);
                    break;
                case R.id.tv_share_sina_weibo:
                    platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                    shareImg(platform);
                    break;
                case R.id.tv_share_qzone:
                    platform = ShareSDK.getPlatform(QZone.NAME);
                    shareImg(platform);
                    break;
                case R.id.tv_share_qq:
                    platform = ShareSDK.getPlatform(QQ.NAME);
                    shareImg(platform);
                    break;
                case R.id.tv_share_link:
                    shareCopy(url);
                    break;
                case R.id.tv_share_report:
                    shareReport();
                    break;
                case R.id.tv_share_zzhd:
                    DialogUtils.showDialogHint(mContext, "您确定要终止活动吗？",false, new DialogUtils.ErrorDialogInterface() {
                        @Override
                        public void btnConfirm() {
                            shareXsEnd();
                        }
                    });
                    break;
                case R.id.tv_share_bctp:
                    LogUtil.w("imgPath:"+imgPath);
                    Bitmap bmp = BitmapUtil.getDiskBitmap(imgPath);
                    if(bmp != null) {
                        String pathDj = Environment.getExternalStorageDirectory()+ File.separator +"区分";
                        if (!new File(pathDj).exists()) {
                            new File(pathDj).mkdirs();
                        }
                        String path = BitmapUtil.saveImgToDisk(pathDj,"find_" + System.currentTimeMillis()+".png",bmp);
                       if(StringUtil.isNotBlank(path)){
                           ImageUtils.galleryAddPic(mContext,path);
                           ToastUtils.getInstance().show("保存成功");
                       }
                    }
                    break;
                case R.id.tv_cancel:
                    break;
            }
        }
    };
    private void shareCopy(String str) {
        ClipboardManager cmb = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cmb != null) {
            DialogUtils.showDialogPraise(mContext,3,true,0);
        } else {
            ToastUtils.getInstance().show("复制失败，请重新复制");
        }
        cmb.setPrimaryClip(ClipData.newPlainText(null, str));
    }
    private void shareImg(Platform platform) {
        Platform.ShareParams paramsToShare = new Platform.ShareParams();
        if ("WechatMoments".equals(platform.getName())) {
            paramsToShare.setShareType(Platform.SHARE_IMAGE);
//                    paramsToShare.setImagePath(str);
        } else if ("SinaWeibo".equals(platform.getName())) {
//                    paramsToShare.setImagePath(str);
        } else if ("Wechat".equals(platform.getName())) {
            paramsToShare.setShareType(Platform.SHARE_IMAGE);
//                    paramsToShare.setImagePath(str);
        } else if ("QQ".equals(platform.getName())) {

        } else {

        }
        if(!imgPath.contains("http")){
            paramsToShare.setImagePath(imgPath);
        }else{
            paramsToShare.setImageUrl(imgPath);
        }
//        Platform a = ShareSDK.getPlatform()
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        platform.setPlatformActionListener(this);
        // 执行图文分享
        platform.share(paramsToShare);

    }
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//        ToastUtils.getInstance().show("分享成功");
        LogUtil.d("分享成功:" + i + "--" + platform.getName());
        addPostShare(postId);
        if (platform.getName().equals("QQ")) {
            if (mShareTypeListener != null) {
                mShareTypeListener.shareType(1);
            }
        } else if (platform.getName().equals("QZone")) {
            if (mShareTypeListener != null) {
                mShareTypeListener.shareType(2);
            }
        } else if (platform.getName().equals("Wechat")) {
            if (mShareTypeListener != null) {
                mShareTypeListener.shareType(3);
            }
        } else if (platform.getName().equals("WechatMoments")) {
            if (mShareTypeListener != null) {
                mShareTypeListener.shareType(4);
            }
        }

    }

    private void shareReport() {
        String getReportModelList = SharedUtils.singleton().get("getReportModelList", "");
        if(StringUtil.isNotBlank(getReportModelList)){
            try {
                final JSONArray array = new JSONArray(getReportModelList);
                List<String> list = new ArrayList<>();
                List<Integer> listIndex = new ArrayList<>();
                for(int i=0;i<array.length();i++){
                    final JSONObject object = array.getJSONObject(i);
                    if(StringUtil.isNotBlank(object.getString("reportName"))){
                        list.add(object.getString("reportName"));
                        listIndex.add(object.getInt("reportId"));
                    }
                }
                ReportPopupWindow window = new ReportPopupWindow(mContext);
                window.setData(list,listIndex,postId);
                window.showAtLocation(tvCancel, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void shareXsEnd() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.getToken());
            node.put("postId", postId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.REWARD_LIST_AZ)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                DialogUtils.showDialogPraise(mContext, 8, true, 0);
            }
            @Override
            public void onError(String message) {
                DialogUtils.showDialogPraise(mContext, 8, false, 0);
            }
        });
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        ToastUtils.getInstance().show("分享失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        ToastUtils.getInstance().show("取消分享");
    }

    /**
     * 隐藏 复制链接和 举报
     */
    public void concealBelow() {
        llBelow.setVisibility(View.GONE);
    }
    /**
     * 显示保存图片
     * true 显示终止活动
     * false 不显示终止活动
     */
    public void showBctp(boolean isZzhd) {
        tvShareBctp.setVisibility(View.VISIBLE);
        if(isZzhd){
            tvShareZzhd.setVisibility(View.VISIBLE);
            tvShareKw.setVisibility(View.GONE);
        }else{
            tvShareZzhd.setVisibility(View.GONE);
            tvShareKw.setVisibility(View.INVISIBLE);
        }
    }


    public interface ShareTypeListener {
        void shareType(int type);
    }

    public void setShareTypeListener(ShareTypeListener mShareTypeListener) {
        this.mShareTypeListener = mShareTypeListener;
    }

    private void addPostShare(int postId){
        if(postId == 0){
            DialogUtils.showDialogPraise(mContext,5,true,0);
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.getToken());
//            node.put("activityId", Integer.valueOf(activityId));
            node.put("postId", postId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.ADD_POST_SHARE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject object = new JSONObject(str).getJSONObject("data");
                    boolean isShare = object.getBoolean("isShareAward");
                    double amount = 0;
                    if(isShare){
                        amount = object.getDouble("amount");
                    }
                    DialogUtils.showDialogPraise(mContext,5,isShare,amount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
