package com.secretk.move.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.secretk.move.MoveApplication;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import org.json.JSONException;
import org.json.JSONObject;

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
     *  @param isFollow 是否已关注  true 关注
     * @param followType 1-关注项目;2-关注帖子；3-关注用户
     * @param followedId 关注类型为1，对应为projectId对应值，2 为postId对应值 3 为对应userId值
     */
    public static void addSaveFollow(final Boolean isFollow ,int followType, int followedId, final SaveFollowImpl follow){
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY,"");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("followType", followType);
            node.put("followedId", followedId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url ;
        if(isFollow){
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
                follow.finishFollow(str,isFollow);
            }
        });
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
        if(isPraise){
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
                follow.finishFollow(str,isPraise);
            }
        });
    }


    /**
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
                follow.finishFollow(str,!isLove);
            }
        });

    }
    public static abstract class SaveFollowImpl{
        /**
         * status :true 未赞
         *      false：已赞
         */
        public abstract void finishFollow(String str,boolean status);
    }
}
