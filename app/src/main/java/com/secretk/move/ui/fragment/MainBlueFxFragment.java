package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

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
import com.secretk.move.bean.CommonListBase;
import com.secretk.move.bean.InfoBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.LoginHomeActivity;
import com.secretk.move.ui.adapter.MainRfFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.CustomViewPager;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：主页 --推荐
 */
public class MainBlueFxFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rcv)
    RecycleScrollView rcv;
    @BindView(R.id.viewpager)
    CustomViewPager viewpager;
    private MainRfFragmentRecyclerAdapter adapter;
    int pageIndex = 1;
    String tokenLs = "";
    boolean showFragment = false;//需要弹框
    private List<InfoBean.DataBeanX.DataBean.RowsBean> rows;
    private ArrayList<String> imageAdList;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_rf;
    }

    @Override
    public void initViews() {
        setVerticalManager(recycler);
        initRefresh();
        adapter = new MainRfFragmentRecyclerAdapter(getActivity());
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        viewpager.setZx();
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
                    viewpager.setVisibility(View.VISIBLE);
                    viewpager.setImageResources(imageAdList, mAdCycleViewListener);
                }
            }
        });
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

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                onFirstUserVisible();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                onFirstUserVisible();
            }
        });
    }


    public void dblclickRefresh() {
        if (getUserVisibleHint()) {
//            recycler.setFocusable(false);
            if(rcv.getScrollY()!=0){
                rcv.fullScroll(ScrollView.FOCUS_UP);
            }
            refreshLayout.autoRefresh();
        }
    }

    @Override
    public void onFirstUserVisible() {
        //轮播图
        getNewsFlashImgList();

        tokenLs = token;
        if (!showFragment) {
            loadingDialog.show();
        }
        showFragment = true;
        final String token = SharedUtils.singleton().get("token", "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);
            node.put("pageSize", Constants.PAGE_SIZE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MAIN_RECOMMEND)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, CommonListBase.class, new HttpCallBackImpl<CommonListBase>() {
            @Override
            public void onCompleted(CommonListBase bean) {
                CommonListBase.DataBean.DetailsBean detailsBean = bean.getData().getRecommends();
                if (detailsBean.getCurPageNum() == detailsBean.getPageCount()) {
                    refreshLayout.finishLoadMoreWithNoMoreData();
                }
                if (pageIndex > 2) {
                    adapter.setAddData(detailsBean.getRows());
                } else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                super.onFinish();
                if (refreshLayout.isEnableRefresh()) {
                    refreshLayout.finishRefresh();
                }
                if (refreshLayout.isEnableLoadMore()) {
                    refreshLayout.finishLoadMore();
                }
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (showFragment && !tokenLs.equals(token)) {
            pageIndex = 1;
            onFirstUserVisible();
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        if (isLoginZt) {
            int postId = adapter.getDataIndex(postion).getPostId();
            int postType = adapter.getDataIndex(postion).getPostType();
            IntentUtil.go2DetailsByType(postType, String.valueOf(postId));
        } else {
            IntentUtil.startActivity(LoginHomeActivity.class);
        }
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
