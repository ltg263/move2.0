package com.secretk.move.ui.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
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
 * 时间：  2018/5/31 09:54
 * 邮箱；ltg263@126.com
 * 描述：我的邀请
 */
public class MineRecommendActivity extends BaseActivity {
    @BindView(R.id.tv_link)
    TextView tvLink;
    @BindView(R.id.tv_m1)
    TextView tvM1;
    @BindView(R.id.tv_m1_v)
    TextView tvM1V;
    @BindView(R.id.tv_m2)
    TextView tvM2;
    @BindView(R.id.tv_m2_v)
    TextView tvM2V;
    @BindView(R.id.tv_award_num)
    TextView tvAwardNum;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_recommend;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        tvLink.setFocusable(false);
    }

    @Override
    protected void initData() {
        if(!NetUtil.isNetworkAvailable()){
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
                .url(Constants.CREATE_URLIN_REGISTER)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
                try {
                    JSONObject obj = new JSONObject(str);
                    JSONObject data = obj.getJSONObject("data");
                    tvAwardNum.setText(Html.fromHtml(StringUtil.format(getString(R.string.extract_find_jl), String.valueOf(data.getDouble("awardNum")))));
                    tvM1V.setText(String.valueOf(data.getInt("m1Num")));
                    tvM2V.setText(String.valueOf(data.getInt("m2Num")));
                    tvLink.setText(data.getString("url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                if(loadingDialog.isShowing()){
                    loadingDialog.dismiss();
                }
            }
        });
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitle("我的邀请");
        mHeadView.setTitleColor(R.color.title_gray);
        return mHeadView;
    }

    @OnClick({R.id.tv_poster, R.id.tv_fz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_poster:
                createPoste();
                break;
            case R.id.tv_fz:
                cmdFz();
                break;
        }
    }

    private void cmdFz() {
        ClipboardManager cmb = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        if (cmb != null) {
            ToastUtils.getInstance().show("复制成功");
        } else {
            ToastUtils.getInstance().show("复制失败，请重新复制");
        }
        cmb.setPrimaryClip(ClipData.newPlainText(null,tvLink.getText().toString()));
    }

    private void createPoste() {
        if(!NetUtil.isNetworkAvailable()){
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
                .url(Constants.CREATE_POSTE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        loadingDialog.show();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String str) {
//                BASE_URL_ZJ
                try {
                    JSONObject obj = new JSONObject(str);
                    String url = obj.getJSONObject("data").getString("url");
                    String key[]={"imgUrl","imgName"};
                    String values[]={Constants.BASE_URL_ZJ+url,"邀请海报"};
                    IntentUtil.startActivity(InvitePosterActivity.class,key,values);
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
