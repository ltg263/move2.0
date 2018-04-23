package com.secretk.move.ui.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.secretk.move.R;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.adapter.AddLabelActivityRecyclerAdapter;
import com.secretk.move.utils.StatusBarUtil;
import com.secretk.move.utils.ToastUtils;
import com.secretk.move.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zc on 2018/4/22.
 */

public class AddLabelActivity extends AppCompatActivity implements ItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    AddLabelActivityRecyclerAdapter adapter;
    List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_label);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, UiUtils.getColor(R.color.main_background), 0);

        recycler.setLayoutManager(new GridLayoutManager(this, 3));

        adapter = new AddLabelActivityRecyclerAdapter();
        recycler.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    private void initData() {
        list.add("+添加话题");
        for (int i = 0; i < 20; i++) {
            list.add(i + "号标签");
        }
        adapter.setData(list);
    }


    @Override
    public void onItemClick(View view, int postion) {
        ToastUtils.getInstance().show("onItemClick postion=" + postion);
        showDialog();
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        ToastUtils.getInstance().show("onItemLongClick postion=" + postion);
    }

    @OnClick(R.id.img_return)
    public void img_return() {
        finish();
    }

    @OnClick(R.id.tv_finish)
    public void tv_finish() {
        finish();
    }

    public void showDialog() {
        PopupWindow popupWindow=new PopupWindow(AddLabelActivity.this);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
       View contentView = mLayoutInflater.inflate(R.layout.dialog_add_label,
                null);
        popupWindow.setContentView(contentView);

        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0,
                0);
    }
}
