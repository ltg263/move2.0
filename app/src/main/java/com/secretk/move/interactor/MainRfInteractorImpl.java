package com.secretk.move.interactor;

import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.returnbean.MainRfBean;
import com.secretk.move.contract.MainRfContract;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zc on 2018/4/27.
 */

public class MainRfInteractorImpl implements MainRfContract.Interactor {
    MainRfContract.CallBack callBack;
    String token = "";

    public MainRfInteractorImpl(MainRfContract.CallBack callBack) {
        this.callBack = callBack;
        token = SharedUtils.singleton().get("token", "");
    }

    @Override
    public void loadReCommendPageIndex(final int index) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", index);
            node.put("pageSize", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MAIN_RECOMMEND)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MainRfBean.class, new HttpCallBackImpl<MainRfBean>() {
            @Override
            public void onCompleted(MainRfBean mainRecommendBean) {
                callBack.loadPageIndexSuccess(index, mainRecommendBean.getData().getRecommends().getRows());
            }
        });
    }

    @Override
    public void loadfollowPageIndex(final int index) {
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("pageIndex", index);
            node.put("pageSize", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.MAIN_FOLLOW)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, MainRfBean.class, new HttpCallBackImpl<MainRfBean>() {
            @Override
            public void onCompleted(MainRfBean mainRecommendBean) {
                callBack.loadPageIndexSuccess(index, mainRecommendBean.getData().getFollows().getRows());
            }
        });
    }

    @Override
    public void destroy() {
        callBack = null;
    }
}
