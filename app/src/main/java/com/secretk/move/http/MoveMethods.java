package com.secretk.move.http;


import com.secretk.move.bean.returnbean.ReturnRegisterBean;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/**
 * 很多东西不能调，先放着！返回的数据肯定不全是RegisterBean
 * */
public interface MoveMethods {
    // 和用户相关接口
   @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("encryptPolicy?policy")
    Observable<ReturnRegisterBean> rxDemo(@Body RequestBody route);


    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/dynamicValidateCode/send?policy")
    Observable<ReturnRegisterBean> rxGetVerificationCode(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/register?policy")
    Observable<ReturnRegisterBean> rxRegister(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/login?policy")
    Observable<ReturnRegisterBean> rxLogin(@Body RequestBody route);
    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/forgetPassword?policy")
    Observable<ReturnRegisterBean> rxForgetPassword(@Body RequestBody route);
    //Todo   上传头像先空着

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/myFollowList?policy")
    Observable<ReturnRegisterBean> rxMyFollowList(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/myCollectList?policy")
    Observable<ReturnRegisterBean> rxMyCollectList(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/myTokenRecordsList?policy")
    Observable<ReturnRegisterBean> rxMyTokenRecordsList(@Body RequestBody route);

    // 和首页相关接口
    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/home/recommendList?policy")
    Observable<ReturnRegisterBean> rxHomeRecommendList(@Body RequestBody route);
    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/home/followList?policy")
    Observable<ReturnRegisterBean> rxHomeFollowList(@Body RequestBody route);


}
