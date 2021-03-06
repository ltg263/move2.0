package com.secretk.move.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;

import java.util.List;

/**
 * 作者： litongge
 * 时间： 2018/5/31 15:58
 * 邮箱；ltg263@126.com
 * 描述：統一wed調用
 */
public class WebViewActivity extends BaseActivity {
    private WebView mWbview;
    private WebSettings webSettings;
    private String url;
    private ImageView iv;
    private ProgressBar progressbar;
//    private WebViewInterface webViewInterface;
    private static final int HANDLER_1 = 1;
    private WebViewInterface webViewInterface;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenuInfos) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.app_bar_background), 0);
        progressbar = findViewById(R.id.myProgressBar);
        iv = findViewById(R.id.iv);
        mWbview = findViewById(R.id.webview);
        url = getIntent().getStringExtra("url");
        String name = getIntent().getStringExtra("name");
        if(name.equals("关于我们")){
            mMenuInfos.add(0,new MenuInfo(R.string.mine_help, getString(R.string.mine_help), 0));
        }
        if(!url.contains("qufen.top")){
            iv.setVisibility(View.VISIBLE);
        }
        mHeadView.setTitle(name);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        initWebView();
    }

    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startWebViewActivity(Constants.HELP,getResources().getString(R.string.mine_help));
    }

    /**
     * 初始化webview
     */
    private void initWebView() {
        //创建webview接口类
        webViewInterface = new WebViewInterface(WebViewActivity.this, mWbview);
        //设置webview的相关属性
        webSettings = mWbview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        //webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);//html页面大小自适应
        //添加webview接口类并设立一个别名
        mWbview.addJavascriptInterface(webViewInterface, "nativeBridgeInterface");
        mWbview.setWebChromeClient(new WebChromeClient());
        //使用WebViewClient加载页面
        mWbview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mWbview.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        mWbview.loadUrl(url);

    }

    /**
     * 数据初始化
     */
    @Override
    protected void initData() {

    }


    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //设置webview页面的加载进度(进度条形式)
            if (newProgress == 100) {
                progressbar.setVisibility(View.GONE);
            } else {
                if (View.INVISIBLE == progressbar.getVisibility()) {
                    progressbar.setVisibility(View.VISIBLE);
                }
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            //获得html中的title标签内容
//            mHeadView.setTitle(title);
        }
    }

    @Override
    public void onBackPressed() {
        //true表示需要拦截返回按键，否则正常处理

        // 判断是否能够回退
        if (mWbview.canGoBack()) {
            mWbview.goBack();// 返回
        } else {
            super.onBackPressed();
        }

    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_1:
                    // 判断是否能够回退
                    if (mWbview.canGoBack()) {
                        mWbview.goBack();// 返回
                    } else {
                        finish();
                    }
                    break;
            }
        }
    };
}
