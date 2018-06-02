package com.secretk.move.interactor.impl;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.secretk.move.MoveApplication;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.VersionBean;
import com.secretk.move.contract.ActivityMainContract;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;


/**
 * Created by zc on 2018/4/6.
 */

public class MainInteractorImpl implements ActivityMainContract.Interactor {
    private ActivityMainContract.CallBack callBack;

    public MainInteractorImpl(ActivityMainContract.CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public void downLoadApk(final String url) {

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                Boolean isSuccess = okHttpDownLoad(url);
                emitter.onNext(isSuccess);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                callBack.isDownLoadSuccess(aBoolean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
//                CrashHandler.getInstance().saveCrashInfo2File(throwable);
//                callBack.requestFailed("downLoadApk 异常了");
            }
        });
    }

    @Override
    public void NetWorkVersion() {
        JSONObject node = new JSONObject();
        try {
            node.put("token", SharedUtils.singleton().get("token",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.UPGRADE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, VersionBean.class, new HttpCallBackImpl<VersionBean>() {
            @Override
            public void onCompleted(VersionBean str) {
                callBack.requestSuccess(str.getData());
            }
        });
    }

    public boolean okHttpDownLoad(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                return false;
            }
            ResponseBody body = response.body();
            long contentLength = body.contentLength();
            BufferedSource source = body.source();
            File file = new File(Constants.APKPATH);
            BufferedSink sink = Okio.buffer(Okio.sink(file));
            sink.writeAll(source);
            sink.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void install(String apkPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(MoveApplication.getContext(), "com.secretk.move.fileprovider", new File(apkPath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        MoveApplication.getContext().startActivity(intent);
    }

    @Override
    public void destroy() {
        callBack=null;
    }

}
