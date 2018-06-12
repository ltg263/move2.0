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
import com.secretk.move.utils.StringUtil;
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
//        wallet","walletType
        String key = getIntent().getStringExtra("walletType");
        String wallet = getIntent().getStringExtra("wallet");
        if (key.equals("1")) {
            setZj(wallet);
        }
    }

    private void setZj(String wallet) {
        etBinding.setCursorVisible(false);
        etBinding.setFocusable(false);
        etBinding.setText(wallet);
        etBinding.setFocusableInTouchMode(false);
        butSubmit.setBackgroundColor(getResources().getColor(R.color.title_gray_ee));
        butSubmit.setText(getResources().getString(R.string.binding_end));
        butSubmit.setTextColor(getResources().getColor(R.color.title_gray));
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
                butSubmit.setBackground(getResources().getDrawable(R.drawable.btn_max_selected_radius));
                butSubmit.setText(getResources().getString(R.string.binding_end));
                butSubmit.setTextColor(getResources().getColor(R.color.white));
                butSubmit.setText(getResources().getString(R.string.binding_ok));
                break;
        }
    }

    private void submit() {
        if(StringUtil.isBlank(etBinding.getText().toString().trim())){
            ToastUtils.getInstance().show("钱包地址不能为空");
            return;
        }
        if (!NetUtil.isNetworkAvailable()) {
            ToastUtils.getInstance().show(getString(R.string.network_error));
            return;
        }
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("wallet", etBinding.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MY_PINLESS_WALLET)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    JSONObject myPinlessWallet = obj.getJSONObject("data").getJSONObject("myPinlessWallet");
                    String wallet = myPinlessWallet.getString("wallet");
                    int walletType = myPinlessWallet.getInt("walletType");
                    if(walletType==1){
                        setZj(wallet);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                loadingDialog.dismiss();
            }
        });
    }
}
