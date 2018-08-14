package com.secretk.move.view;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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


public class SharePopupWindow extends PopupWindow implements PlatformActionListener {


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
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    private View mMenuView;
    private Context mContext;
    private String url;
    private String title;
    private String content;
    private String imgUrl;
    private String activityId;
    private boolean isImg = false;
    private int postId;
    private String imgPath;
    private ShareTypeListener mShareTypeListener;

    @SuppressLint("InflateParams")
    public SharePopupWindow(Context context) {
        super(context);
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.item_share_popupwindow, null);
        ButterKnife.bind(this, mMenuView);
        tvCancel.setOnClickListener(tvOnClickListener);
        tvShareWechat.setOnClickListener(tvOnClickListener);
        tvShareWechatMoments.setOnClickListener(tvOnClickListener);
        tvShareSinaWeibo.setOnClickListener(tvOnClickListener);
        tvShareQzone.setOnClickListener(tvOnClickListener);
        tvShareQq.setOnClickListener(tvOnClickListener);
        tvShareLink.setOnClickListener(tvOnClickListener);
        tvShareReport.setOnClickListener(tvOnClickListener);
        //		tv_photo_count.setText("亲，你还可以上传"+(5-mDataList.size())+"张图片。");
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
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

    public void setShareUrl(String activityId,String title, String content, String url, String imgUrl,int postId) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.imgUrl = imgUrl;
        this.activityId = activityId;
        this.isImg = false;
        this.postId = postId;
    }

    /**
     * 海报分享
     * @param imgPath
     */
    public void setShareImg( final String imgPath) {
        this.imgPath = imgPath;
        this.isImg = true;
        if(!imgPath.contains("http")){
//            tvShareLink.setVisibility(View.INVISIBLE);
        }
    }

    OnClickListener tvOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            dismiss();
            switch (view.getId()) {
                case R.id.tv_share_wechat:
                    if(isImg){
                        Platform platform = ShareSDK.getPlatform(Wechat.NAME);
                        shareImg(platform);
                        return;
                    }
                    shareToWechat(0);
                    break;
                case R.id.tv_share_wechat_moments:
                    if(isImg){
                        Platform platform = ShareSDK.getPlatform(WechatMoments.NAME);
                        shareImg(platform);
                        return;
                    }
                    shareToWechat(1);
                    break;
                case R.id.tv_share_sina_weibo:
                    if(isImg){
                        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
                        shareImg(platform);
                        return;
                    }
                    shareSinaWeibo();
                    break;
                case R.id.tv_share_qzone:
                    if(isImg){
                        Platform platform = ShareSDK.getPlatform(QZone.NAME);
                        shareImg(platform);
                        return;
                    }
                    shareToQQ(1);
                    break;
                case R.id.tv_share_qq:
                    if(isImg){
                        Platform platform = ShareSDK.getPlatform(QQ.NAME);
                        shareImg(platform);
                        return;
                    }
                    shareToQQ(0);
                    break;
                case R.id.tv_share_link:
                    if(isImg){
                        shareCopy(imgPath);
                        return;
                    }
                    shareCopy(url);
                    break;
                case R.id.tv_share_report:
                    shareReport();
                    break;
                case R.id.tv_cancel:
                    break;
            }
        }
    };

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

    private void shareSinaWeibo() {
        Platform.ShareParams paramsToShare = new Platform.ShareParams();
        paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
        paramsToShare.setTitle(mContext.getString(R.string.app_name));
        paramsToShare.setText(content);
        Platform platform = ShareSDK.getPlatform(SinaWeibo.NAME);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        platform.setPlatformActionListener(this);
        // 执行图文分享
        platform.share(paramsToShare);
    }


    private void shareToWechat(final int i) {
        imgUrl = "http://download.sdk.mob.com/web/images/2018/05/25/10/1527215980143/108_108_7.07.png";
        Platform.ShareParams paramsToShare = new Platform.ShareParams();
        Platform platform;
        if (i == 1) {
            platform = ShareSDK.getPlatform(WechatMoments.NAME);
        } else {
            platform = ShareSDK.getPlatform(Wechat.NAME);
        }
        paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
        paramsToShare.setTitle(title);
        paramsToShare.setText(content);
        paramsToShare.setImageUrl(imgUrl);
        paramsToShare.setUrl(url);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        platform.setPlatformActionListener(this);
        // 执行图文分享
        platform.share(paramsToShare);
    }

    private void shareToQQ(int index) {
        String headUrl = "http://download.sdk.mob.com/web/images/2018/05/25/10/1527215980143/108_108_7.07.png";
        String imgWechat = "";
        if (StringUtil.isNotBlank(imgUrl) && imgUrl.contains("http")) {
            imgWechat = imgUrl + "?imageView2/1/w/108";
        }
        Platform.ShareParams paramsToShare = new Platform.ShareParams();
        Platform platform = ShareSDK.getPlatform(QQ.NAME);
        if(index==1){
            paramsToShare.setSite("区分");
            platform = ShareSDK.getPlatform(QZone.NAME);
        }
        paramsToShare.setTitle(title);
        paramsToShare.setText(content);
        paramsToShare.setTitleUrl(url);
        if (StringUtil.isNotBlank(imgWechat)) {
            paramsToShare.setImageUrl(imgWechat);
        } else {
            paramsToShare.setImageUrl(headUrl);
        }
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        platform.setPlatformActionListener(this);
        // 执行图文分享
        platform.share(paramsToShare);
    }


    private void shareCopy(String str) {
        ClipboardManager cmb = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cmb != null) {
            DialogUtils.showDialogPraise(mContext,3,true,0);
        } else {
            ToastUtils.getInstance().show("复制失败，请重新复制");
        }
        cmb.setPrimaryClip(ClipData.newPlainText(null, str));
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        ToastUtils.getInstance().show("分享成功");
        LogUtil.d("分享成功:" + i + "--" + platform.getName());
        if(StringUtil.isNotBlank(activityId)){
            addPostShare(activityId);
        }
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
//                EventBus.getDefault().post(new MessageEvent("","wechatShare"));
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

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        ToastUtils.getInstance().show("分享失败");
    }

    @Override
    public void onCancel(Platform platform, int i) {
        ToastUtils.getInstance().show("取消分享");
    }


    public interface ShareTypeListener {
        void shareType(int type);
    }

    public void setShareTypeListener(ShareTypeListener mShareTypeListener) {
        this.mShareTypeListener = mShareTypeListener;
    }

    private void addPostShare(String activityId){
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.getToken());
            node.put("activityId", Integer.valueOf(activityId));
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
            public void onCompleted(String str) {}
        });
    }
}
