package com.secretk.move.view;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;

/**
 * 作者： litongge
 * 时间：  2018/5/18 15:53
 * 邮箱；ltg263@126.com
 * 描述：自定義頻分
 */
public class EvaluationSliderView extends FrameLayout {


    TextView tvDimensionalityName;
    TextView tvDimensionalityCompile;
    RelativeLayout rlState;
    TextView tvEvaluationMun;
    EvaluationItemView esView;
    RelativeLayout rl;
    private View esv;
    private TextView tvDimensionalityEvaluate;

    public EvaluationSliderView(Context context) {
        super(context);
        initView(context, null);
    }

    public EvaluationSliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public int initAppBar() {
        return R.layout.evaluation_slider_view;
    }

    protected void initView(Context context, AttributeSet attrs) {
        esv = LayoutInflater.from(context).inflate(initAppBar(), this);
        tvDimensionalityName = esv.findViewById(R.id.tv_dimensionality_name);
        tvDimensionalityEvaluate = esv.findViewById(R.id.tv_dimensionality_evaluate);
        tvDimensionalityCompile = esv.findViewById(R.id.tv_dimensionality_compile);
        rlState = esv.findViewById(R.id.rl_state);
        tvEvaluationMun = esv.findViewById(R.id.tv_evaluation_mun);
        esView = esv.findViewById(R.id.es_view);
        rl = esv.findViewById(R.id.rl);
        setScore(6);
        setEsvBackground(R.color.app_background);
        esView.setOnValueChangeListener(new EvaluationItemView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                String strValue;
                if(value==(int)value){
                    strValue=String.valueOf((int)value);
                }else{
                    strValue=String.valueOf(value);
                }
                tvEvaluationMun.setText(strValue);
            }
        });
    }

    /**
     * 设置分数
     * @param score
     */
    public void setScore(float score){
        esView.setSelectorValue(score);
        tvEvaluationMun.setText(String.valueOf(score));
    }

    /**
     * 设置改变的颜色
     * @param color
     */
    public void setEsvBackground(int color){
        tvEvaluationMun.setTextColor(getResources().getColor(color));
        esView.setAlphaColor(color);
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{getResources().getColor(R.color.white),getResources().getColor(color)});
        rl.setBackground(gradientDrawable);
        rl.getBackground().setAlpha(25);
    }
    /**
     *  设置评价名称
     * @param valuate
     */
    public void setTvDimensionalityName(String valuate) {
        this.tvDimensionalityName.setText(valuate);
    }

    /**
     *  设置评价状态
     * @param valuate
     */
    public void setEvaluate(String valuate){
        tvDimensionalityEvaluate.setText(valuate);
    }

    /**
     *获取分数
     * @return
     */
    public String getTvEvaluationMun() {
        return tvEvaluationMun.getText().toString().trim();
    }
    /**
     *获取类型名称
     * @return
     */
    public String getTvDimensionalityName() {
        return tvDimensionalityName.getText().toString().trim();
    }
    /**
     *获取评价状态
     * @return
     */
    public String getTvDimensionalityEvaluate() {
        return tvDimensionalityEvaluate.getText().toString().trim();
    }
}
