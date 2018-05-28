package com.secretk.move.view;

import android.content.Context;

import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.sharesdk.OnekeyShare;
import com.secretk.move.sharesdk.ShareContentCustomizeCallback;

import cn.sharesdk.framework.Platform;

/**
 * 文件名：com.hn.ssc.view
 * 描    述：SSC
 * 作    者：yujian
 * 时    间：2016/7/19 20:01
 * 版    权： 南海云
 */
public class ShareView {
    private static Context mContext = BaseManager.app;

    public static void showShare(final String skipRrl, final String title, final String content) {
        final OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
            @Override
            public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
                paramsToShare.setTitle(title);
                paramsToShare.setText(content);
                paramsToShare.setImageUrl("http://download.sdk.mob.com/web/images/2018/05/25/10/1527215980143/108_108_7.07.png");
                paramsToShare.setUrl(skipRrl);

                if("WechatMoments".equals(platform.getName())){

                }else if("SinaWeibo".equals(platform.getName())){

                    paramsToShare.setText("微博标头i"+skipRrl);

                }else if("Wechat".equals(platform.getName())){

                }else if("QQ".equals(platform.getName())){

                }else{

                }
            }
        });
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
       /* // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mContext.getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(content);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("企业定制化解决方案平台"+content);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(content);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("企业定制化解决方案平台");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mContext.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(content);*/
        // 构造一个图标
//        Bitmap enableLogo = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.copy_link);
////        String label = mContext.getString(R.string.share_link);
////        View.OnClickListener listener = new View.OnClickListener() {
////            public void onClick(View v) {
////                ClipboardManager cmb = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
////                if (cmb != null) {
////                    UIUtil.shortToast("复制成功");
////                } else {
////                    UIUtil.shortToast("复制失败，请重新复制");
////                }
////                cmb.setPrimaryClip(ClipData.newPlainText(null, content));
////            }
////        };
//        oks.setCustomerLogo(enableLogo, label, listener);
        oks.show(mContext);
    }
}
