package com.secretk.move.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.customview.QuickIndexBar;
import com.secretk.move.ui.adapter.TopicFragmentRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/5.
 */

public class TopicFragment extends LazyFragment {

    @BindView(R.id.qbar)
    QuickIndexBar qbar;
    @BindView(R.id.tvShow)
    TextView tvShow;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private TopicFragmentRecyclerAdapter adapter;
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
        adapter=new TopicFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
    }

    @Override
    public void onFirstUserVisible() {
        List<String> list=new ArrayList<String>();
       for (int i=0;i<100;i++){
           list.add("this is the "+i+" position");
       }
       adapter.setData(list);
    }
}
