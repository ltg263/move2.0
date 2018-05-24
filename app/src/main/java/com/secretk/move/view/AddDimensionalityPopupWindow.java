package com.secretk.move.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.secretk.move.R;
import com.secretk.move.utils.StringUtil;
import com.secretk.move.utils.ToastUtils;


public class AddDimensionalityPopupWindow extends PopupWindow implements OnClickListener{


    private final Button btnSave;
    private final Button btnCancel;
    private final EditText etEvaluationName;
    private final EditText etEvaluationWeight;
    private final EditText etEvaluationGrade;
    private final ImageView ivDeleteName;
    private final ImageView ivDeleteWeight;
    private final ImageView ivDeleteGrade;
    private final PopupOnClickListener itemsOnClick;
    private final Context context;
    private View mMenuView;

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_save:
                if(StringUtil.isBlank(getString(etEvaluationName))){
                    ToastUtils.getInstance().show(context.getString(R.string.evaluation_hint)+"名称");
                    return;
                }
                if(StringUtil.isBlank(getString(etEvaluationWeight))
                        || isNumTwo(getString(etEvaluationWeight))
                        || Float.valueOf(getString(etEvaluationWeight))==0
                        || Float.valueOf(getString(etEvaluationWeight))>1){
                    ToastUtils.getInstance().show(context.getString(R.string.evaluation_hint)+"权重");
                    return;
                }
                if(StringUtil.isBlank(getString(etEvaluationGrade))
                        || isNumTwo(getString(etEvaluationGrade))
                        || Float.valueOf(getString(etEvaluationGrade))<1
                        || Float.valueOf(getString(etEvaluationGrade))>10){
                    ToastUtils.getInstance().show(context.getString(R.string.evaluation_hint)+"分数");
                    return;
                }
                //销毁弹出框
                itemsOnClick.popupOnClick(view,etEvaluationName.getText().toString().trim(),
                        Float.valueOf(getString(etEvaluationWeight)),
                        Float.valueOf(getString(etEvaluationGrade)));
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.iv_delete_name:
                etEvaluationName.setText("");
                break;
            case R.id.iv_delete_grade:
                etEvaluationGrade.setText("");
                break;
            case R.id.iv_delete_weight:
                etEvaluationWeight.setText("");
                break;
        }
    }
    @SuppressLint("InflateParams")
    public AddDimensionalityPopupWindow(Activity context, final PopupOnClickListener itemsOnClick) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow_add_dimensionality, null);
        btnSave = mMenuView.findViewById(R.id.btn_save);
        btnCancel = mMenuView.findViewById(R.id.btn_cancel);
        etEvaluationName = mMenuView.findViewById(R.id.et_evaluation_name);
        etEvaluationWeight = mMenuView.findViewById(R.id.et_evaluation_weight);
        etEvaluationGrade = mMenuView.findViewById(R.id.et_evaluation_grade);
        ivDeleteName = mMenuView.findViewById(R.id.iv_delete_name);
        ivDeleteWeight = mMenuView.findViewById(R.id.iv_delete_weight);
        ivDeleteGrade = mMenuView.findViewById(R.id.iv_delete_grade);

        this.itemsOnClick = itemsOnClick;
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        ivDeleteName.setOnClickListener(this);
        ivDeleteWeight.setOnClickListener(this);
        ivDeleteGrade.setOnClickListener(this);


        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
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

    public String getString(EditText et){
        return et.getText().toString().trim();
    }
    public Boolean isNumTwo(String stra){
        String str = String.valueOf(stra);
        str = str.substring(str.indexOf('.') + 1);
        if(str.length()>2){
            return true;
        }
        return false;
    }
   public static abstract class PopupOnClickListener{
       public abstract void popupOnClick(View view, String name, float weight, float grade);
    }

    public void setEtValue(String name,Float grade,Float weight){
        etEvaluationName.setText(name);
        etEvaluationGrade.setText(String.valueOf(grade));
        etEvaluationWeight.setText(String.valueOf(weight));
    }


}
