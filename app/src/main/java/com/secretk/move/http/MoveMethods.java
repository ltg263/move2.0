package com.secretk.move.http;


import com.secretk.move.bean.returnbean.RegisterBean;
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
    Observable<RegisterBean> rxDemo(@Body RequestBody route);


    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/dynamicValidateCode/send?policy")
    Observable<RegisterBean> rxGetVerificationCode(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/register?policy")
    Observable<RegisterBean> rxRegister(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/login?policy")
    Observable<RegisterBean> rxLogin(@Body RequestBody route);
    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/forgetPassword?policy")
    Observable<RegisterBean> rxForgetPassword(@Body RequestBody route);
    //Todo   上传头像先空着

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/myFollowList?policy")
    Observable<RegisterBean> rxMyFollowList(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/myCollectList?policy")
    Observable<RegisterBean> rxMyCollectList(@Body RequestBody route);

    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/user/myTokenRecordsList?policy")
    Observable<RegisterBean> rxMyTokenRecordsList(@Body RequestBody route);

    // 和首页相关接口
    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/home/recommendList?policy")
    Observable<RegisterBean> rxHomeRecommendList(@Body RequestBody route);
    @Headers({"platform: 2","phoneModel: iphone6p"})
    @POST("kff/home/followList?policy")
    Observable<RegisterBean> rxHomeFollowList(@Body RequestBody route);


}
