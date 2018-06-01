package com.secretk.move.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * 文件名：com.juanct.iwork99.activity.base
 * 描    述：iwork99
 * 作    者：yujian
 * 时    间：2016/8/5 17:27
 * 版    权： 99云企
 */
public class WebViewInterface {
    private Context mContext;
    private WebView mWbview;
    public String callback;

    public WebViewInterface(Context context, WebView webview) {
        this.mContext = context;
        this.mWbview = webview;
    }

    /**
     * 进入店铺
     */
    @JavascriptInterface
    public void enterShop(String id, String name, String type) {
//        IntentUtil.startStoreActivity(mContext, id, name, Integer.parseInt(type));
    }


    /**
     * 公共回调方法
     */
    public void callbackHandle(final String imageCode, final String callback) {
        //执行回调
        ((Activity) mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mWbview.loadUrl("javascript:nativeBridgeClass.allCallbackHandle('" + imageCode + "'," + callback + ")");
            }
        });
    }


}
