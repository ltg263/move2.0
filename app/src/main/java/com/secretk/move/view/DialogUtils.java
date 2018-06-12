package com.secretk.move.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.bean.ProjectTypeListBean;
import com.secretk.move.listener.ItemClickListener;
import com.secretk.move.ui.activity.MainActivity;
import com.secretk.move.ui.adapter.DiaLogListAdapter;
import com.secretk.move.utils.GlideUtils;
import com.secretk.move.utils.StringUtil;

import java.util.List;


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
     * 单个按钮，设置监听；
     *
     * @param context
     * @param
     */
    public static void showDialogImage(final Context context, String url, final ErrorDialogInterface anInterface) {
        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_image, null);
        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        GlideUtils.loadSideMaxImage(context,ivIcon,url);
        ivIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new PopupWindowUtils(context, new PopupWindowUtils.ErrorDialogInterface() {
                    @Override
                    public void btnConfirm() {
                        anInterface.btnConfirm();
                    }
                });
                return false;
            }
        });
        ivIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
        if(context.getString(R.string.set_my_name).equals(title)){
            etLogContent.setMaxLines(10);
            etLogContent.setHint("请输入昵称");

        }
        if(context.getString(R.string.set_my_signature).equals(title)){
            etLogContent.setMaxLines(20);
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
    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 赞赏
     */
    public static void showGiveRewardDialog(final Context context, String title, String content, final EditTextDialogInterface editTextDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_give_reward, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        final TextView tvGive1 = view.findViewById(R.id.tv_give_1);
        final TextView tvGive2 = view.findViewById(R.id.tv_give_2);
        final TextView tvGive3 = view.findViewById(R.id.tv_give_3);
        final TextView tvGive4 = view.findViewById(R.id.tv_give_4);
        final TextView tvGive5 = view.findViewById(R.id.tv_give_5);
        final TextView tvGive6 = view.findViewById(R.id.tv_give_6);
        final EditText etLogContent = view.findViewById(R.id.et_log_content);
        TextView tvLogPrompt = view.findViewById(R.id.tv_log_prompt);
        TextView tvLogConfirm = view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = view.findViewById(R.id.tv_log_cancel);
        tvLogPrompt.setText(title);
        if(StringUtil.isNotBlank(content)){
            etLogContent.setText(content);
        }
        if(context.getString(R.string.sponsor_title).equals(title)){
            etLogContent.setHint("请输入赞助金额");
            etLogContent.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        dialog.setContentView(view);
        dialog.show();
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
                        editTextDialogInterface.btnConfirm(season);
                        dialog.dismiss();
                        break;
                    case R.id.tv_log_cancel:
                        dialog.dismiss();
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
        tvLogCancel.setOnClickListener(giveReward);
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

    public interface ErrorDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm();
    }
    public interface ListDialogInterface {
        /**
         * 确定
         * @param postion
         */
        public void btnConfirm(int postion);
    }

    public interface EditTextDialogInterface {
        /**
         * 确定
         *
         * @param season
         */
        public void btnConfirm(String season);
    }

    /**
     * 单个按钮，设置监听；
     *
     * @param context
     * @param
     */
    public static void showListView(Context context, List<ProjectTypeListBean.DataBean.ProjectTypesBean> list, final ListDialogInterface anInterface) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dia_list_view, null);
        RecyclerView bt_ok = view.findViewById(R.id.lv_list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        bt_ok.setLayoutManager(manager);
        DiaLogListAdapter adapter = new DiaLogListAdapter(context);
        bt_ok.setAdapter(adapter);
        adapter.setData(list);
        adapter.setItemListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                anInterface.btnConfirm(postion);
                dialog5.dismiss();
            }

            @Override
            public void onItemLongClick(View view, int postion) {}
        });

        dialog5.setContentView(view);
        dialog5.show();
    }
    /**
     * 单个按钮，设置监听；
     *
     * @param context
     * @param
     */
    public static void showDialogAppUpdate(final Context context, boolean force,String contact, final ErrorDialogInterface anInterface) {
        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_app_update, null);
        TextView tv_update_name = view.findViewById(R.id.tv_update_name);
        TextView tv_bnt = view.findViewById(R.id.tv_bnt);
        ImageView iv_cloes = view.findViewById(R.id.iv_cloes);
//        if(force){
//            iv_cloes.setVisibility(View.GONE);
//        }
        tv_update_name.setText(contact);
        tv_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anInterface.btnConfirm();
                dialog5.dismiss();
            }
        });
        iv_cloes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog5.dismiss();
            }
        });
        dialog5.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if(context instanceof MainActivity){
                    ((MainActivity)context).finish();
                }
            }
        });
        dialog5.setCanceledOnTouchOutside(false);
        dialog5.setContentView(view);
        dialog5.show();
    }
    /**
     * 单个按钮，提示框；
     *
     * @param context
     * @param
     */
    public static void showDialogHint(Context context, String title, boolean isOne,final ErrorDialogInterface dialogConfirm) {

            final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
            final View view = LayoutInflater.from(context).inflate(R.layout.dialog_hine, null);
            TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
            TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(title);
            if(isOne){
                suanle.setVisibility(View.GONE);
            }
            suanle.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    dialog5.dismiss();
                }
            });
            bt_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogConfirm.btnConfirm();
                    dialog5.dismiss();
                }
            });
            dialog5.setCancelable(false);
            dialog5.setContentView(view);
            dialog5.show();
        }


}
