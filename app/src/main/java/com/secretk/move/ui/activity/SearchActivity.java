package com.secretk.move.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.SearchActivityRecyclerAdapter;
import com.secretk.move.ui.adapter.TopicFragmentRecyclerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/17.
 */

public class SearchActivity extends AppCompatActivity implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private SearchActivityRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this,  UiUtils.getColor(R.color.main_background), 0);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        adapter=new SearchActivityRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);

        List<String> list=new ArrayList<>();
        list.add("小米");
        list.add("华为");
        adapter.setData(list);
    }

    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }
}
