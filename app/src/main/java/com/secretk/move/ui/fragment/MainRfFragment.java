package com.secretk.move.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.secretk.move.R;
import com.secretk.move.base.LazyFragment;
import com.secretk.move.bean.MainRfBean;
import com.secretk.move.contract.MainRfContract;
import com.secretk.move.customview.ProgressWheel;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.presenter.impl.MainRfPresenterImpl;
import com.secretk.move.ui.activity.EvaluationWriteActivity;
import com.secretk.move.ui.activity.ReleaseArticleActivity;
import com.secretk.move.ui.activity.ReleaseDiscussActivity;
import com.secretk.move.ui.activity.ReleaseEvaluationActivity;
import com.secretk.move.ui.adapter.MainRfFragmentRecyclerAdapter;
import com.secretk.move.utils.IntentUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.view.LoadingDialog;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zc on 2018/4/6.
 */

public class MainRfFragment extends LazyFragment implements ItemClickListener, MainRfContract.View {
    @BindView(R.id.recycler)
    XRecyclerView recycler;
    private LoadingDialog loadingDialog;
    private LinearLayoutManager layoutManager;
    private MainRfFragmentRecyclerAdapter adapter;
    MainRfContract.Presenter presenter;
    private int current_position;
    Dialog dialog;
    Intent intent;
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
        loadingDialog=new LoadingDialog(getActivity());
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
                IntentUtil.startProjectActivity(data.getProjectId());
                break;
            case R.id.ll_user:
            case R.id.ll_user2:
                IntentUtil.startHomeActivity(data.getCreateUserId());
                break;
            case R.id.img_comment:
                initDailog(data);
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

        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        recycler.refreshComplete();
        loadingDialog.dismiss();
    }

    @Override
    public void showMsg(String msg) {
        recycler.refreshComplete();
        recycler.loadMoreComplete();
        loadingDialog.dismiss();
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

    private void initDailog(final MainRfBean.Rows data) {
        dialog = new Dialog(getContext(), R.style.Dialog_Fullscreen);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_release, null);
        view.findViewById(R.id.ll_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                intent = new Intent(getContext(), EvaluationWriteActivity.class);
                intent.putExtra("professionalEvaDetail","professionalEvaDetail");
                intent.putExtra("totalScore",data.getTotalScore());
                intent.putExtra("projectId",data.getPostId());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_article).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), ReleaseArticleActivity.class);
                intent.putExtra("projectId",data.getPostId());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_discuss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), ReleaseDiscussActivity.class);
                intent.putExtra("projectId",data.getPostId());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.img_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }
}
