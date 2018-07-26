package com.secretk.move.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.BaseManager;
import com.secretk.move.baseManager.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NetUtil {
    public static boolean isNetworkConnected() {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) MoveApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        return false;
    }

    public static boolean isWifiConnected() {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) MoveApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        return false;
    }

    public static boolean isMobileConnected() {
            ConnectivityManager mConnectivityManager = (ConnectivityManager)MoveApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        return false;
    }

    public static int getConnectedType() {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) MoveApplication.getContext()
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        return -1;
    }

    /**
     * 判断网络是否可用
     * @return
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) MoveApplication.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     *    // 0 显示 +关注； 1--显示 已关注 按钮 ；2 不显示按钮
     *  @param tvSaveFollow 已关注  TextView
     * @param followType 1-关注项目;2-关注帖子；3-关注用户
     * @param followedId 关注类型为1，对应为projectId对应值，2 为postId对应值 3 为对应userId值
     */
    public static void addSaveFollow(TextView tvSaveFollow , int followType, int followedId, final SaveFollowImp follow){
        String statusStr = tvSaveFollow.getText().toString().trim();
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        tvSaveFollow.setSelected(!statusStr.equals(BaseManager.app.getString(R.string.follow_status_1)));
        int statusCode=0;
        if(statusStr.equals(BaseManager.app.getString(R.string.follow_status_1))){
            statusCode=1;
            tvSaveFollow.setText(BaseManager.app.getString(R.string.follow_status_0));
        }else{
            tvSaveFollow.setText(BaseManager.app.getString(R.string.follow_status_1));
        }

        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("followType", followType);
            node.put("followedId", followedId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url ;
        if(statusCode==1){
            url=Constants.CANCEL_FOLLOW;
        }else{
            url=Constants.SAVE_FOLLOW;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if (obj.getJSONObject("data")!=null) {
                        int followStatus = obj.getJSONObject("data").getInt("followStatus");
                        if(followStatus==1){
                            follow.finishFollow(BaseManager.app.getString(R.string.follow_status_1));
                        }else{
                            follow.finishFollow(BaseManager.app.getString(R.string.follow_status_0));
                        }
                    }else{
                        follow.finishFollow(Constants.FOLLOW_ERROR);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                follow.finishFollow(Constants.FOLLOW_ERROR);
            }
        });
    }

    public static abstract class SaveFollowImp{
        /**
         * status :true 未赞
         *      false：已赞
         */
        public abstract void finishFollow(String str);
    }

    /**
     * 点赞或取消 帖子 包括 评测 ，文章
     * @param isPraise
     * @param postId
     * @param follow
     */
    public static void setPraise(final Boolean isPraise ,int postId,final SaveFollowImpl follow){
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", postId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url ;
        if(!isPraise){
            url=Constants.CANCEL_POST_PRAISE;
        }else{
            url=Constants.SAVE_POST_PRAISE;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if(obj.getJSONObject("data")!=null){
                        int praiseNum = obj.getJSONObject("data").getInt("praiseNum");
                        boolean isSendPraiseToken = obj.getJSONObject("data").getBoolean("isSendPraiseToken");
                        double retrueDzan=0;
                        if(isSendPraiseToken){
                            retrueDzan = obj.getJSONObject("data").getDouble("retrueDzan");
                        }
                        follow.finishFollow(String.valueOf(praiseNum),!isPraise,retrueDzan);
                    }else{
                        follow.finishFollow(Constants.PRAISE_ERROR,isPraise,0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                follow.finishFollow(Constants.PRAISE_ERROR,isPraise,0);
            }
        });
    }


    /**
     * 对评论内容点赞
     *  @param isLove 是否已赞 true 赞
     * @param commentsId
     * praiseStatus":0,//点赞状态：0-未点赞；1-已点赞，2-未登录用户不显示 数字
     */
    public static void addCommentsPraise(final Boolean isLove , int commentsId, final SaveFollowImpl follow){
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("commentsId", commentsId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url ;
        if(!isLove){
            url=Constants.CANCEL_COMMENTS_PRAISE;
        }else{
            url=Constants.SAVE_COMMENTS_PRAISE;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if( obj.getJSONObject("data")!=null){
                        int praiseNum = obj.getJSONObject("data").getInt("praiseNum");
                        follow.finishFollow(String.valueOf(praiseNum),!isLove,0);
                    }else{
                        follow.finishFollow(Constants.PRAISE_ERROR,isLove,0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(String message) {
                follow.finishFollow(Constants.PRAISE_ERROR,isLove,0);
            }
        });

    }
    public static abstract class SaveFollowImpl{
        /**
         * status :true 未赞
         *      false：已赞
         *
         */
        public abstract void finishFollow(String str,boolean status,double find);
    }

    /**
     *
     * @param isCollect
     * @param postId
     * @param collect
     */
    public static void saveCollect(Boolean isCollect ,int postId, final SaveCollectImp collect){
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", postId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url ;
        if(isCollect){
            url=Constants.CANCEL_COLLECT;
        }else{
            url=Constants.SAVE_COLLECT;
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(url)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    //0-未收藏，1-已收藏，数字
                    if(obj.getJSONObject("data")!=null){
                        int followStatus = obj.getJSONObject("data").getInt("collectStatus");
                        collect.finishCollect("",followStatus==0?true:false);
                    }else {
                        collect.finishCollect(Constants.COLLECT_ERROR,true);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                collect.finishCollect(Constants.COLLECT_ERROR,true);
            }
        });
    }

    public static abstract class SaveCollectImp{
        public abstract void finishCollect(String code,boolean status);
    }

    /**
     *
     */
    public static void commendation(int postId,int receiveUserId,double amount,int projectId, final SaveCommendationImp collect){
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("postId", postId);
            node.put("receiveUserId", receiveUserId);
            node.put("amount", amount);
            node.put("projectId", projectId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.COMMENDATION)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if(obj.getJSONObject("data")!=null){
                        double commendationNum = obj.getJSONObject("data").getDouble("commendationNum");
                        int donateNum = obj.getJSONObject("data").getInt("donateNum");
                        collect.finishCommendation(String.valueOf(Math.round(commendationNum)),String.valueOf(donateNum),true);
                    }else{
                        collect.finishCommendation("","",false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                collect.finishCommendation("","",false);
            }
        });
    }
    public static abstract class SaveCommendationImp{
        public abstract void finishCommendation(String commendationNum,String donateNum,boolean status);
    }

    /**
     * 能否点赞
     * @return
     */
    public static boolean isPraise(int currentUserId,int loginUserId) {
        if(currentUserId==loginUserId){
            ToastUtils.getInstance().show("不能给自己点赞");
            return false;
        }
        return true;
    }
    /**
     * 能否赞赏
     * @return
     */
    public static boolean isSponsor(int currentUserId,int loginUserId) {
        if(currentUserId==loginUserId){
            ToastUtils.getInstance().show("不能给自己赞助");
            return false;
        }
        return true;
    }


    /**
     * 获取七牛Token
     */
    public static void getQiniuToken(final SaveCommendationImp collect){
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SEND_TOKEN)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if(obj.getJSONObject("data")!=null){
                        String upToken = obj.getJSONObject("data").getString("upToken");
                        String userId = obj.getJSONObject("data").getString("uid");
                        collect.finishCommendation(userId,upToken,true);
                    }else{
                        collect.finishCommendation("","",false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                collect.finishCommendation("","",false);
            }
        });
    }

    /**
     * 实时行情价格
     * @param collect
     * https://api.coinmarketcap.com/v2/ticker/1/?convert=CNY
     */
    public static void getCoinmarketcapTicker(String id,String name,final SaveCommendationImp collect){
        RxHttpParams params = new RxHttpParams.Build()
//                .url(Constants.SEND_TOKEN)
                .url("https://api.coinmarketcap.com/v2/ticker/"+id+"/?convert="+name+"")
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    if(obj.getJSONObject("data")!=null){
                        JSONObject cny = obj.getJSONObject("data").getJSONObject("quotes").getJSONObject("CNY");
                        double price = cny.getDouble("price");
                        double percent_change_1h = cny.getDouble("percent_change_1h");
                        collect.finishCommendation("￥"+String.format("%.2f", price),String.format("%.2f", percent_change_1h),true);
                    }else{
                        collect.finishCommendation("","",false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String message) {
                collect.finishCommendation("","",false);
            }
        });
    }

    //当前时间()精确毫秒)+用户id+图片位数+图片后缀
