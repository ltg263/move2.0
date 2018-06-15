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
import com.secretk.move.ui.adapter.MessageFragmentRecyclerAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
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
                detaliMessage(lists.get(position).getMessageId(), context);
            }
        });
    }

    private void detaliMessage(int messageId, Context context) {
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
                .url(Constants.MESSAGE_DETAIL)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                ivState.setVisibility(View.GONE);
//                String[] key = {"message"};
//                String[] values = {str};
//                IntentUtil.startActivity(MessageDesActivity.class, key, values);
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
