package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MineAssetDetailsAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.RecycleScrollView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/29 11:11
 * 邮箱；ltg263@126.com
 * 描述：账单明细
 */
public class MineAssetDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_total_assets)
    TextView tvTotalAssets;
    @BindView(R.id.rv_review)
    RecyclerView rvReview;
    @BindView(R.id.rsv)
    RecycleScrollView rsv;
    private MineAssetDetailsAdapter adapter;

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.expenditure));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_details;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MineAssetDetailsAdapter(this);
        rvReview.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MY_TOKEN_RECORDS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String bean) {

            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add("我是：" + i);
        }
        adapter.setData(list);
    }
}
