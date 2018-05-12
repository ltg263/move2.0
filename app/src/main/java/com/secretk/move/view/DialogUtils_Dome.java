package com.secretk.move.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;


/**
 * 李同革
 * DiaLog 对话框
 */
public class DialogUtils_Dome {

    private static EditText tvLogContent;

    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     */
  /* public static void ShowUnifiedDialog(final Context context, String[] content, final UnifiedDialogInterface unifiedDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_unified_prompt, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tvLogPrompt = (TextView) view.findViewById(R.id.tv_log_prompt);
        TextView tvLogContent = (TextView) view.findViewById(tv_log_content);
        TextView tvLogConfirm = (TextView) view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = (TextView) view.findViewById(R.id.tv_log_cancel);

        tvLogPrompt.setText(content[0]);
        tvLogContent.setText(content[1]);
        // 确定
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                unifiedDialogInterface.btnConfirm();
                dialog.dismiss();
            }

        });
        // 取消
        tvLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                unifiedDialogInterface.btnCancel();
                dialog.dismiss();
            }
        });
    }*/
    /**
     * 描述: 自定义ShowUnifiedDialog
     * 统一 确认取消的Dialog
     *//*
    public static void ShowEditTextDialog(final Context context, String title, final EditTextDialogInterface editTextDialogInterface) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_with_edittext, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        dialog.setContentView(view);
        dialog.show();
        TextView tvLogPrompt = (TextView) view.findViewById(R.id.tv_log_prompt);
        tvLogContent = (EditText) view.findViewById(tv_log_content);
        TextView tvLogConfirm = (TextView) view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = (TextView) view.findViewById(R.id.tv_log_cancel);

        tvLogPrompt.setText(title);
        // 确定
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String season = tvLogContent.getText().toString().trim();
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

    *//**
     * 单个按钮，不设置监听
     * @param context
     * @param title
     *//*
    public static void showDialogNoListen(Context context, String title) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_exit, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_confirm);
        TextView suanle = (TextView) view.findViewById(R.id.bt_suanle);
        suanle.setVisibility(View.GONE);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setText(title);
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }


    *//**
     * 单个按钮，不设置监听；
     * @param context
     * @param text
     *//*
    public static void showDialogPasswordError(Context context, String text) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_dialog_layout_ok);

        TextView tv_body = (TextView) view.findViewById(R.id.tv_body);
        tv_body.setText(text);

        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }
    *//**
     * 单个按钮，设置监听；
     * @param context
     * @param text
     *//*
    public static void showDialogError(Context context, final String text, final EditTextDialogInterface anInterface) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_dialog_layout_ok);

        TextView tv_body = (TextView) view.findViewById(R.id.tv_body);
        tv_body.setText(text);

        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                anInterface.btnConfirm(text);
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }

    *//**
     * 两个按钮
     * 确定按钮：关闭
     * 创建公司：跳转创建公司网页
     *
     * 提示信息：已提交管理员审核，审核通过后会收到短信通知，请耐心等候！
     * @param context
     *//*
    public static void showDialogWaittingApply (Context context) {
        final Context mContext  = context;
        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout_create_company, null);
        TextView bt_ok = (TextView) view.findViewById(R.id.bt_dialog_layout_ok);
        TextView bt_dialog_layout_create_company = (TextView) view.findViewById(R.id.bt_dialog_layout_create_company);

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog5.dismiss();
            }
        });

        bt_dialog_layout_create_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", Constants.URL_ADD_COMPANY_LOGIN_FROM);
                mContext.startActivity(intent);
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }


    *//**
     * 描述: 自定义Dialog
     * 云企的Dialog
     *//*
    public static void ShowDialog(Context context, final Dialog_Exit.DialogConfirm dialogConfirm, String title) {
        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_unified_prompt, null);
        TextView tvLogPrompt = (TextView) view.findViewById(R.id.tv_log_prompt);
        TextView bt_ok = (TextView) view.findViewById(R.id.tv_log_confirm);
        TextView suanle = (TextView) view.findViewById(R.id.tv_log_cancel);
        TextView tv_title = (TextView) view.findViewById(tv_log_content);
        tvLogPrompt.setText("提示");
        tv_title.setText(title);
        suanle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog5.dismiss();
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog5.dismiss();
                dialogConfirm.confirm();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }


    *//**
     * 选择添加员工的方式
     * @param context
     *//*
    public static void showDialogAddStaff(Context context, final GetPosition getPosition) {

        final Dialog dialog5 = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_staff, null);
        RelativeLayout rlSdAdd = (RelativeLayout) view.findViewById(R.id.rl_sd_add);
        RelativeLayout rlDrAdd = (RelativeLayout) view.findViewById(R.id.rl_dr_add);
        RelativeLayout rlYqAdd = (RelativeLayout) view.findViewById(R.id.rl_yq_add);
        if(!Constants.FEN_XIANG){
            view.findViewById(R.id.view).setVisibility(View.GONE);
            rlYqAdd.setVisibility(View.GONE);
        }
        rlSdAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosition.getPosition(0);
                dialog5.dismiss();
            }
        });
        rlDrAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosition.getPosition(1);
                dialog5.dismiss();
            }
        });
        rlYqAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosition.getPosition(2);
                dialog5.dismiss();
            }
        });

        dialog5.setContentView(view);
        dialog5.show();
    }

    public interface GetPosition{
        void getPosition(int position);
    }


    *//**
     * 检查更新
     * @param context：上下文
     * @param content：内容  数组长度必须为4
     *  @param sign: 内容是否居中
     * @param dialogConfirm
     *//*
    public static void showUpdateDialog(Context context, String[] content,boolean sign, final DialogConfirm dialogConfirm) {

        final Dialog dialog = new Dialog(context, R.style.selectorDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.dialog_unified_prompt_update, null);
        TextView tvLogCancel = (TextView) view.findViewById(R.id.tv_log_cancel);
        TextView tvLogConfirm = (TextView) view.findViewById(R.id.tv_log_confirm);
        TextView tvUpdateContent = (TextView) view.findViewById(R.id.tv_update_content);
        TextView tvUpdateSize = (TextView) view.findViewById(R.id.tv_update_size);
        TextView tvUpdate = (TextView) view.findViewById(R.id.tv_update);
        tvUpdateContent.setText(content[0]);
        if(sign){
            tvUpdate.setVisibility(View.VISIBLE);
            tvUpdateSize.setVisibility(View.GONE);
            tvUpdate.setText(content[1]);

        }else{
            tvUpdateSize.setText(content[1]);
            tvUpdate.setVisibility(View.GONE);
            tvUpdateSize.setVisibility(View.VISIBLE);
        }
        tvLogCancel.setText(content[2]);
        tvLogConfirm.setText(content[3]);
        tvLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                dialogConfirm.confirm();
            }
        });

        dialog.setContentView(view);
        dialog.show();
    }

    public interface DialogConfirm {
        void confirm();
    }*/


