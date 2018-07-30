package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.InfoBean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.DialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间： 2018/7/2 18:34
 * 邮箱；ltg263@126.com
 * 描述：主页话题
 */

public class FindWkDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_find_num)
    TextView tvFindNum;
    @BindView(R.id.tv_find_yd)
    TextView tvFindYd;
    @BindView(R.id.tv_jl_num)
    TextView tvJlNum;
    @BindView(R.id.tv_sy_num)
    TextView tvSyNum;
    @BindView(R.id.tv_go_follow)
    TextView tvGoFollow;
    @BindView(R.id.tv_go_comment)
    TextView tvGoComment;
    @BindView(R.id.tv_go_share)
    TextView tvGoShare;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_go_lqjl)
    TextView tvGoLqjl;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("点评挖矿");
        mMenuInfos.add(0, new MenuInfo(R.string.home_find_wk_2, getString(R.string.home_find_wk_2), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_wk_details_find;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        initRefresh();
    }


    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
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
                if (data == null) {
                    return;
                }
            }
        });
    }


    private void initRefresh() {
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadMore(false);

        /**
         * 下拉刷新
         */
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
            }
        });
    }


    @OnClick({R.id.tv_go_follow, R.id.tv_go_comment, R.id.tv_go_share, R.id.tv_go_lqjl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_go_follow:
                break;
            case R.id.tv_go_comment:
                break;
            case R.id.tv_go_share:
                break;
            case R.id.tv_go_lqjl:
                DialogUtils.showDialogGetFind(this, "领取900FIND", new DialogUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {

                    }
                });
                break;
        }
    }
}
