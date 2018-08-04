package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.FindKwBean;
import com.secretk.move.ui.adapter.FindFragmentAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/6/8 13:46
 * 邮箱；ltg263@126.com
 * 描述：主页 --主页项目
 */
public class FindKwFragment extends LazyFragment{
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.rl_top_theme)
    RelativeLayout rlTopTheme;
    @BindView(R.id.tv_icon)
    ImageView tvIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    int pageIndex = 1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private FindFragmentAdapter adapter;
    private int state;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_find_wk;
    }

    @Override
    public void initViews() {
        setVerticalManager(recycler);
        adapter = new FindFragmentAdapter(getActivity());
        recycler.setAdapter(adapter);
        initRefresh();
    }
    private void initRefresh() {
        refreshLayout.setEnableRefresh(false);
        /**
         * 上啦加载
         */
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout rl) {
                getNewsFlashPageList(null);
            }
        });
    }
    public void setRefresh(SmartRefreshLayout refreshLayout){
        pageIndex=1;
        getNewsFlashPageList(refreshLayout);
    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public void onFirstUserVisible() {
        getNewsFlashPageList(null);
    }
    private void getNewsFlashPageList(final SmartRefreshLayout rl) {
        JSONObject node = new JSONObject();
        try {
            //0-进行中，1-已结束
            node.put("state", state);
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
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    return;
                }
                if (data.getCurPageNum() == data.getPageSize()) {
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
                if (rl !=null && rl.isEnableRefresh()) {
                    rl.finishRefresh();
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
