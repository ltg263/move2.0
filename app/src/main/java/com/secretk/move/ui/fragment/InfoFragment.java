package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

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
import com.secretk.move.ui.adapter.InfoFragmentAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.CustomViewPager;
import com.secretk.move.view.LoadingDialog;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/7/2 18:34
 * 邮箱；ltg263@126.com
 * 描述：我的消息
 */

public class InfoFragment extends LazyFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    private InfoFragmentAdapter adapter;
    int pageIndex = 0 ;
    private List<MessageBean.DataBean.MessagesBean.RowsBean> list;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_info;
    }

    private ArrayList<String> ImageAdList = new ArrayList<>();
    @Override
    public void initViews() {
        initRefresh();
        setVerticalManager(recycler);
        adapter = new InfoFragmentAdapter(getActivity());
        recycler.setAdapter(adapter);
        loadingDialog = new LoadingDialog(getActivity());
        ImageAdList.add("https://pic.qufen.top/20180629212800717.jpg");
        ImageAdList.add("https://pic.qufen.top/20180628162058703554");
        ImageAdList.add("https://pic.qufen.top/20180628162058545552");
        ImageAdList.add("https://pic.qufen.top/20180628162058375550");
        viewpager.setImageResources(ImageAdList, mAdCycleViewListener);
    }
    private CustomViewPager.ImageCycleViewListener mAdCycleViewListener = new CustomViewPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            LogUtil.w("-----------------------------------");
            // TODO 单击图片处理事件
//            int curPos = viewpager.getCurPos();
//            Intent intent = new Intent(getActivity(), WebViewActivity.class);
//            intent.putExtra("url", listvp.get(curPos).getAccessURL());
//            startActivity(intent);
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            // TODO 加载显示图片
            imageView.setTag(null);
            GlideUtils.loadSideMaxImage(getActivity(),imageView,imageURL);
        }
    };


    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        loadData();
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


    @OnClick({R.id.iv_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_status:

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
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                list = str.getData().getMessages().getRows();
                if (pageIndex > 2) {
                    adapter.addData(list);
                } else {
                    adapter.setData(list);
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onError(String message) {

            }
        });
    }
}
