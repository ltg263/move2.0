package com.secretk.move.apiService;

import android.content.Context;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.secretk.move.ui.activity.LoginActivity;
import com.secretk.move.ui.activity.MainActivity;
import com.secretk.move.ui.activity.SplashScreenActivity;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： litongge
 * 时间：  2018/7/12 10:20
 * 邮箱；ltg263@126.com
 * 描述：接收CID、透传消息以及其他推送服务事件
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调,
 * 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class MoveIntentService extends GTIntentService {

    public MoveIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        LogUtil.d("onReceiveMessageData -> ");
        //链接类型:0-完整版专业评测，1-自定义评测，2-文章，3-打假，4-单项评测，5关注
        String payload = new String(msg.getPayload());
        LogUtil.d("onReceiveMessageData -> payload:"+payload);
        if (StringUtil.isNotBlank(payload)) {
            try {
                JSONObject object = new JSONObject(payload);
               String  strType = object.getString("type");
               String strId =  object.getString("id");
               if(StringUtil.isExistMainActivity(context, MainActivity.class)){
                   if(!SharedUtils.getLoginZt() || StringUtil.isBlank(SharedUtils.getToken())){
                       IntentUtil.startActivity(LoginActivity.class);
                       return;
                   }
                   if(StringUtil.isNotBlank(strType) && StringUtil.isNotBlank(strId)){
                       int type = Integer.valueOf(strType);
                       if(type==0 || type ==1 || type==4){
                           type=1;
                       }else if(type==3){
                           type=2;
                       }else if(type==2){
                           type=3;
                       }else if(type==5){
                           IntentUtil.startHomeActivity(Integer.valueOf(strId));
                           return;
                       }else{
                           return;
                       }
                       IntentUtil.go2DetailsByType(type, strId);
                   }
                   return;
               }
                String key[] = {"type","postId"};
                String values[] = {strType,strId};
                IntentUtil.startActivity(SplashScreenActivity.class, key, values);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

        @Override
        public void onReceiveClientId(Context context, String clientId) {
            SharedUtils.singleton().put("clientId",clientId);
            LogUtil.d("onReceiveClientId -> " + "clientId = " + clientId);
        }

        @Override
        public void onReceiveOnlineState(Context context, boolean online) {
            LogUtil.d("onReceiveOnlineState -> " + "online = " + online);
        }

        @Override
        public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
            LogUtil.d("onReceiveCommandResult -> " + "cmdMessage = " + cmdMessage.toString());
        }

        @Override
        public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
            LogUtil.d("onNotificationMessageArrived -> " + "msg = " + msg.getContent());
        }

        @Override
        public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
            LogUtil.d("onNotificationMessageClicked -> " + "msg = " + msg.getContent());
        }
    }

