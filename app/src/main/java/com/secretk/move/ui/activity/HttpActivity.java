package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.bean.RegisterBean;
import com.secretk.move.bean.VersionBean;
import com.secretk.move.http.Network;
import com.secretk.move.utils.CrashHandler;
import com.secretk.move.utils.ToastUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

public class HttpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
    }
    public void rxRegister(View view){
        String str="=%22{%22phone%22:%2215967158669%22,%22module%22:%22register%22}%22 ";
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),str);
        Network.getMoveMethods().rxRegister(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RegisterBean>() {
            @Override
            public void accept(RegisterBean versionBean) throws Exception {
              String str="RegisterBean";
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
}
