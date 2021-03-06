package com.secretk.move.apiService;

import android.os.Build;
import android.widget.Toast;

import com.google.gson.Gson;
import com.secretk.move.MoveApplication;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者： litongge
 * 时间：  2017/2/26 11:51
 * 邮箱；ltg263@126.com
 */
public class RetrofitUtil {

    private static Retrofit mRetrofit;
    private static Gson gson;

    static {
        gson = new Gson();
        //初始化
        mRetrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .client(genericClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
                .addConverterFactory(ScalarsConverterFactory.create()) // 使用String作为数据转换器
                .addConverterFactory(GsonConverterFactory.create()) // 使用Gson作为数据转换器

                .build();
    }

    public static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("platform", "2")
                                .addHeader("phoneModel", Build.MODEL)
                                .addHeader("systemVersion", Build.VERSION.SDK)
                                .addHeader("appVersion", StringUtil.getVersionCode())
                                .addHeader("operator", "")
                                .addHeader("networkType", "wifi")
                                .addHeader("deviceId", "")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return httpClient;
    }

    /**
     * 通用的接口请求
     *
     * @param params
     * @param clz
     * @param callBack
     * @param <T>      对象数据
     */
    public static <T> void request(RxHttpParams params, final Class<T> clz, final HttpCallBackImpl<T> callBack) {
        if (params == null || clz == null) {
            return;
        }
        long time = System.currentTimeMillis();
//        params.addQuery("time", time+"");
        BaseApiService apiService = mRetrofit.create(BaseApiService.class);
        String url = params.getUrl();
        Call<ResponseBody> call;

        if (params.getParamsBody() != null) {
            call = apiService.post(url, params.getParamsBody());
        } else if (params.getPartParams().isEmpty() && params.getQueryParams().isEmpty()
                && params.getPart() == null) {
            if (params.getMethod() == RxHttpParams.HttpMethod.POST) {
                call = apiService.post(url);
            } else {
                call = apiService.get(url);
            }
        } else if (params.getPartParams().isEmpty() && params.getPart() == null) {
            if (params.getMethod() == RxHttpParams.HttpMethod.POST) {
                call = apiService.post(url, params.getQueryParams());
            } else {
                call = apiService.get(url, params.getQueryParams());
            }
        } else if (params.getQueryParams().isEmpty()) {
            if (params.getPartParams().isEmpty()) {
                call = apiService.postPart(url, params.getPart());
            } else {
                call = apiService.postPart(url, params.getPartParams(), params.getPart());
            }
        } else {
            call = apiService.post(url, params.getMultipartBody());
        }
        call.enqueue(new Callback() {
                         @Override
                         public void onResponse(Call call, retrofit2.Response response) {
                             ResponseBody body = (ResponseBody) response.body();
                             // InputStream is = body.byteStream();
                             LogUtil.w("************************************************************************************");
                             LogUtil.i("request " + response.raw().toString());
                             if (body != null) {
                                 String jsonStr = new String(read(body.byteStream()), Charset.forName("UTF-8"));
                                 if(!response.raw().toString().contains("api.coinmarketcap.com") && !response.raw().toString().contains("data.block.cc")){
                                     LogUtil.w("jsonStr:" + jsonStr);
                                     try {
                                         JSONObject jsonObject = new JSONObject(jsonStr);
                                         int code = jsonObject.getInt("code");
                                         String msg = jsonObject.getString("msg");
//                                     String data = jsonObject.getJSONObject("data").toString();
                                         if ((code != 0 || !msg.equals("Success"))) {
//                                         if(code==11004){
//                                             msg = "帐号或密码错误请重新登陆";
//                                             IntentUtil.startActivity(LoginHomeActivity.class);
//                                         }
                                             if(code!=500){
                                                 Toast.makeText(MoveApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
                                             }
                                             callBack.onError(msg);
                                             callBack.onFinish();
                                             return;
                                         }
                                     } catch (JSONException e) {
                                         e.printStackTrace();
                                     }
                                 }
                                 if (clz == null) {
                                     callBack.onFinish();
                                     callBack.onError(jsonStr);
                                     return;
                                 }
                                 T t;
                                 if (clz == String.class) {
                                     t = (T) jsonStr;
                                 } else {
                                     t = gson.fromJson(jsonStr, clz);
                                 }
                                 if (t == null) {
                                     ToastUtils.getInstance().show("实体类有误");
                                     callBack.onFinish();
                                     return;
                                 }
                                 if (callBack != null) {
                                     callBack.onFinish();
                                     callBack.onCompleted(t);
                                 }

                             } else {
                                if(!response.raw().toString().contains("api.coinmarketcap.com")
                                        && !response.raw().toString().contains("data.block.cc")){
                                    ToastUtils.getInstance().show("网络错误");
                                }
                                 if (callBack != null) {
                                     callBack.onFinish();
                                     callBack.onError(response.raw().toString());
                                 }
                             }
                         }

                         @Override
                         public void onFailure(Call call, Throwable t) {
                             if (callBack != null) {
                                 callBack.onFinish();
                                 callBack.onError(t.getMessage());
                             }
                         }
                     }

        );
    }

    private static byte[] read(InputStream inputStream) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
