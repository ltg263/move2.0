package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.gson.Gson;
import com.secretk.move.R;
import com.secretk.move.bean.postbean.PostRegisterBean;
import com.secretk.move.bean.returnbean.RegisterBean;
import com.secretk.move.http.Network;
import com.secretk.move.utils.AESUtil;
import com.secretk.move.utils.CrashHandler;
import com.secretk.move.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HttpActivity extends AppCompatActivity {
    String key="0987654321qazxcv";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }
    public void rxDemo(View view) throws Exception {
//       String str2="=%22{%22phone%22:%2215967158669%22,%22module%22:%22register%22}%22 ";
        PostRegisterBean registerBean=new PostRegisterBean("15967158669","register");
        Gson gson=new Gson();
        String route= gson.toJson(registerBean);//通过Gson将Bean转化为Json字符串形式
        String str= AESUtil.encrypt(route,key);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        Network.getMoveMethods().rxDemo(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RegisterBean>() {
            @Override
            public void accept(RegisterBean versionBean) throws Exception {
              String str="PostRegisterBean";
                ToastUtils.getInstance().show(str);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
              CrashHandler.getInstance().saveCrashInfo2File(throwable);
                String str="Throwable";
                ToastUtils.getInstance().show(str);

            }
        });
    }
    public void rxGetVerificationCode(View view){

    }
}
