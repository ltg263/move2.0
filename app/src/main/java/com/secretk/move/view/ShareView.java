package com.secretk.move.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RelativeLayout;

import com.secretk.move.R;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.sharesdk.OnekeyShare;
import com.secretk.move.sharesdk.ShareContentCustomizeCallback;
import com.secretk.move.utils.ImageUtils;
import com.secretk.move.utils.StringUtil;

import cn.sharesdk.framework.Platform;

/**
 * 作者： litongge
 * 时间： 2018/6/15 16:10
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class ShareView {
    private static Context mContext = BaseManager.app;


    public static void showShare(final String skipRrl, final String title, final String content, final String imgUrl) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                String url = "http://download.sdk.mob.com/web/images/2018/05/25/10/1527215980143/108_108_7.07.png";
                String imgWechat = "";
                if (StringUtil.isNotBlank(imgUrl) && imgUrl.contains("http")) {
                    imgWechat = imgUrl + "?imageView2/1/w/108";
                }
                if ("WechatMoments".equals(platform.getName())) {
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                    paramsToShare.setTitle(title);
                    paramsToShare.setText(content);
                    paramsToShare.setImageUrl(url);
                    paramsToShare.setUrl(skipRrl);
                } else if ("SinaWeibo".equals(platform.getName())) {
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                    paramsToShare.setTitle(mContext.getString(R.string.app_name));
                    paramsToShare.setText("微博标头i" + skipRrl);
                } else if ("Wechat".equals(platform.getName())) {
                    paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                    paramsToShare.setTitle(title);
                    paramsToShare.setText(content);
                    paramsToShare.setUrl(skipRrl);
                    paramsToShare.setImageUrl(url);
                } else if ("QQ".equals(platform.getName())) {
                    paramsToShare.setTitle(title);
                    paramsToShare.setText(content);
                    paramsToShare.setTitleUrl(skipRrl);
                    if (StringUtil.isNotBlank(imgWechat)) {
                        paramsToShare.setImageUrl(imgWechat);
                    } else {
                        paramsToShare.setImageUrl(url);
                    }
                } else {

                }
            }
        });
        oks.show(mContext);
    }


    private static String str;
    public static void showShare1(final RelativeLayout relativeLayout, final String imgPath) {
        if(StringUtil.isBlank(imgPath) && relativeLayout!=null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Bitmap b = DialogUtils.getViewBitmap(relativeLayout);
//                String pathDj = Environment.getExternalStorageDirectory()+ File.separator +"区分";
                    String pathDj = Constants.LOCAL_PATH;
                    str = ImageUtils.saveBitmap(b, pathDj, "share.png");
                }
            }).start();
        }

        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {

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
                if(StringUtil.isBlank(imgPath)){
                    paramsToShare.setImagePath(str);
                }else{
                    paramsToShare.setImageUrl(imgPath);
                }
            }
        });

    // 启动分享GUI
        oks.show(mContext);
    }
}
