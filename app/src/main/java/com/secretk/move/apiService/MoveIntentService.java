package com.secretk.move.apiService;

import android.content.Context;

import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.secretk.move.utils.LogUtil;

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
        //获取后台推送通知里面的透传消息
        String payload = new String(msg.getPayload());
        LogUtil.w("payload:"+payload);
        if (payload != null) {
//            Intent intent = new Intent(context, RecommendActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("", payload);
//            //***在这里需要向跳转的activity中传递参数
//            startActivity(intent);
        }
    }

        @Override
        public void onReceiveClientId(Context context, String clientid) {
            LogUtil.w("onReceiveClientId -> " + "clientid = " + clientid);
        }

        @Override
        public void onReceiveOnlineState(Context context, boolean online) {
        }

        @Override
        public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        }

        @Override
        public void onNotificationMessageArrived(Context context, GTNotificationMessage msg) {
        }

        @Override
        public void onNotificationMessageClicked(Context context, GTNotificationMessage msg) {
        }
    }

