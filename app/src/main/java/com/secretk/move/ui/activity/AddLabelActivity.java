package com.secretk.move.ui.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
        showPopupWindow();
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

    public void showPopupWindow() {
     final    PopupWindow popupWindow = new PopupWindow(AddLabelActivity.this);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        View contentView = mLayoutInflater.inflate(R.layout.popup_add_label,
                null);
        popupWindow.setContentView(contentView);
        AddLabelActivity.this.setBackgroundAlpha(AddLabelActivity.this, 0.5f);//设置屏幕透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(AddLabelActivity.this,1.0f);
            }
        });
        Button but_cancel=contentView.findViewById(R.id.but_cancel);
        Button but_confirm=contentView.findViewById(R.id.but_confirm);
        final EditText ed_word=contentView.findViewById(R.id.ed_word);
        but_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ToastUtils.getInstance().show("取消");
            }
        });

        but_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                String str=ed_word.getText().toString().trim();
                if (!TextUtils.isEmpty(str)){
                    adapter.addData(str);
                }
            }
        });
        popupWindow.showAtLocation(contentView, Gravity.CENTER, 0,
                0);
    }

    /**
     * 设置页面的透明度
     *
     * @param bgAlpha 1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }
}
