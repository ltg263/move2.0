package com.secretk.move.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/5.
 */

public class TopicFragment extends LazyFragment implements ItemClickListener, QuickIndexBar.OnLetterChangeListener {

    @BindView(R.id.qbar)
    QuickIndexBar qbar;
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_sort_name)
    TextView tv_sort_name;
    @BindView(R.id.tv_sort_follow)
    TextView tv_sort_follow;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private LinearLayoutManager layoutManager;
    private TopicFragmentRecyclerAdapter adapter;

    private LoadingDialog loadingDialog;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_topic;
    }

    @Override
    public void initViews() {

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new TopicFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
        qbar.setOnLetterChangeListener(this);
        loadingDialog = new LoadingDialog(getActivity());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.getInstance().show("跳转界面");
            }
        });
//        List<String> list = new ArrayList<>();
//        list.add("#");
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        list.add("d");
//        list.add("e");
//        list.add("f");
//        list.add("g");
//        list.add("h");
//        list.add("i");
//        list.add("j");
//        list.add("k");
//        list.add("l");
//        list.add("m");
//        list.add("n");
//        list.add("o");
//        list.add("p");
//        list.add("q");
//        list.add("r");
//        list.add("s");
//        list.add("t");
//        list.add("y");
//        list.add("v");
//        list.add("u");
//        list.add("x");
//        list.add("g");
//        list.add("z");
//
//        qbar.setData(list);

    }

    //1-按关注数量倒序；2-按名称排序
    @Override
    public void onFirstUserVisible() {
        http(Constants.TOPIC_SORT_BY_NUM);
    }

    @OnClick(R.id.tv_sort_name)
    public void sortByName(View view) {
        fab.setVisibility(View.GONE);
        qbar.setVisibility(View.VISIBLE);
        tv_count.setText("共" + 0 + "个币种");
        tv_sort_name.setTextColor(Color.parseColor("#3b88f6"));
        tv_sort_follow.setTextColor(Color.parseColor("#dddddd"));
        List<SearchedBean.Projects> list = adapter.getDataByType(Constants.TOPIC_SORT_BY_NAME);
        if (list == null || list.size() == 0) {
            http(Constants.TOPIC_SORT_BY_NAME);
            return;
        }
        adapter.swithData(Constants.TOPIC_SORT_BY_NAME);
        int count = adapter.getDataByType(Constants.TOPIC_SORT_BY_NAME).size();
        tv_count.setText("共" + count + "个币种");


    }

    @OnClick(R.id.tv_sort_follow)
    public void sortByFollow(View view) {
        fab.setVisibility(View.VISIBLE);
        qbar.setVisibility(View.GONE);
        tv_count.setText("共" + 0 + "个币种");
        tv_sort_name.setTextColor(Color.parseColor("#dddddd"));
        tv_sort_follow.setTextColor(Color.parseColor("#3b88f6"));
        List<SearchedBean.Projects> list = adapter.getDataByType(Constants.TOPIC_SORT_BY_NUM);
        if (list == null || list.size() == 0) {
            http(Constants.TOPIC_SORT_BY_NUM);
            return;
        }
        adapter.swithData(Constants.TOPIC_SORT_BY_NUM);
        int count = adapter.getDataByType(Constants.TOPIC_SORT_BY_NUM).size();
        tv_count.setText("共" + count + "个币种");

    }


    @Override
    public void onItemClick(View view, int postion) {
        int id = adapter.getData().get(postion).getProjectId();
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
                if (bean.getCode() == 0) {
                    List<SearchedBean.Projects> list = bean.getData().getProjects();
                    tv_count.setText("共" + list.size() + "个币种");
                    adapter.setData(list, type);
                   if (type==Constants.TOPIC_SORT_BY_NAME){
                       qbar.setDatax(list);
                   }
                }
            }

            @Override
            public void onError(String message) {
                loadingDialog.dismiss();
            }
        });
    }

}
