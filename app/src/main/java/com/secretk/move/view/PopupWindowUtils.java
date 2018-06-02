package com.secretk.move.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.secretk.move.R;

/**
 * 作者： litongge
 * 时间：  2018/6/2 14:41
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PopupWindowUtils extends PopupWindow {

    Context mcontext;
    public PopupWindowUtils(Context context, final ErrorDialogInterface dialogInterface) {
        super(context);
        mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.popwindow_site, null);
        Button btnCanel =  view.findViewById(R.id.btn_cancel);
        // 取消按钮
        btnCanel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogInterface.btnConfirm();
                // 销毁弹出框
                dismiss();
            }
        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置允许在外点击消失
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.ll_popup).getTop();
                int y = (int) event.getY();
                if (y < height) {
                    dismiss();
                }
                return true;
            }
        });
        showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }
    public interface ErrorDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }
}
