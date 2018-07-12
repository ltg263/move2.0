package com.secretk.move.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.igexin.sdk.PushManager;
import com.secretk.move.R;
import com.secretk.move.apiService.MoveIntentService;
import com.secretk.move.apiService.MovePushService;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.SharedUtils;


public class SplashScreenActivity extends Activity {

    private boolean isFirst = false;
    private Boolean isLogin=false;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Constants.IS_PUSH_SERVICE){
            PushManager.getInstance().initialize(this.getApplicationContext(), MovePushService.class);
            PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), MoveIntentService.class);
        }
        setContentView(R.layout.splashscreen);
//		getWindow().setFormat(PixelFormat.RGBA_8888);

        isFirst = SharedUtils.singleton().get("isFirst",false);
        isLogin = SharedUtils.singleton().get(Constants.IS_LOGIN_KEY,false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startUi();
            }
        }, 2000);
    }

    private void startUi() {
        Intent intent = new Intent();
        if (false) {
//            intent.setClass(SplashScreenActivity.this, ViewPagerActivity.class);
//            SharedUtils.singleton().put("isFirst", false);
            intent.setClass(SplashScreenActivity.this, MainActivity.class);
        } else {
//            if(isLogin){
            if(true){
                intent.setClass(SplashScreenActivity.this, MainActivity.class);
            }else{
                intent.setClass(SplashScreenActivity.this, LoginHomeActivity.class);
            }
        }
        startActivity(intent);
        finish();
    }

}
