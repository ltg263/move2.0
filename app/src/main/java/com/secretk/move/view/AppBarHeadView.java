package com.secretk.move.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.BaseManager;

/**
 * 通用的标题栏
 * Created by litongge on 2017/5/14.
 */
public class AppBarHeadView extends FrameLayout {

    protected View mAppBarLayout;
    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected TextView mTitleVice;
    protected ImageView mImg;
    protected String setText;
    public boolean isHeadBackShow() {
        return headBackShow;
    }

    public void setHeadBackShow(boolean headBackShow) {
        this.headBackShow = headBackShow;
    }

    private boolean headBackShow = false;

    public AppBarHeadView(Context context) {
        super(context);
        initView(context,null);
    }

    public AppBarHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a=getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
        setText=a.getString(R.styleable.TitleBar_setTitle);
        initView(context,attrs);
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    public int initAppBar(){
        return R.layout.head_toolbar;
    }

    public View getAppBarLayout() {
        return mAppBarLayout;
    }

    protected void initView(Context context, AttributeSet attrs) {
        mAppBarLayout =  LayoutInflater.from(context).inflate(initAppBar(), this);
        if (mAppBarLayout != null) {
            mToolbar =  mAppBarLayout.findViewById(R.id.head_toolbar);
            mToolbar.setTitle("");
            mTitle = mAppBarLayout.findViewById(R.id.tv_head_title);
            mTitleVice = mAppBarLayout.findViewById(R.id.tv_head_vice);
            mImg = mAppBarLayout.findViewById(R.id.iv_head_img);
            mTitle.setText(setText);
        }
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }
    public void setTitleVice(String title) {
        mTitleVice.setText(title);
    }
    public void setHeadViewColor(int color) {
        mAppBarLayout.setBackgroundResource(color);
        mToolbar.setBackgroundResource(color);
        mAppBarLayout.setBackgroundColor(ContextCompat.getColor(getContext(),color));
        mToolbar.setBackgroundColor(ContextCompat.getColor(getContext(),color));
    }
    public void setTitleColor(int color) {
        mTitle.setTextColor(BaseManager.app.getResources().getColor(color));
    }
    public void setTitleSize(int size) {
        mTitle.setTextSize(size);
    }
    public int getTitleHeight() {
        return mTitle.getHeight();
    }
    public TextView getTextView(){
        return mTitle;
    }
    public ImageView getImageView(){
        return mImg;
    }

}
