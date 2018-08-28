package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.ui.adapter.MessageFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/7/2 15:43
 * 邮箱；ltg263@126.com
 * 描述：我的消息
 */

public class MineMessageActivity extends BaseActivity {
    @BindView(R.id.rv_collect)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    private MessageFragmentRecyclerAdapter adapter;
    int pageIndex = 1 ;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("消息");
        mMenuInfos.add(0, new MenuInfo(R.string.mine_message_sate, getString(R.string.mine_message_sate), 0));
        return mHeadView;
    }
    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_collect;
    }

    @Override
    protected void OnToolbarRightListener() {
        detaliMessage(0);
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                loadData();

            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                loadData();
            }
        });
    }
    @Override
    protected void initData() {
        loadData();
    }

    @OnClick({R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if(tvSubmit.getText().toString().equals(getString(R.string.not_refresh))){
                    pageIndex = 1;
                    loadData();
                }else{
                    IntentUtil.startActivity(LoginHomeActivity.class);
                }
                break;
        }
    }


    private void loadData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", 15);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MESSAGE_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MessageBean.class, new HttpCallBackImpl<MessageBean>() {
            @Override
            public void onCompleted(MessageBean bean) {
                MessageBean.DataBean.MessagesBean detailsBean = bean.getData().getMessages();
                if(bean.getData().getMessages()==null){
                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (detailsBean.getCurPageNum() == detailsBean.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    refreshLayout.setEnableLoadMoreWhenContentNotFull(true);
                }
                if (detailsBean.getRows() == null || detailsBean.getRows().size() == 0) {
                    findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    return;
                }
                if (pageIndex > 2) {
                    adapter.addData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }
    private void detaliMessage(final int messageId) {
        if(!NetUtil.isNetworkAvailable()){
            ToastUtils.getInstance().show(getString(R.string.network_error));
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
        RetrofitUtil.request(params, MessageBean.class, new HttpCallBackImpl<MessageBean>() {
            @Override
            public void onCompleted(MessageBean str) {
                if(messageId==0){
                    pageIndex=1;
                }
                refreshLayout.setNoMoreData(false);
                loadData();
            }

            @Override
            public void onFinish() {

            }
        });
    }



    @Override
    protected void initUI(Bundle savedInstanceState) {
        initRefresh();
        setVerticalManager(recycler);
        adapter = new MessageFragmentRecyclerAdapter(this);
        recycler.setAdapter(adapter);
        loadingDialog.show();
        rlTopTheme.setVisibility(View.VISIBLE);
        tvIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_not_message));
        tvName.setText(getResources().getString(R.string.not_message));
        tvSubmit.setText(getResources().getString(R.string.not_refresh));
    }
}

