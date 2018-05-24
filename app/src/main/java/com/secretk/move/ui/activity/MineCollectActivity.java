package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.bean.MyCollectList;
import com.secretk.move.ui.adapter.HomeListAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;


/**
 * 作者： litongge
 * 时间： 2018/5/3 16:53
 * 邮箱；ltg263@126.com
 * 描述：文章-评测--评论详情列表
 */
public class MineCollectActivity extends BaseActivity {

    @BindView(R.id.rv_collect)
    RecyclerView rvCollect;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String postId;
    private int pageIndex = 1;
    private HomeListAdapter adapter;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_collect;
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("收藏");
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }


    @Override
    protected void initUI(Bundle savedInstanceState) {
        postId = getIntent().getStringExtra("postId");
        setVerticalManager(rvCollect);
        adapter = new HomeListAdapter(this);
        rvCollect.setAdapter(adapter);
        initRefresh();
    }

    private void initRefresh() {
        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageIndex = 1;
                initData();
            }
        });
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                initData();
            }
        });
    }

    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", pageIndex++);//帖子ID
            node.put("pageSize", Constants.PAGE_SIZE);//帖子ID
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_H5_URLS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, MyCollectList.class, new HttpCallBackImpl<MyCollectList>() {
            @Override
            public void onCompleted(MyCollectList bean) {
                MyCollectList.DataBean.MyTokenRecordsBean detailsBean = bean.getData().getMyTokenRecords();
                if(detailsBean.getRows()==null ||detailsBean.getRows().size()==0){
                    return;
                }
                if(pageIndex>2){
                    adapter.setAddData(detailsBean.getRows());
                }else {
                    adapter.setData(detailsBean.getRows());
                }
            }

            @Override
            public void onFinish() {
                if (refreshLayout.isRefreshing()) {
                    refreshLayout.finishRefresh();
                }
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }
}
