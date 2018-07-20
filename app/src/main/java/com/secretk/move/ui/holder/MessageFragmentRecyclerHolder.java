package com.secretk.move.ui.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.RecyclerViewBaseHolder;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.ui.activity.MineAssetDetailsActivity;
import com.secretk.move.ui.adapter.MessageFragmentRecyclerAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/14.
 */

public class MessageFragmentRecyclerHolder extends RecyclerViewBaseHolder {
    @BindView(R.id.img)
    public ImageView img;
    @BindView(R.id.iv_model_type)
    public ImageView ivModelType;
    @BindView(R.id.iv_state)
    public ImageView ivState;
    @BindView(R.id.tvName)
    public TextView tvName;
    @BindView(R.id.tvLastContent)
    public TextView tvLastContent;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.ll_detail)
    LinearLayout llDetail;

    public MessageFragmentRecyclerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void refresh(final Context context, final int position, final List<MessageBean.DataBean.MessagesBean.RowsBean> lists,
                        final MessageFragmentRecyclerAdapter adapter) {
        MessageBean.DataBean.MessagesBean.RowsBean currenBean = lists.get(position);
        GlideUtils.loadCircleUserUrl(context, img, Constants.BASE_IMG_URL + currenBean.getSenderUserIcon());
        StringUtil.getUserType(currenBean.getUserType(),ivModelType);
        // 阅读状态 ，数字，状态：1-未读；2-已读
        if (currenBean.getState() == 1) {
            ivState.setVisibility(View.VISIBLE);
        } else {
            ivState.setVisibility(View.GONE);
        }
        tvName.setText(currenBean.getTitle());
        tvLastContent.setText(currenBean.getContent());
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMessage(lists.get(position).getMessageId(), context);
                lists.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageBean.DataBean.MessagesBean.RowsBean message = lists.get(position);
                if(ivState.getVisibility()== View.VISIBLE){
                    detaliMessage(lists.get(position), context);
                }
                //`type 消息类型：1-关注；2-点赞；3-评论；4-赞赏；5-评论被回复；6-上榜单；7-奖励token'
                int type = message.getType();
                if(type==1){
                    IntentUtil.startHomeActivity(message.getSenderUserId());
                }else if(type==2 || type==3 || type==4 || type==5){
                    int postType = message.getPostType();
                    IntentUtil.go2DetailsByType(postType,String.valueOf(message.getPostId()));
                }else if(type==6){
//                    IntentUtil.startHomeActivity(message.getSenderUserId());
                }else if(type==7){
                    IntentUtil.startActivity(MineAssetDetailsActivity.class);
                }
            }
        });
    }

    private void detaliMessage(final MessageBean.DataBean.MessagesBean.RowsBean message, Context context) {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(context.getString(R.string.network_error));
            return;
        }
        final String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("messageId", message.getMessageId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MESSAGE_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                ivState.setVisibility(View.GONE);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void deleteMessage(int messageId, Context context) {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(context.getString(R.string.network_error));
            return;
        }
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("messageId", messageId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.DELETE_MESSAGE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {

            }
        });
    }
}
