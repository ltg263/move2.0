package com.secretk.move.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;

/**
 * 作者： litongge
 * 时间：  2018/6/2 14:41
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PopupWindowUtilsReward extends PopupWindow {

    Context mcontext;
    public interface ErrorDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }
    public interface GiveDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm(String str);
    }

    /**
     * 打赏
     * @param context
     * @param dialogInterface
     */
    public PopupWindowUtilsReward(Activity context, final GiveDialogInterface dialogInterface) {
        super(context);
        this.mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.popup_reward, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ActionBar.LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点�?
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        setEvent(view,dialogInterface);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        view.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = view.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    private void setEvent(View view, final GiveDialogInterface dialogInterface) {
        final TextView tvGive1 = view.findViewById(R.id.tv_give_1);
        final TextView tvGive2 = view.findViewById(R.id.tv_give_2);
        final TextView tvGive3 = view.findViewById(R.id.tv_give_3);
        final TextView tvGive4 = view.findViewById(R.id.tv_give_4);
        final TextView tvGive5 = view.findViewById(R.id.tv_give_5);
        final TextView tvGive6 = view.findViewById(R.id.tv_give_6);
        final EditText etLogContent = view.findViewById(R.id.et_log_content);
        TextView tvLogConfirm = view.findViewById(R.id.tv_log_confirm);
        ImageView ivLogCancel = view.findViewById(R.id.iv_log_cancel);
//        etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER );
        etLogContent.setHint("最低1000");
        View.OnClickListener giveReward = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tv_give_1:
                        etLogContent.setText("1000");
                        tvGive1.setSelected(true);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_2:
                        etLogContent.setText("5000");
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(true);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_3:
                        etLogContent.setText("10000");
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(true);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_4:
                        etLogContent.setText("20000");
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(true);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_5:
                        etLogContent.setText("50000");
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(true);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_6:
                        etLogContent.setText("100000");
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(true);
                        break;
                    case R.id.tv_log_confirm:
                        String season = etLogContent.getText().toString().trim();
                        if(StringUtil.isBlank(season)){
                            ToastUtils.getInstance().show("悬赏金额不能为空");
                            return;
                        }
                        if(Integer.valueOf(season)<1000){
                            ToastUtils.getInstance().show("悬赏金额不能低于1000");
                            return;
                        }
                        dialogInterface.btnConfirm(season);
                        dismiss();
                        break;
                    case R.id.iv_log_cancel:
                        dismiss();
                        break;
                }
            }
        };
        tvGive1.setOnClickListener(giveReward);
        tvGive2.setOnClickListener(giveReward);
        tvGive3.setOnClickListener(giveReward);
        tvGive4.setOnClickListener(giveReward);
        tvGive5.setOnClickListener(giveReward);
        tvGive6.setOnClickListener(giveReward);
        tvLogConfirm.setOnClickListener(giveReward);
        ivLogCancel.setOnClickListener(giveReward);
        StringUtil.etSearchChangedListener(etLogContent, null, new StringUtil.EtChange() {
            @Override
            public void etYes() {
                tvGive1.setSelected(false);
                tvGive2.setSelected(false);
                tvGive3.setSelected(false);
                tvGive4.setSelected(false);
                tvGive5.setSelected(false);
                tvGive6.setSelected(false);
            }
        });
    }
}
