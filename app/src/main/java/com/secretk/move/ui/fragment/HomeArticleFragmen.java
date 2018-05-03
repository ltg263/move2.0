package com.secretk.move.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.HomeRecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by WZJSB-01 on 2017/12/5.
 */

public class HomeArticleFragmen extends LazyFragment  implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;

    private HomeRecommendAdapter adapter;
    @Override
    public int setFragmentView() {
//        return R.layout.home_viewpager_layout;
        return R.layout.fragment_topic;
    }

    @Override
    public void initViews() {
        setVerticalManager(recycler);
        adapter = new HomeRecommendAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        List<String> list = new ArrayList<String>();
        list.add("8.5分");
        list.add("7.2分");
        list.add("9.1分");

    }



    @Override
    public void onItemClick(View view, int postion) {

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }

}
