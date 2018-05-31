package com.secretk.move.view;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.utils.StringUtil;


/**
 * 作者： litongge
 * 时间： 2018/5/7 11:12
 * 邮箱；ltg263@126.com
 * 描述：常用Dialog对话框样式
 */
public class DialogUtils {

    /**
     * 描述: 自定义alertdialog
     * 更换头像和修改性别共同调用
     */
    public static void ShowAlertDialog(final Context context, String[] content, final AlertDialogInterface alertDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.change_head, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        dialog.setContentView(view);
        dialog.show();
        RelativeLayout localPicLayout = view.findViewById(R.id.localPicLayout);//从本地获取照片按钮
        RelativeLayout takePicLayout = view.findViewById(R.id.takePicLayout);
        TextView tvLogTop = view.findViewById(R.id.tv_log_top);
        TextView tvLogZhong = view.findViewById(R.id.tv_log_zhong);
        TextView tvLogBottom = view.findViewById(R.id.tv_log_bottom);
        ImageView ivXbNan = view.findViewById(R.id.iv_xb_nan);
        ImageView ivXbNv = view.findViewById(R.id.iv_xb_nv);

        tvLogTop.setText(content[0]);
        tvLogZhong.setText(content[1]);
        tvLogBottom.setText(content[2]);
        if (content[0].equals("修改性别")) {
            if ("男".equals(content[3])) {
                ivXbNan.setVisibility(View.VISIBLE);
                ivXbNv.setVisibility(View.GONE);
            } else if ("女".equals(content[3])) {
                ivXbNan.setVisibility(View.GONE);
                ivXbNv.setVisibility(View.VISIBLE);
            } else {
                ivXbNan.setVisibility(View.GONE);
                ivXbNv.setVisibility(View.GONE);
            }
            tvLogZhong.setCompoundDrawables(null, null, null, null);
            tvLogBottom.setCompoundDrawables(null, null, null, null);
        } else {

        }

        // 确定
        localPicLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                alertDialogInterface.btnLineListener(1);
                dialog.dismiss();
            }

        });
        // 取消
        takePicLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                alertDialogInterface.btnLineListener(2);
                dialog.dismiss();
            }

        });
    }

    public interface AlertDialogInterface {
        void btnLineListener(int index);
    }

    /**
     * 单个按钮，设置监听；
     *
     * @param context
     * @param
     */
    public static void showDialogError(Context context, final ErrorDialogInterface anInterface) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        Button bt_ok = view.findViewById(R.id.but_confirm);
        TextView tv_body = view.findViewById(R.id.tv_body);
//        tv_body.setText(text);

        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                anInterface.btnConfirm();
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }

    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
    public static void showEditTextDialog(final Context context, String title, String content, final EditTextDialogInterface editTextDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_with_edittext, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tvLogPrompt = view.findViewById(R.id.tv_log_prompt);
        final EditText etLogContent = view.findViewById(R.id.et_log_content);
        if(StringUtil.isNotBlank(content)){
            etLogContent.setText(content);
        }
        if(context.getString(R.string.sponsor_title).equals(title)){
            etLogContent.setHint("请输入赞助金额");
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER);
            etLogContent.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(context.getString(R.string.set_my_name).equals(title)){
            etLogContent.setHint("请输入昵称");

        }
        if(context.getString(R.string.set_my_signature).equals(title)){
            etLogContent.setHint("请输入简介");
        }
        TextView tvLogConfirm = view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = view.findViewById(R.id.tv_log_cancel);

        tvLogPrompt.setText(title);
        // 确定
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String season = etLogContent.getText().toString().trim();
                editTextDialogInterface.btnConfirm(season);
                dialog.dismiss();
            }

        });
        // 取消
        tvLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    public interface ErrorDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }

    public interface EditTextDialogInterface {
        /**
         * 确定
         *
         * @param season
         */
        public void btnConfirm(String season);
    }


}
