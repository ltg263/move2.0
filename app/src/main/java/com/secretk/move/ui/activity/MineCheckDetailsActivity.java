package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.NetUtil;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者： litongge
 * 时间：  2018/5/29 10:01
 * 邮箱；ltg263@126.com
 * 描述：我的资产明细
 */
public class MineCheckDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_total_assets)
    TextView tvTotalAssets;
    @BindView(R.id.tv_coin_lock_sum)
    TextView tvCoinLockSum;
    @BindView(R.id.tv_my_pinless_wallet)
    TextView tvMyPinlessWallet;
    @BindView(R.id.tv_coin_distributed_sum)
    TextView tvCoinDistributedSum;
    @BindView(R.id.tv_coin_usable_sum)
    TextView tvCoinUsableSum;
    @BindView(R.id.ll_coin_lock_sum)
    LinearLayout llCoinLockSum;
    @BindView(R.id.ll_pinless_wallet)
    LinearLayout llPinlessWallet;


    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.asset_details));
        mMenuInfos.add(0, new MenuInfo(R.string.bill, getString(R.string.bill), 0));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_check_details;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {

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
    }

    @Override
    protected void OnToolbarRightListener() {
        IntentUtil.startActivity(MineAssetDetailsActivity.class);
    }

    @OnClick({R.id.ll_coin_lock_sum, R.id.ll_pinless_wallet, R.id.ll_coin_distributed_sum, R.id.ll_coin_usable_sum,
            R.id.tv_extract, R.id.tv_recharge,R.id.tv_wallet_site})
    public void onViewClicked(View view) {
        String key[] = {"key"};
        switch (view.getId()) {
            case R.id.ll_coin_lock_sum:
                ToastUtils.getInstance().show("努力开发中...");
//                IntentUtil.startActivity(MineAssetCoinActivity.class);
                break;
            case R.id.ll_pinless_wallet:
                ToastUtils.getInstance().show("努力开发中...");
//                IntentUtil.startActivity(MineAssetPinlessActivity.class);
                break;
            case R.id.ll_coin_distributed_sum:
                IntentUtil.startActivity(MineAssetDistributedActivity.class);
                break;
            case R.id.ll_coin_usable_sum:
                ToastUtils.getInstance().show("努力开发中...");
//                IntentUtil.startActivity(MineAssetUsableActivity.class);
                break;
            case R.id.tv_wallet_site:
                String[] values = {"1"};
                IntentUtil.startActivity(MineAssetBindingActivity.class, key, values);
                break;
            case R.id.tv_extract:
                ToastUtils.getInstance().show("努力开发中...");
//                IntentUtil.startActivity(MineAssetFindExtractActivity.class);
                break;
            case R.id.tv_recharge:
                ToastUtils.getInstance().show("努力开发中...");
                break;
        }
    }
}
