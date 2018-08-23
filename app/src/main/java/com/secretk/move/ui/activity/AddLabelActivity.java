package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.BaseActivity;
import com.secretk.move.base.TagsAndTagtbean;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.DiscussLabelListbean;
import com.secretk.move.bean.MenuInfo;
import com.secretk.move.ui.adapter.AddLabelTopAdapter;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;
import com.secretk.move.view.AppBarHeadView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/22.
 */

public class AddLabelActivity extends BaseActivity{
    @BindView(R.id.recycler)
    RecyclerView recycler;
    AddLabelTopAdapter adapter;
   public static SparseArray<DiscussLabelListbean.TagList> array =null;

    @Override
    protected int setOnCreate() {
        return R.layout.activity_add_label;
    }

    @Override
    protected void initUI(Bundle savedInstanceState) {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);

        setVerticalManager(recycler);
        adapter = new AddLabelTopAdapter();
        recycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        array = new SparseArray<>();
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("typeId", 0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.GET_TAGS_AND_TAG_TYPE)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, TagsAndTagtbean.class, new HttpCallBackImpl<TagsAndTagtbean>() {
            @Override
            public void onCompleted(TagsAndTagtbean bean) {
                List<TagsAndTagtbean.DataBean.ResultBean> lists = bean.getData().getResult();
                adapter.setData(lists);
            }
        });
    }

    @Override
    protected AppBarHeadView initHeadView(List<MenuInfo> mMenus) {
        mHeadView = findViewById(R.id.head_app_server);
        mHeadView.setHeadBackShow(true);
        mHeadView.setTitleColor(R.color.title_gray);
        mHeadView.setTitle("选择标签");
        mMenuInfos.add(0, new MenuInfo(R.string.ok, getString(R.string.ok), 0));
        return mHeadView;
    }

    @Override
    protected void OnToolbarRightListener() {
        finish();
    }

    @Override
    public void onBackPressed() {
        array=null;
        super.onBackPressed();
    }

}
