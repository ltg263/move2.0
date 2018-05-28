package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MessageBean;
import com.secretk.move.ui.adapter.MessageFragmentRecyclerAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/5.
 */

public class MessageFragment extends LazyFragment{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MessageFragmentRecyclerAdapter adapter;
    int pageIndex = 1;
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
    }

    @Override
    public void onFirstUserVisible() {
//        MESSAGE_LIST
        if(!NetUtil.isNetworkAvailable()){
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
                if (str.getData().getMessages().getCurPageNum() == str.getData().getMessages().getPageSize()) {
                    refreshLayout.setLoadmoreFinished(true);
                }
                list = str.getData().getMessages().getRows();
                if(pageIndex>2){
                    adapter.addData(list);
                }else{
                    adapter.setData(list);
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isLoading()) {
                    refreshLayout.finishLoadmore(true);
                }
            }
        });
    }
    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.setLoadmoreFinished(false);
                pageIndex=1;
                onFirstUserVisible();

            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                onFirstUserVisible();
            }
        });
    }
    @OnClick(R.id.tv_status)
    public void onViewClicked() {
    }
}
