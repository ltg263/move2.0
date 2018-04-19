package com.secretk.move.http;

import android.content.Context;



import java.io.File;
import java.util.concurrent.TimeUnit;


import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class Network {
    private static OkHttpClient mOkHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static HttpMethods methods;
    private static MoveMethods moveMethods;

    private static String BASEURL="http://api.fir.im/apps/latest/";
    private static String MOVEBASEURL="http://47.98.197.101/tzg-rest";
    public static void initOkhttp(Context mContext) {
        if (mOkHttpClient == null) {
            synchronized (Network.class) {
                if (mOkHttpClient == null) {
                    File mFile = new File(mContext.getCacheDir(), "cache");
                    Cache mCache = new Cache(mFile, 1024 * 1024 * 200);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(mCache)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(15, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }

    public static HttpMethods getMethods() {
        if (methods == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(BASEURL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)

                    .build();
            methods = retrofit.create(HttpMethods.class);
        }
        return methods;
    }

    public static MoveMethods getMoveMethods() {
        if (moveMethods == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl(BASEURL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)

                    .build();
            moveMethods = retrofit.create(MoveMethods.class);
        }
        return moveMethods;
    }
}
