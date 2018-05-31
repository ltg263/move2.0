package com.secretk.move.ui.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

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
import com.secretk.move.utils.UiUtils;
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
        loadingDialog = new LoadingDialog(getActivity());
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
                IntentUtil.go2DetailsByType(data.getPostType(), data.getPostId());
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
        RelativeLayout rl_parent = view.findViewById(R.id.rl_parent);
        Bitmap bitmap = screenShotWithoutStatusBar(getActivity());
        rl_parent.setBackground(new BitmapDrawable(getResources(), blurBitmap(bitmap, 25)));
        view.findViewById(R.id.ll_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                intent = new Intent(getContext(), EvaluationWriteActivity.class);
                intent.putExtra("professionalEvaDetail", "professionalEvaDetail");
                intent.putExtra("totalScore", data.getTotalScore());
                intent.putExtra("projectId", data.getPostId());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_article).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), ReleaseArticleActivity.class);
                intent.putExtra("projectId", data.getPostId());
                startActivity(intent);
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.ll_discuss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), ReleaseDiscussActivity.class);
                intent.putExtra("projectId", data.getPostId());
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

    // 图片缩放比例(即模糊度)
    private static final float BITMAP_SCALE = 0.1f;

    public Bitmap blurBitmap(Bitmap image, float blurRadius) {
        // 计算图片缩小后的长宽
        int width = Math.round(image.getWidth() * BITMAP_SCALE);
        int height = Math.round(image.getHeight() * BITMAP_SCALE);

        // 将缩小后的图片做为预渲染的图片
        Bitmap inputBitmap = Bitmap.createScaledBitmap(image, width, height, false);
        // 创建一张渲染后的输出图片
        Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        // 创建RenderScript内核对象
        RenderScript rs = RenderScript.create(getActivity());
        // 创建一个模糊效果的RenderScript的工具对象
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        // 由于RenderScript并没有使用VM来分配内存,所以需要使用Allocation类来创建和分配内存空间
        // 创建Allocation对象的时候其实内存是空的,需要使用copyTo()将数据填充进去
        Allocation tmpIn = Allocation.createFromBitmap(rs, inputBitmap);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);

        // 设置渲染的模糊程度, 25f是最大模糊度
        blurScript.setRadius(blurRadius);
        // 设置blurScript对象的输入内存
        blurScript.setInput(tmpIn);
        // 将输出数据保存到输出内存中
        blurScript.forEach(tmpOut);
        // 将数据填充到Allocation中
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }


    public static Bitmap screenShotWithoutStatusBar(Activity activity) {
        //通过window的源码可以看出：检索顶层窗口的装饰视图，可以作为一个窗口添加到窗口管理器
        View view = activity.getWindow().getDecorView();
        //启用或禁用绘图缓存
        view.setDrawingCacheEnabled(true);
        //创建绘图缓存
        view.buildDrawingCache();
        //拿到绘图缓存
        Bitmap bitmap = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);

        //状态栏高度
        int statusBarHeight = frame.top;
        int width = UiUtils.getWindowWidth();
        int height = UiUtils.getWindowHeight();

        Bitmap bp = null;
        bp = Bitmap.createBitmap(bitmap, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }
}
