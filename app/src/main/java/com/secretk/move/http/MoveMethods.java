package com.secretk.move.http;


import com.secretk.move.bean.RegisterBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MoveMethods {
   @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("encryptPolicy?policy")
    Observable<RegisterBean> rxRegister(@Body RequestBody route);
}
