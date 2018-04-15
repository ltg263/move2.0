package com.secretk.move.http;


import com.secretk.move.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HttpMethods {

    @GET("5ac682e2548b7a17968537bb?api_token=38fb1bef224fa94b0fbe3ab82a4f4196&type=android")
    Observable<VersionBean> getVersionInfos();
}
