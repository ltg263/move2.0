package com.secretk.move.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.bean.TopicBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.MainBlueSkyFragmentRecyclerAdapter;
import com.secretk.move.ui.adapter.MainFollowFragmentRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/6.
 */

public class MainBlueSkyFragment extends LazyFragment implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private MainBlueSkyFragmentRecyclerAdapter adapter;

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_bluesky;
    }

    @Override
    public void initViews() {
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter = new MainBlueSkyFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        List<String> list = new ArrayList<String>();
        list.add("0");
        list.add("1");
        adapter.setData(list);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
