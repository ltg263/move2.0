package com.secretk.move.ui.fragment;

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
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MessageFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/5.
 */

public class MessageFragment extends LazyFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
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
    int pageIndex = 0 ;
    private List<MessageBean.DataBean.MessagesBean.RowsBean> list;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_message;
    }

    @Override
    public void initViews() {
        initRefresh();
        setVerticalManager(recycler);
        adapter = new MessageFragmentRecyclerAdapter(getActivity());
        recycler.setAdapter(adapter);
        rlTopTheme.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.GONE);
        tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_go_login));
        tvName.setText(getActivity().getResources().getString(R.string.not_message));
        tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
        loadingDialog = new LoadingDialog(getActivity());
//        loadingDialog.show();
    }

    @Override
    public void onFirstUserVisible() {
    }

    private void initRefresh() {
//        loadingDialog = new LoadingDialog(getActivity());
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.setNoMoreData(false);
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


    @OnClick({R.id.tv_status, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_status:
                detaliMessage(0);
                break;
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
    @Override
    public void onResume() {
        super.onResume();
        if(list==null || !isLoginZt) {
            pageIndex = 1;
            loadData();
        }
    }

    private void loadData() {
        if(!isLoginZt){
            refreshLayout.setVisibility(View.GONE);
            convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
            rlTopTheme.setVisibility(View.VISIBLE);
            tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_go_login));
            tvName.setText("您尚未登陆,无法预览消息内容");
            tvSubmit.setText(getString(R.string.go_login));
            tvSubmit.setVisibility(View.VISIBLE);
            tvStatus.setVisibility(View.GONE);
            list=null;
            return;
        }
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
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
            public void onCompleted(MessageBean str) {
                if (str.getData().getMessages() == null && pageIndex == 2) {
                    convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    tvIcon.setImageDrawable(getActivity().getResources().getDrawable(R.drawable.ic_not_message));
                    tvStatus.setVisibility(View.GONE);
                    tvName.setText(getActivity().getResources().getString(R.string.not_message));
                    tvSubmit.setText(getActivity().getResources().getString(R.string.not_refresh));
                    return;
                }
                if (str.getData().getMessages().getCurPageNum() == str.getData().getMessages().getPageSize()) {
                    refreshLayout.setNoMoreData(true);
                }
                list = str.getData().getMessages().getRows();
                if (pageIndex > 2) {
                    convertView.findViewById(R.id.no_data).setVisibility(View.GONE);
                    refreshLayout.setVisibility(View.VISIBLE);
                    tvStatus.setVisibility(View.VISIBLE);
                    adapter.addData(list);
                } else {
                    adapter.setData(list);
                    if(list.size()>0){
                        convertView.findViewById(R.id.no_data).setVisibility(View.GONE);
                        refreshLayout.setVisibility(View.VISIBLE);
                        tvStatus.setVisibility(View.VISIBLE);
                    }else{
                        list=null;
                        convertView.findViewById(R.id.no_data).setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFinish() {
//                if (refreshLayout.isRefreshing()) {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
//                if (refreshLayout.isLoading()) {
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore(true);
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
                loadData();
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
