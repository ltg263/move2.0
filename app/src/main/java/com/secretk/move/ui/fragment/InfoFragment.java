package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
import com.secretk.move.bean.InfoBean;
import com.secretk.move.ui.adapter.InfoFragmentAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
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
    int pageIndex = 1 ;
    private List<InfoBean.DataBeanX.DataBean.RowsBean> rows;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_info;
    }

    private ArrayList<String> imageAdList;
    @Override
    public void initViews() {
        initRefresh();
        setVerticalManager(recycler);
        adapter = new InfoFragmentAdapter(getActivity());
        recycler.setAdapter(adapter);
        loadingDialog = new LoadingDialog(getActivity());
    }

    private CustomViewPager.ImageCycleViewListener mAdCycleViewListener = new CustomViewPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //  ：0-完整版专业评测，1-自定义评测，2-文章，3-打假，4-单项评测
            if(rows==null || rows.size()==0){
                return;
            }
            InfoBean.DataBeanX.DataBean.RowsBean row = rows.get(viewpager.getCurPos());
            int type = row.getType();
            int postId = row.getArticleId();
            if(row.getIsCheckDetails()==1 || row.getArticleId()==0){
                return;
            }
            if(type==0 || type ==1 || type==4){
                type=1;
            }else if(type==3){
                type=2;
            }else if(type==2){
                type=3;
            }else{
                ToastUtils.getInstance().show("类型出错");
                return;
            }
            IntentUtil.go2DetailsByType(type, String.valueOf(postId));
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            // TODO 加载显示图片
            imageView.setTag(null);
            Glide.with(getActivity()).load(imageURL).into(imageView);
        }
    };


    @Override
    public void onFirstUserVisible() {
        loadingDialog.show();
        loadData();
    }

    private void initRefresh() {
        refreshLayout.setEnableRefresh(false);
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
                getNewsFlashPageList();
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
        //轮播图
        getNewsFlashImgList();
        getNewsFlashPageList();
    }

    private void getNewsFlashImgList() {
        JSONObject node = new JSONObject();
        try {
            node.put("pageIndex", 1);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_NEWS_FLASH_IMG_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, InfoBean.class, new HttpCallBackImpl<InfoBean>() {
            @Override
            public void onCompleted(InfoBean bean) {
                InfoBean.DataBeanX.DataBean data = bean.getData().getData();
                if(data==null){
                    return;
                }
                rows = bean.getData().getData().getRows();
                if(rows!=null && rows.size()>0){
                    imageAdList = new ArrayList<>();
                    for(int i = 0;i<rows.size();i++){
                        imageAdList.add(rows.get(i).getImgPath());
                    }
                    viewpager.setImageResources(imageAdList, mAdCycleViewListener);
                }
            }
        });
    }
    private void getNewsFlashPageList() {
        JSONObject node = new JSONObject();
        try {
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_NEWS_FLASH_PAGE_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, InfoBean.class, new HttpCallBackImpl<InfoBean>() {
            @Override
            public void onCompleted(InfoBean str) {
                InfoBean.DataBeanX.DataBean data = str.getData().getData();
                if(data==null){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                if (data.getCurPageNum() == data.getPageSize()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                List<InfoBean.DataBeanX.DataBean.RowsBean> list = data.getRows();
                if(list!=null && list.size()>0){
                    if (pageIndex > 2) {
                        adapter.addData(list);
                    } else {
                        adapter.setData(list);
                    }
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
                loadingDialog.dismiss();
            }
        });
    }
}