    /**
     * 描述: 自定义alertdialog
     * 更换头像和修改性别共同调用
     */
    public static void ShowAlertDialog(final Context context, String[] content, final AlertDialogInterface alertDialogInterface) {

        View view = LayoutInflater.from(context).inflate(R.layout.change_head, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        dialog.setContentView(view);
        dialog.show();
        RelativeLayout localPicLayout = (RelativeLayout) view.findViewById(R.id.localPicLayout);//从本地获取照片按钮
        RelativeLayout takePicLayout = (RelativeLayout) view.findViewById(R.id.takePicLayout);
        TextView tvLogTop = (TextView) view.findViewById(R.id.tv_log_top);
        TextView tvLogZhong = (TextView) view.findViewById(R.id.tv_log_zhong);
        TextView tvLogBottom = (TextView) view.findViewById(R.id.tv_log_bottom);
        ImageView ivXbNan = (ImageView) view.findViewById(R.id.iv_xb_nan);
        ImageView ivXbNv = (ImageView) view.findViewById(R.id.iv_xb_nv);

        tvLogTop.setText(content[0]);
        tvLogZhong.setText(content[1]);
        tvLogBottom.setText(content[2]);
        if (!content[0].equals("修改头像")) {
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
                alertDialogInterface.btnLineOne();
                dialog.dismiss();
            }

        });
        // 取消
        takePicLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                alertDialogInterface.btnLineTo();
                dialog.dismiss();
            }

        });
    }

    public interface AlertDialogInterface {

        public void btnLineOne();

        public void btnLineTo();

    }


    public interface UnifiedDialogInterface {
        /**
         * 确定
         */
        public void btnCancel();

        /**
         * 取消
         */
        public void btnConfirm();

    }
    public interface EditTextDialogInterface {
        /**
         * 确定
         */
        public void btnConfirm(String reason);

    }

    //****************************************************************************************
    /**
     * 描述: 自定义ShowDataDialog
     * 出生日期选择控件
     */
    /*public static void ShowDataDialog(final Context context, long data, final UnifiedDialogInterface unifiedDialogInterface) {
        //根据制定日期 得到年月日
        final int yyyy = Integer.parseInt(getSdfDta("yyyy", data));
        final int mm = Integer.parseInt(getSdfDta("MM", data));
        final int dd = Integer.parseInt(getSdfDta("dd", data));
        final int currentY = Integer.valueOf(getSdfDta("yyyy", System.currentTimeMillis()));
        final List<String> listY = getListY(currentY);
        final List<String> listM = getListM();
        final List<String> listD = getListD(yyyy, mm);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_bate, null);// 获得dialog布局
        final Dialog dialog = new Dialog(context, R.style.RemindDialog);
        final TextView tvLogPrompt = (TextView) view.findViewById(R.id.tv_log_prompt);
        TextView tvLogConfirm = (TextView) view.findViewById(R.id.tv_log_confirm);
        TextView tvLogCancel = (TextView) view.findViewById(R.id.tv_log_cancel);
        final WheelView wvDataY = (WheelView) view.findViewById(R.id.wv_data_y);
        final WheelView wvDataM = (WheelView) view.findViewById(R.id.wv_data_m);
        final WheelView wvDataD = (WheelView) view.findViewById(R.id.wv_data_d);
        tvLogPrompt.setText(getSdfDta("yyyy年MM月dd日", data));

        //设置选中与未选中字体的样式
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 18;
        style.textSize = 14;
        style.holoBorderColor = Color.parseColor("#5eb3ee");
        style.selectedTextColor = Color.parseColor("#5eb3ee");

        //年
        wvDataY.setWheelAdapter(new ArrayWheelAdapter(context));
        wvDataY.setWheelData(listY);
        wvDataY.setWheelSize(3);
        wvDataY.setStyle(style);
        wvDataY.setLoop(false);
        wvDataY.setSkin(WheelView.Skin.Holo); // common皮肤
        for (int i = 0; i < listY.size(); i++) {
            if (listY.get(i).contains(String.valueOf(yyyy))) {
                wvDataY.setSelection(i);
            }
        }
        //月
        wvDataM.setWheelAdapter(new ArrayWheelAdapter(context));
        wvDataM.setWheelData(listM);
        wvDataM.setWheelSize(3);
        wvDataM.setStyle(style);
        wvDataM.setLoop(true);
        wvDataM.setSkin(WheelView.Skin.Holo); // common皮肤
        wvDataM.setSelection(mm - 1);
        //日
        wvDataD.setWheelAdapter(new ArrayWheelAdapter(context));
        wvDataD.setWheelData(listD);
        wvDataD.setWheelSize(3);
        wvDataD.setStyle(style);
        wvDataD.setLoop(true);
        wvDataD.setSkin(WheelView.Skin.Holo); // common皮肤
        wvDataD.setSelection(dd - 1);
        dialog.setContentView(view);
        dialog.show();
        *//**
         * 根据年份的来显示对应的日期
         *//*
        final WheelView.OnWheelItemSelectedListener wheelItemSelectedListener = new WheelView.OnWheelItemSelectedListener<String>() {
            int signI = 0;

            @Override
            public void onItemSelected(int position, String s) {
                String seleY = (String) wvDataY.getSelectionItem();
                String seleM = (String) wvDataM.getSelectionItem();
                String seleD = (String) wvDataD.getSelectionItem();
                String str = seleY + seleM + seleD;
                if (signI < 4) {
                    signI++;
                    return;
                }
                if (s.contains("年")) {
                } else if (s.contains("日")) {
                } else if (s.contains("月")) {
                    List<String> listD = getListD(Integer.valueOf(getNumbers(seleY)), position + 1);
                    wvDataD.resetDataFromTop(listD);
                    int pos = 0;
                    if (Integer.valueOf(getNumbers(seleD)) >= listD.size()) {
                        pos = listD.size() - 1;
                    } else {
                        pos = Integer.parseInt(getNumbers((String) wvDataD.getSelectionItem())) - 1;
                    }
                    wvDataD.setSelection(pos);
                }
                if(!s.contains("月")){
                    tvLogPrompt.setText(str);
                }
            }
        };

        wvDataY.setOnWheelItemSelectedListener(wheelItemSelectedListener);
        wvDataM.setOnWheelItemSelectedListener(wheelItemSelectedListener);
        wvDataD.setOnWheelItemSelectedListener(wheelItemSelectedListener);

        // 确定
        tvLogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setBirthDate(StringUtil.getDataLong(tvLogPrompt.getText().toString()));
                unifiedDialogInterface.btnConfirm();
                dialog.dismiss();
            }

        });
        // 取消
        tvLogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                unifiedDialogInterface.btnCancel();
                dialog.dismiss();
            }
        });
    }

    private static long birthDate;

    public static void setBirthDate(long birth) {
        birthDate = birth;
    }

    public static long getBirthDate() {
        return birthDate;
    }

    *//**
     * 截取数字
     *//*
    private static String getNumbers(String content) {
        Matcher matcher = Pattern.compile("\\d+").matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";

    }

    *//**
     * * @return 最近100年的集合
     *//*
    public static List<String> getListY(int currentY) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(currentY - i + "年");
        }
        return list;
    }

    *//**
     * @return 12个月
     *//*
    private static List<String> getListM() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(i + "月");
        }
        return list;
    }

    *//**
     * @param year  ：年
     * @param month ：月
     * @return 当前月的天数
     *//*
    private static List<String> getListD(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        List<String> list = new ArrayList<>();
        for (int i = 1; i < maxDate + 1; i++) {
            list.add(i + "日");
        }
        return list;
    }

    *//**
     * 根据指定的时间格式 获取日期
     *
     * @param type ：指定格式
     * @return ：日期
     *//*
    public static String getSdfDta(String type, long data) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        return sdf.format(data);
    }*/
    //****************************************************************************************
}
