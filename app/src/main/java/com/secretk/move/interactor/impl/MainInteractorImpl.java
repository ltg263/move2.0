package com.secretk.move.interactor.impl;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.secretk.move.MoveApplication;
import com.secretk.move.bean.VersionBean;
import com.secretk.move.http.Network;
import com.secretk.move.interactor.MainInteractor;
import com.secretk.move.listener.MainRequestCallBack;
import com.secretk.move.utils.Constant;
import com.secretk.move.utils.CrashHandler;

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

public class MainInteractorImpl implements MainInteractor {
    private MainRequestCallBack callBack;

    public MainInteractorImpl(MainRequestCallBack callBack) {
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
        Network.getMethods().getVersionInfos().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<VersionBean>() {
            @Override
            public void accept(VersionBean versionBean) throws Exception {
                callBack.requestSuccess(versionBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
//                CrashHandler.getInstance().saveCrashInfo2File(throwable);
                callBack.requestFailed("NetWorkVersion异常了");
            }
        });
    }

    @Override
    public int localVerison() {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = MoveApplication.getContext().getPackageManager().
                    getPackageInfo(MoveApplication.getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
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
            File file = new File(Constant.APKPATH);
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
            Uri contentUri = FileProvider.
                    getUriForFile(MoveApplication.getContext(),
                            "com.secretk.move.fileprovider",
                            new File(apkPath));
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        MoveApplication.getContext().startActivity(intent);
    }

}