//     * 身份证图片idcard  帖子图片 posts   用户头像avatars

    public static String getQiniuImgName(String type,String userId,int index){
        long time = System.currentTimeMillis();
        return type+getTime(time)+userId+index+".png";
    }
    /**
     * 毫秒数转日期
     */
    public static String getTime(long seconds) {
        Date d = new Date(seconds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(d).toString();
    }

    public static void sendQiniuImgUrl(File imgFile, String qiToken, String imgPath, final QiniuImgUpload upload) {
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                //.recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
                //.recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                //.zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = new UploadManager(config);
        //data = <File对象、或 文件路径、或 字节数组>
        //String key = <指定七牛服务上的文件名，或 null>;
        //String token = <从服务端获取>;
        uploadManager.put(imgFile,imgPath, qiToken,
                new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo info, JSONObject res) {
                        //res包含hash、key等信息，具体字段取决于上传策略的设置
                        LogUtil.w("七牛上传图片结果：key："+key+";/n info"+info+"/n res:"+res);
                        if (info.isOK()) {
                            upload.uploadStatus(Constants.QUNIU_IMG_RUL+key,true);
                        } else {
                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
                            upload.uploadStatus("",false);
                        }
                    }
                }, new UploadOptions(null, null, false,
                        new UpProgressHandler(){
                            public void progress(String name, double percent){
                                upload.uploadLoading(name,percent);
                            }
                        }, null));
    }

    public static abstract class QiniuImgUpload{
        /**
         * status :true 未赞
         *      false：已赞
         */
        public abstract void uploadStatus(String str,boolean status);
        /**
         * status :true 未赞
         *      false：已赞
         */
        public void uploadLoading(String name,double status){};
    }
}
