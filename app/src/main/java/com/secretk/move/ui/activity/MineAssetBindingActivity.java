package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.MenuInfo;
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
 * 时间：  2018/5/29 15:05
 * 邮箱；ltg263@126.com
 * 描述：绑定錢包地址
 */
public class MineAssetBindingActivity extends BaseActivity {
    @BindView(R.id.et_binding)
    EditText etBinding;
    @BindView(R.id.but_submit)
    Button butSubmit;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_binding;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        String key = getIntent().getStringExtra("key");
        if (key.equals("1")) {
            etBinding.setCursorVisible(false);
            etBinding.setFocusable(false);
            etBinding.setFocusableInTouchMode(false);
            butSubmit.setText(getResources().getString(R.string.binding_end));
        } else {

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle(getString(R.string.wallet_site));
        return mHeadView;
    }

    @OnClick({R.id.but_submit,R.id.tv_change_site})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_submit:
                submit();
                break;
            case R.id.tv_change_site:
                etBinding.setCursorVisible(true);
                etBinding.setFocusable(true);
                etBinding.setFocusableInTouchMode(true);
                etBinding.requestFocus();
                break;
        }
    }

    private void submit() {
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
            public void onCompleted(String str) {

            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }
}
