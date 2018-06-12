package com.secretk.move.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.ui.activity.EvaluationCompileListActivity;
import com.secretk.move.ui.activity.EvaluationSimplenessActivity;
import com.secretk.move.utils.StringUtil;

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
    Context context;
    public EvaluationSliderView(Context context) {
        super(context);
        initView(context, null);
    }

    public EvaluationSliderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
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
        setScore(8);
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
                setEvaluate(StringUtil.getStateValueStr(Float.valueOf(strValue)));
                setHdListener(Float.valueOf(strValue));
            }
        });
    }

    private void setHdListener(float value) {
        Activity activity = (Activity)context;
        if((activity  instanceof EvaluationCompileListActivity)){
            ((EvaluationCompileListActivity) activity).setComprehensiveGrade(getTvDimensionalityName(),value);
        }else if(activity instanceof EvaluationSimplenessActivity){
            ((EvaluationSimplenessActivity) activity).setTvEvaluationState(StringUtil.getStateValueStr(value));
        }
    }

    public void setCompileState(final Compile compile){
        tvDimensionalityCompile.setVisibility(View.VISIBLE);
        tvDimensionalityEvaluate.setVisibility(View.GONE);
        tvDimensionalityCompile.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                compile.OnClick(view);
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
        setEvaluate(StringUtil.getStateValueStr(score));
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
     * 设置滑动
     * @param side
     */
    public void setSetSlide(boolean side){
        esView.setSetSlide(side);
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
    public abstract static class Compile{
        public abstract void OnClick(View view);
    }
}
