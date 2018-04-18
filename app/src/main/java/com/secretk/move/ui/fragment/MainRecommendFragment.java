package com.secretk.move.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.MainRecommendFollowPresenter;
import com.secretk.move.presenter.impl.MainRecommendFollowPresenterImpl;
import com.secretk.move.ui.adapter.MainFollowFragmentRecyclerAdapter;
import com.secretk.move.ui.adapter.MainRecommendFragmentRecyclerAdapter;
import com.secretk.move.view.UiListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/6.
 */

public class MainRecommendFragment extends LazyFragment implements ItemClickListener ,UiListView<String>{
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private LinearLayoutManager layoutManager;
    private MainRecommendFragmentRecyclerAdapter adapter;
    MainRecommendFollowPresenter presenter;
    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_recommend;
    }

    @Override
    public void initViews() {
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new MainRecommendFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        presenter= new MainRecommendFollowPresenterImpl(this);
        presenter.init();
        List<String> list = new ArrayList<String>();
        list.add("8.5分");
        list.add("7.2分");
        list.add("9.1分");
        adapter.setData(list);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String error) {

    }

    @Override
    public void loadInfoSuccess(List<String> list) {
        adapter.setData(list);
    }
}
