package com.secretk.move.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * ScrollView 和 RecycleView嵌套滑动惯性
 * Created by ltg263 on 2016/7/11.
 */
public class RecycleNestedScrollView extends NestedScrollView {
    private final int mTouchSlop;
    private int downY = 0;

    public RecycleNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取系统设置的滑动惯性灵敏度
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
