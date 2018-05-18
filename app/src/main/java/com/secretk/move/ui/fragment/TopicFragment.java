package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.apiService.HttpCallBackImpl;
import com.secretk.move.apiService.RetrofitUtil;
import com.secretk.move.apiService.RxHttpParams;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.baseManager.Constants;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.customview.QuickIndexBar;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.SearchActivity;
import com.secretk.move.ui.adapter.TopicFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.MD5;
import com.secretk.move.utils.PatternUtils;
import com.secretk.move.utils.PolicyUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.LoadingDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/5.
 */

public class TopicFragment extends LazyFragment implements  ItemClickListener, QuickIndexBar.OnLetterChangeListener {

    @BindView(R.id.qbar)
    QuickIndexBar qbar;
    @BindView(R.id.tvShow)
    TextView tvShow;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_sort_name)
    TextView tv_sort_name;
    @BindView(R.id.tv_sort_follow)
    TextView tv_sort_follow;


    private LinearLayoutManager layoutManager;
    private TopicFragmentRecyclerAdapter adapter;

    private LoadingDialog loadingDialog;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_topic;
    }

    @Override
    public void initViews() {
        qbar.addBundleView(tvShow);
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new TopicFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        qbar.setOnLetterChangeListener(this);
        loadingDialog=new LoadingDialog(getActivity());
    }
    //1-按关注数量倒序；2-按名称排序
    @Override
    public void onFirstUserVisible() {
        http(2);
    }

    @OnClick(R.id.tv_sort_name)
    public void sortByName(View view) {
        qbar.setVisibility(View.VISIBLE);
        tv_count.setText("共"+0+"个币种");
        tv_sort_name.setTextColor(Color.parseColor("#3b88f6"));
        tv_sort_follow.setTextColor(Color.parseColor("#dddddd"));
        List<SearchedBean.Projects> list=adapter.getDataByType(2);
        if (list==null||list.size()==0){
            http(2);
            return;
        }
        adapter.swithData(2);
    }

    @OnClick(R.id.tv_sort_follow)
    public void sortByFollow(View view) {
        qbar.setVisibility(View.GONE);
        tv_count.setText("共"+0+"个币种");
        tv_sort_name.setTextColor(Color.parseColor("#dddddd"));
        tv_sort_follow.setTextColor(Color.parseColor("#3b88f6"));
        List<SearchedBean.Projects> list   =adapter.getDataByType(1);
        if (list==null||list.size()==0){
            http(1);
            return;
        }
        adapter.swithData(1);
    }


    @Override
    public void onItemClick(View view, int postion) {
     int id=   adapter.getData().get(postion).getProjectId();
        IntentUtil.startProjectActivity(id);
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        ToastUtils.getInstance().show("onItemLongClick postion=" + postion);
    }

    @Override
    public void onLetterChange(String letter) {
        List<SearchedBean.Projects> list = adapter.getData();
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).getProjectCode().charAt(0) + "";
            if (letter.equals(str.trim().toUpperCase())) {
                recycler.scrollToPosition(i);
                LinearLayoutManager manager = (LinearLayoutManager) recycler.getLayoutManager();
                manager.scrollToPositionWithOffset(i, 0);
                break;
            } else if (letter.equals("#") && PatternUtils.isLetter(str) == false) {
                recycler.scrollToPosition(0);
            }
        }
    }

    @OnClick(R.id.toolbar)
    public void goSearch(View view) {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void http(final int type) {
        loadingDialog.show();
        JSONObject node = new JSONObject();
        try {
            node.put("token", token);
            node.put("projectCode", "");
            node.put("sortType", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RxHttpParams params = new RxHttpParams.Build()
                .url(Constants.SEARCH_PROJECTS)
                .addQuery("policy", PolicyUtil.encryptPolicy(node.toString()))
                .addQuery("sign", MD5.Md5(node.toString()))
                .build();
        RetrofitUtil.request(params, SearchedBean.class, new HttpCallBackImpl<SearchedBean>() {
            @Override
            public void onCompleted(SearchedBean bean) {
                loadingDialog.dismiss();
                     if (bean.getCode()==0){
                         List<SearchedBean.Projects> list=  bean.getData().getProjects();
                         tv_count.setText("共"+list.size()+"个币种");
                         adapter.setData(list,type);
                     }
            }

            @Override
            public void onError(String message) {
                loadingDialog.dismiss();
            }
        });
    }
}
