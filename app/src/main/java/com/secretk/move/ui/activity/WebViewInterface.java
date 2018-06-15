package com.secretk.move.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * 作者： litongge
 * 时间：  18:32
 * 邮箱；ltg263@126.com
 * 描述：
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
