package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.secretk.move.R;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.MineAssetDetailsAdapter;
import com.secretk.move.view.AppBarHeadView;
import com.secretk.move.view.RecycleScrollView;

import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间：  2018/5/29 13:57
 * 邮箱；ltg263@126.com
 * 描述：提取记录
 */
public class MineAssetExtractRecordActivity extends BaseActivity {
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
        mHeadView.setTitle(getString(R.string.extract_record));
        return mHeadView;
    }

    @Override
    protected int setOnCreate() {
        return R.layout.activity_mine_asset_extract_record;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        setVerticalManager(rvReview);
        adapter = new MineAssetDetailsAdapter(this);
        rvReview.setAdapter(adapter);
    }

    @Override
    protected void initData() {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 9; i++) {
//            list.add("我是：" + i);
//        }
//        adapter.setData(list);

//        if (!NetUtil.isNetworkAvailable()) {
//            ToastUtils.getInstance().show(getString(R.string.network_error));
//            return;
//        }
//        JSONObject node = new JSONObject();
//        try {
//            node.put("token", token);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        RxHttpParams params = new RxHttpParams.Build()
//                .url(Constants.MY_TOKEN_RECORDS)
//                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
//                .addQuery("sign", MD5.Md5(node.toString()))
//                .build();
//        loadingDialog.show();
//        RetrofitUtil.request(params, MyCollectList.class, new HttpCallBackImpl<MyCollectList>() {
//            @Override
//            public void onCompleted(MyCollectList bean) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                loadingDialog.dismiss();
//            }
//        });
    }

}
