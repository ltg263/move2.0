package com.secretk.move.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.bean.TopicBean;
import com.secretk.move.customview.QuickIndexBar;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.impl.TopicFragmentPresenter;
import com.secretk.move.presenter.impl.TopicFragmentPresenterImpl;
import com.secretk.move.ui.adapter.TopicFragmentRecyclerAdapter;
import com.secretk.move.utils.PatternUtils;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.TopicFragmentView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zc on 2018/4/5.
 */

public class TopicFragment extends LazyFragment implements TopicFragmentView ,ItemClickListener,QuickIndexBar.OnLetterChangeListener {

    @BindView(R.id.qbar)
    QuickIndexBar qbar;
    @BindView(R.id.tvShow)
    TextView tvShow;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private TopicFragmentRecyclerAdapter adapter;
    private TopicFragmentPresenter presenter;
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
        adapter.setItemListener(this);
        qbar.setOnLetterChangeListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        presenter=new TopicFragmentPresenterImpl(this);
        presenter.initialized();


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String error) {
        ToastUtils.getInstance().show(error);
    }

    @Override
    public void loadInfoSuccess(List<TopicBean> list) {
        adapter.setData(list);
    }

    @Override
    public void onItemClick(View view, int postion) {
        ToastUtils.getInstance().show("onItemClick postion="+postion);
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        ToastUtils.getInstance().show("onItemLongClick postion="+postion);
    }

    @Override
    public void onLetterChange(String letter) {
        List<TopicBean> list=adapter.getData();
        for (int i=0;i<list.size();i++){
            String str=list.get(i).getSpell().charAt(0)+"";
            if (letter.equals(str.trim().toUpperCase())){
                recycler.scrollToPosition(i);
                break;
            }else if (letter.equals("#")&& PatternUtils.isLetter(str)==false){
                recycler.scrollToPosition(0);
            }
        }

    }
}
