package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.HomeReviewBase;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.DetailsDiscussActivity;
import com.secretk.move.ui.adapter.ProjectRecommendAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.SharedUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： litongge
 * 时间： 2018/4/27 15:04
 * 邮箱；ltg263@126.com
 * 描述：项目主页--讨论
 */

public class ProjectDiscussFragment extends LazyFragment  implements ItemClickListener{
    @BindView(R.id.rv_review_hot)
    RecyclerView rvReviewHot;
    @BindView(R.id.rv_review_newest)
    RecyclerView rvReviewNewest;

    private ProjectRecommendAdapter adapter;
    private ProjectRecommendAdapter adapterNewest;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_project_discuss;
    }

    @Override
    public void initViews() {
        setVerticalManager(rvReviewHot);
        setVerticalManager(rvReviewNewest);
        adapter = new ProjectRecommendAdapter();
        rvReviewHot.setAdapter(adapter);
        adapterNewest = new ProjectRecommendAdapter();
        rvReviewNewest.setAdapter(adapterNewest);
        adapter.setItemListener(this);
        adapterNewest.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        String token = SharedUtils.singleton().get(Constants.TOKEN_KEY, "");
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            //node.put("userId", token);
            node.put("pageIndex", 1);
            node.put("pageSize", 10);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.USERHOME_DISCUSS_LIST)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, String.class, new HttpCallBackImpl<String>() {
            @Override
            public void onCompleted(String bean) {
                List<HomeReviewBase> list = new ArrayList<>();
                HomeReviewBase base = new HomeReviewBase();
                base.setDiyi("张三");
                base.setEr("李四");
                base.setSan("周五");
                base.setIndex(1);
                list.add(base);
                list.add(base);
                adapter.setData(list);
                adapterNewest.setData(list);
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        IntentUtil.startActivity(DetailsDiscussActivity.class);
        //Toast.makeText(getActivity(), "讨论界面    我是第："+postion, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
