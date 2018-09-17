package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.secretk.move.bean.FindKwBean;
import com.secretk.move.bean.FindXhBean;
import com.secretk.move.bean.HotProjectAndHotUserBean;
import com.secretk.move.bean.InfoBean;
import com.secretk.move.ui.activity.FindRankingListActivity;
import com.secretk.move.ui.activity.FindRingRequiredActivity;
import com.secretk.move.ui.activity.FindWkActivity;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.activity.RewardSquareActivity;
import com.secretk.move.ui.adapter.FindFragmentAdapter;
import com.secretk.move.ui.adapter.FindFragmentXhAdapter;
import com.secretk.move.ui.adapter.FindNewAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
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
 * 描述：主页话题
 */

public class FindFragment extends LazyFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.recycler_jdwk)
    RecyclerView recyclerJdwk;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.iv_head_img)
    ImageView ivHeadImg;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.home_find_1)
    LinearLayout homeFind1;
    @BindView(R.id.home_find_2)
    LinearLayout homeFind2;
    @BindView(R.id.home_find_3)
    LinearLayout homeFind3;
    @BindView(R.id.home_find_4)
    LinearLayout homeFind4;
    @BindView(R.id.ll_jdwk)
    LinearLayout ll_jdwk;
    @BindView(R.id.rv_new_pro)
    RecyclerView rvNewPro;
    @BindView(R.id.rv_new_user)
    RecyclerView rvNewUser;
    @BindView(R.id.tv_dpwk)
    TextView tvDpwk;
    @BindView(R.id.tv_jdwk)
    TextView tvJdwk;
    int pageIndex = 1;
    int pageIndexXh = 1;
    private List<InfoBean.DataBeanX.DataBean.RowsBean> rows;
    private FindNewAdapter adapterP;
    private FindNewAdapter adapterU;
    private FindFragmentAdapter adapter;
    private FindFragmentXhAdapter adapterXh;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_find;
    }

    private ArrayList<String> imageAdList;

    @Override
    public void initViews() {
        initRefresh();
        setVerticalManager(recycler);
        setVerticalManager(recyclerJdwk);
        setHorizontalManager(rvNewUser);
        setHorizontalManager(rvNewPro);
        adapterP = new FindNewAdapter(getActivity(),1);
        rvNewPro.setAdapter(adapterP);
        adapterU = new FindNewAdapter(getActivity(),2);
        rvNewUser.setAdapter(adapterU);
        adapter = new FindFragmentAdapter(getActivity());
        recycler.setAdapter(adapter);
        adapterXh = new FindFragmentXhAdapter(getActivity());
        recyclerJdwk.setAdapter(adapterXh);
        loadingDialog = new LoadingDialog(getActivity());
        viewpager.setZx();
    }

    private CustomViewPager.ImageCycleViewListener mAdCycleViewListener = new CustomViewPager.ImageCycleViewListener() {
        @Override
        public void onImageClick(int position, View imageView) {
            //  ：0-完整版专业评测，1-自定义评测，2-文章，3-打假，4-单项评测
            if (rows == null || rows.size() == 0) {
                return;
            }

            InfoBean.DataBeanX.DataBean.RowsBean row = rows.get(viewpager.getCurPos(1));
            int type = row.getType();

            if (type == 5 && StringUtil.isNotBlank(row.getOutUrl())) {
                IntentUtil.startWebViewActivity(row.getOutUrl(), getString(R.string.app_name));
                return;
            }
            int postId = row.getArticleId();
            if (row.getIsCheckDetails() == 1 || row.getArticleId() == 0) {
                return;
            }
            if (type == 0 || type == 1 || type == 4) {
                type = 1;
            } else if (type == 3) {
                type = 2;
            } else if (type == 2) {
                type = 3;
            } else {
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
//        refreshLayout.setEnableRefresh(false);
//        refreshLayout.setEnableLoadMore(false);
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData();

            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if(recycler.getVisibility()==View.VISIBLE){
                    getMiningActivityPageList();
                }else{
                    getMiningActivityXhPageList();
                }
            }
        });
    }



    private void loadData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        pageIndex=1;
        pageIndexXh=1;
        //轮播图
        getNewsFlashImgList();
        //发现页面取热门项目，活跃用户
        getHotProjectAndHotUser();
        //挖矿列表页
        adapter.clearData();
        adapterXh.clearData();

        tvDpwk.setTextColor(getResources().getColor(R.color.app_background));
        tvJdwk.setTextColor(getResources().getColor(R.color.title_gray));
        recycler.setVisibility(View.VISIBLE);
        recyclerJdwk.setVisibility(View.GONE);
        getMiningActivityPageList();
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
                .url(Constants.GET_NEWS_FLASH_IMG_LIST_FOR_FOUND)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, InfoBean.class, new HttpCallBackImpl<InfoBean>() {
            @Override
            public void onCompleted(InfoBean bean) {
                InfoBean.DataBeanX.DataBean data = bean.getData().getData();
                if (data == null) {
                    return;
                }
                rows = bean.getData().getData().getRows();
                if (rows != null && rows.size() > 0) {
                    imageAdList = new ArrayList<>();
                    for (int i = 0; i < rows.size(); i++) {
                        imageAdList.add(rows.get(i).getImgPath());
                    }
                    if(imageAdList.size()>1){
                        viewpager.setVisibility(View.VISIBLE);
                        ivHeadImg.setVisibility(View.GONE);
                        viewpager.setImageResources(imageAdList, mAdCycleViewListener);
                    }else{
                        viewpager.setVisibility(View.GONE);
                        ivHeadImg.setVisibility(View.VISIBLE);
                        GlideUtils.loadSideMaxImage(getActivity(),ivHeadImg,imageAdList.get(0));
                    }
                }
            }
        });
    }
    boolean isNotDataDpwk = true;
    private void getMiningActivityPageList() {
        JSONObject node = new JSONObject();
        try {
            //0-进行中，1-已结束
            node.put("state", 0);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_MINING_ACTIVITY_PAGE_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, FindKwBean.class, new HttpCallBackImpl<FindKwBean>() {
            @Override
            public void onCompleted(FindKwBean str) {
                FindKwBean.DataBeanX.DataBean data = str.getData().getData();
                if (data == null) {
                    isNotDataDpwk = false;
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                if (data.getCurPageNum() == data.getPageSize()) {
                    isNotDataDpwk = false;
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                List<FindKwBean.DataBeanX.DataBean.RowsBean> rowsBeans = data.getRows();
                if (rowsBeans != null && rowsBeans.size() > 0) {
                    if (pageIndex > 2) {
                        adapter.addData(rowsBeans);
                    } else {
                        adapter.setData(rowsBeans);
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
    boolean isNotDataJdwk = true;
    private void getMiningActivityXhPageList() {
        JSONObject node = new JSONObject();
        try {
            //0-进行中，1-已结束
            node.put("pageIndex", pageIndexXh++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_REWARD_ACTIVITY_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, FindXhBean.class, new HttpCallBackImpl<FindXhBean>() {
            @Override
            public void onCompleted(FindXhBean str) {
                FindXhBean.DataBean data = str.getData();
                if (data == null) {
                    isNotDataJdwk = false;
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                if (data.getCurPageNum() == data.getPageSize()) {
                    isNotDataJdwk = false;
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                List<FindXhBean.DataBean.RowsBean> rowsBeans = data.getRows();
                if (rowsBeans != null && rowsBeans.size() > 0) {
                    if (pageIndexXh > 2) {
                        adapterXh.addData(rowsBeans);
                    } else {
                        adapterXh.setData(rowsBeans);
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

    @OnClick({R.id.home_find_1, R.id.home_find_2, R.id.home_find_3,R.id.home_find_4
    ,R.id.ll_dpwk,R.id.ll_jdwk})
    public void onViewClicked(View view) {
        if(!SharedUtils.getLoginZt() || StringUtil.isBlank(SharedUtils.getToken())){
            IntentUtil.startActivity(LoginHomeActivity.class);
            return;
        }
        switch (view.getId()) {
            case R.id.home_find_1:
                IntentUtil.startActivity(FindWkActivity.class);
                break;
            case R.id.home_find_2:
                IntentUtil.startActivity(FindRankingListActivity.class);
                break;
            case R.id.home_find_3:
                IntentUtil.startActivity(FindRingRequiredActivity.class);
                break;
            case R.id.home_find_4:
                IntentUtil.startActivity(RewardSquareActivity.class);
                break;
            case R.id.ll_dpwk:
                if(!isNotDataDpwk){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    refreshLayout.setNoMoreData(false);
                }
                tvDpwk.setTextColor(getResources().getColor(R.color.app_background));
                tvJdwk.setTextColor(getResources().getColor(R.color.title_gray));
                recycler.setVisibility(View.VISIBLE);
                recyclerJdwk.setVisibility(View.GONE);
                if(adapter.getItemCount()==0){
                    pageIndex=1;
                    getMiningActivityPageList();
                }
                rcv.scrollTo(0, llTop.getTop());
                break;
            case R.id.ll_jdwk:
                if(!isNotDataJdwk){
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }else{
                    refreshLayout.setNoMoreData(false);
                }
                tvDpwk.setTextColor(getResources().getColor(R.color.title_gray));
                tvJdwk.setTextColor(getResources().getColor(R.color.app_background));
                recycler.setVisibility(View.GONE);
                recyclerJdwk.setVisibility(View.VISIBLE);
                if(adapterXh.getItemCount()==0){
                    pageIndexXh=1;
                    getMiningActivityXhPageList();
                }
                rcv.scrollTo(0, llTop.getTop());
                break;
        }
    }

    public void getHotProjectAndHotUser() {
        JSONObject node = new JSONObject();
        try {
            node.put("pageIndex", 1);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_HOT_PROJECT_AND_HOT_USER)
//                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
//                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, HotProjectAndHotUserBean.class, new HttpCallBackImpl<HotProjectAndHotUserBean>() {
            @Override
            public void onCompleted(HotProjectAndHotUserBean str) {
                if(str.getData()!=null){
                    adapterU.setData(str.getData());
                    adapterP.setData(str.getData());
                }
            }
        });
    }
}
