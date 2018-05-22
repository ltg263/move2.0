package com.secretk.move.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.utils.LogUtil;
import com.secretk.move.utils.StringUtil;

/**
 * 作者： litongge
 * 时间： 2018/4/28 19:56
 * 邮箱；ltg263@126.com
 * 描述：进度条的样式
 *
 */
public class ProgressBarStyleView extends FrameLayout {

    protected String setOneTitle;
    protected String setTwoTitle;
    protected String setThreeTitle;
    protected View PbView;
    protected TextView tvOneTitle;//默认：Color #333333  Size：14sp
    protected TextView tvTwoTitle;//默认：Color #888888  Size：12sp
    protected TextView tvThreeTitle;//默认：Color #3b88f6  Size：14sp
    protected ProgressBar pbProgress;//默认：pb_view_grade
    protected ProgressBar pbProgressMax;//默认：pb_view_grade
    private ImageView ivRightIcon;

    public void setTvOne(String strOneTitle,float strOneSize,int strOneColor) {
        tvOneTitle.setText(strOneTitle);
        if(strOneSize!=0){
            tvOneTitle.setTextSize(strOneSize);
        }
        if(strOneColor!=0){
            tvOneTitle.setTextColor(strOneColor);
        }
    }
    public void setTvTwo(String strTwoTitle,float strTwoSize,int strTwoColor) {
        if(StringUtil.isNotBlank(strTwoTitle)){
            tvTwoTitle.setText(strTwoTitle);
            ivRightIcon.setVisibility(View.VISIBLE);
        }
        if(strTwoSize!=0){
            tvTwoTitle.setTextSize(strTwoSize);
        }
        if(strTwoColor!=0){
            tvTwoTitle.setTextColor(strTwoColor);
        }
    }
    public void setTvThree(double strThreeTitle,float strThreeSize,int colorId) {
        tvThreeTitle.setText(String.valueOf(strThreeTitle));
        setProgress((int) (strThreeTitle*10));
        if(strThreeSize!=0){
            tvThreeTitle.setTextSize(strThreeSize);
        }
        if(colorId!=0){
            tvThreeTitle.setTextColor(getResources().getColor(colorId));
        }
    }
    public void setAllTv(String ont,String two,double three) {
        if(StringUtil.isNotBlank(two)){
            ivRightIcon.setVisibility(View.VISIBLE);
        }
        tvOneTitle.setText(ont);
        tvTwoTitle.setText(two);
        tvThreeTitle.setText(String.valueOf(three));
        setProgress((int) (three*10));
    }

    public void setProgressDrawable(int id,int colorId){
        Drawable progressDrawable= getResources().getDrawable(id);
        pbProgress.setProgressDrawable(progressDrawable);
        pbProgressMax.setProgressDrawable(progressDrawable);
        tvThreeTitle.setTextColor(getResources().getColor(colorId));
    }
    public void setProgress(int progress){
        pbProgress.setProgress(progress);
        pbProgressMax.setProgress(progress);

    }


    public ProgressBarStyleView(Context context) {
        super(context);
        initView(context, null);
    }

    public ProgressBarStyleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray pbTitle = getContext().obtainStyledAttributes(attrs, R.styleable.PbTitle);
        setOneTitle = pbTitle.getString(R.styleable.PbTitle_setOneTitle);
        setTwoTitle = pbTitle.getString(R.styleable.PbTitle_setTwoTitle);
        setThreeTitle = pbTitle.getString(R.styleable.PbTitle_setThreeTitle);
        initView(context, attrs);
    }

    public int initAppBar() {
        return R.layout.progress_bar_view;
    }

    protected void initView(Context context, AttributeSet attrs) {
        PbView = LayoutInflater.from(context).inflate(initAppBar(), this);
        if (PbView != null) {
            tvOneTitle = PbView.findViewById(R.id.tv_title);
            tvTwoTitle = PbView.findViewById(R.id.tv_head_level);
            ivRightIcon = PbView.findViewById(R.id.iv_right_icon);
            tvThreeTitle = PbView.findViewById(R.id.tv_score);
            pbProgress = PbView.findViewById(R.id.pb_progress);
            pbProgressMax = PbView.findViewById(R.id.pb_progress_max);

            tvOneTitle.setText(setOneTitle);
            tvTwoTitle.setText(setTwoTitle);
            tvThreeTitle.setText(setThreeTitle);
        }
    }

    public void setPbProgressMaxVisible() {
        pbProgress.setVisibility(View.GONE);
        pbProgressMax.setVisibility(View.VISIBLE);
    }
}
