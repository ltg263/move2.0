package com.secretk.move.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.bean.MainRfBean;
import com.secretk.move.contract.MainRfContract;
import com.secretk.move.customview.ProgressWheel;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.impl.MainRfPresenterImpl;
import com.secretk.move.ui.adapter.MainRfFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/6.
 */

public class MainRfFragment extends LazyFragment implements ItemClickListener, MainRfContract.View {
    @BindView(R.id.recycler)
    XRecyclerView recycler;
    @BindView(R.id.progress_bar)
    ProgressWheel progress_bar;
    private LinearLayoutManager layoutManager;
    private MainRfFragmentRecyclerAdapter adapter;
    MainRfContract.Presenter presenter;
    private int current_position;

    public static MainRfFragment newInstance(int position) {
        Bundle args = new Bundle();
        MainRfFragment fragment = new MainRfFragment();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_main_rf;
    }

    @Override
    public void initViews() {
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        recycler.setRefreshProgressStyle(ProgressStyle.SysProgress);
        recycler.setLoadingMoreProgressStyle(ProgressStyle.SysProgress);
        adapter = new MainRfFragmentRecyclerAdapter();
        recycler.setAdapter(adapter);
        recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.loadingHead();
            }

            @Override
            public void onLoadMore() {
                presenter.loadingFoot();
            }
        });
        adapter.setItemListener(this);
    }

    @Override
    public void onFirstUserVisible() {
        current_position = getArguments().getInt("position");
        adapter.setAdapterType(current_position);
        presenter = new MainRfPresenterImpl(this, current_position);
        presenter.loadingHead();

    }

    @Override
    public void onItemClick(android.view.View view, int postion) {
        MainRfBean.Rows data = adapter.getDataIndex(postion);
        switch (view.getId()) {
            case R.id.img_organization:
            case R.id.tvName:
            case R.id.tv_english_name:
            case R.id.tvTime:

                break;
            case R.id.ll_user:
                IntentUtil.startHomeActivity(data.getCreateUserId());
                break;
            default:
                IntentUtil.go2DetailsByType(data.getPostType(),data.getPostId());
                break;
        }
    }

    @Override
    public void onItemLongClick(android.view.View view, int postion) {

    }

    @Override
    public void showLoading() {
        progress_bar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        recycler.refreshComplete();
        progress_bar.setVisibility(android.view.View.INVISIBLE);
    }

    @Override
    public void showMsg(String msg) {
        recycler.refreshComplete();
        recycler.loadMoreComplete();
        ToastUtils.getInstance().show(msg);
    }

    @Override
    public void onloadHeadSuccess(List<MainRfBean.Rows> list) {
        recycler.refreshComplete();
        adapter.loadHead(list);
    }

    @Override
    public void onloadMoreSuccess(List<MainRfBean.Rows> list) {
        recycler.loadMoreComplete();
        adapter.loadMore(list);
    }
}
