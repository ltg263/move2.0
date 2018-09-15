package com.secretk.move.view;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.utils.KeybordS;
import com.secretk.move.utils.PopupWindowShowUtil;
import com.secretk.move.utils.SharedUtils;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;

/**
 * 作者： litongge
 * 时间：  2018/6/2 14:41
 * 邮箱；ltg263@126.com
 * 描述：
 */
public class PopupWindowUtils extends PopupWindow {

    Context mcontext;
    private EditText etLogContent;

    public PopupWindowUtils(ImageView ivPupo, final Context context, final ErrorDialogInterface dialogInterface) {
        super(context);
        mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.popwindow_site, null);
//        Button btnCanel =  view.findViewById(R.id.btn_cancel);
//        // 取消按钮
//        btnCanel.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//                dialogInterface.btnConfirm();
//                // 销毁弹出框
//                dismiss();
//            }
//        });
        // 设置SelectPicPopupWindow的View
        this.setContentView(view);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置允许在外点击消失
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置SelectPicPopupWindow弹出窗体的背景

        this.setBackgroundDrawable(new ColorDrawable());
        WindowManager.LayoutParams lp =((Activity)context).getWindow().getAttributes();
        lp.alpha =0.3f;
        ((Activity)context).getWindow().setAttributes(lp);
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

        int windowPos[] = PopupWindowShowUtil.calculatePopWindowPos(ivPupo, view);
        int xOff = 0; // 可以自己调整偏移
        windowPos[0] -= xOff;
        this.showAtLocation(ivPupo, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }
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
    public PopupWindowUtils(Activity context, final GiveDialogInterface dialogInterface) {
        super(context);
        this.mcontext = context;
        LayoutInflater inflater = (LayoutInflater) context .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.popup_give_reward, null);

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
                        KeybordS.closeKeybord(etLogContent,mcontext);
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
        etLogContent = view.findViewById(R.id.et_log_content);
        TextView tvLogPrompt = view.findViewById(R.id.tv_log_prompt);
        TextView tvLogConfirm = view.findViewById(R.id.tv_log_confirm);
        ImageView ivLogCancel = view.findViewById(R.id.iv_log_cancel);
//        etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER );
        tvLogPrompt.setText("给作者鼓励FIND");
        etLogContent.setHint("可填写10-2000");
        View.OnClickListener giveReward = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.tv_give_1:
                        etLogContent.setText(tvGive1.getText().toString());
                        tvGive1.setSelected(true);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_2:
                        etLogContent.setText(tvGive2.getText().toString());
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(true);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_3:
                        etLogContent.setText(tvGive3.getText().toString());
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(true);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_4:
                        etLogContent.setText(tvGive4.getText().toString());
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(true);
                        tvGive5.setSelected(false);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_5:
                        etLogContent.setText(tvGive5.getText().toString());
                        tvGive1.setSelected(false);
                        tvGive2.setSelected(false);
                        tvGive3.setSelected(false);
                        tvGive4.setSelected(false);
                        tvGive5.setSelected(true);
                        tvGive6.setSelected(false);
                        break;
                    case R.id.tv_give_6:
                        etLogContent.setText(tvGive6.getText().toString());
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
                            ToastUtils.getInstance().show("赞助金额不能为空");
                            return;
                        }
                        if(Integer.valueOf(season)<10 || Integer.valueOf(season)>2000){
                            ToastUtils.getInstance().show("请输入10-2000整数");
                            return;
                        }
                        if(Double.valueOf(season)>Double.valueOf(SharedUtils.getKffCoinNum())){
                            ToastUtils.getInstance().show("你的余额不足");
                            return;
                        }
                        dialogInterface.btnConfirm(season);
                        dismiss();
                        break;
                    case R.id.iv_log_cancel:
                        KeybordS.closeKeybord(etLogContent,mcontext);
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
