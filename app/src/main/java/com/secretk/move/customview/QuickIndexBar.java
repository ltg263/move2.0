package com.secretk.move.customview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * 快速索引栏
 *
 * @author jyh
 */
public class QuickIndexBar extends View {

    private static final String[] LETTERS = new String[]{"#", "A", "B", "C", "D",
            "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private Paint mPaint;

    private int cellWidth;

    private int mHeight;

    private float cellHeight;
    private TextView showTv;
    int touchIndex = -1;
  private Context mContext;
    public void addBundleView(TextView tv) {
        showTv = tv;
    }

    /**
     * 字母变化监听
     *
     * @author poplar
     */
    public interface OnLetterChangeListener {
        void onLetterChange(String letter);
    }

    private OnLetterChangeListener onLetterChangeListener;

    public OnLetterChangeListener getOnLetterChangeListener() {
        return onLetterChangeListener;
    }

    public void setOnLetterChangeListener(
            OnLetterChangeListener onLetterChangeListener) {
        this.onLetterChangeListener = onLetterChangeListener;
    }

    public QuickIndexBar(Context context) {
        this(context, null);

    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext=context;
        // 初始化画笔, 抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        // 设置字体加粗
        //	mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(dip2px(12));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            int x = (int) (cellWidth * 0.5f - mPaint.measureText(letter) * 0.5f);

            // 文本所在的矩形, 获取文本的高度
            Rect bounds = new Rect();
            mPaint.getTextBounds(letter, 0, letter.length(), bounds);
            int textHeight = bounds.height();

            // 如果当前绘制时, 索引是我们按下的字母, 设置画笔为灰色, 否则设置为白色
            mPaint.setColor(touchIndex == i ? Color.BLUE : Color.parseColor("#666666"));

            int y = (int) (cellHeight * 0.5f + textHeight * 0.5f + i
                    * cellHeight);

            canvas.drawText(letter, x, y, mPaint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = 0;
        int index = -1;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.setBackgroundColor(Color.GRAY);
                panduan(event);
                break;
            case MotionEvent.ACTION_MOVE:
                panduan(event);
                break;
            case MotionEvent.ACTION_UP:
                this.setBackgroundColor(Color.TRANSPARENT);
                touchIndex = -1;
                showTv.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        // 由本View消费触摸事件
        return true;
    }

    private void panduan(MotionEvent event) {
        float y;
        int index;
        y = event.getY();
        // 根据当前的y值, 除以单元格高度, 获取当前按下字母的索引
        index = (int) (y / cellHeight);

        if (index >= 0 && index < LETTERS.length) {

            // 判断跟刚刚的索引是否一致, 不一致才显示出来
            if (index != touchIndex) {
                // 得到按下的字母
                String letter = LETTERS[index];

                if (onLetterChangeListener != null) {
                    onLetterChangeListener.onLetterChange(letter);
                }
                showTv.setVisibility(View.VISIBLE);
                touchIndex = index;
                showTv.setText(LETTERS[touchIndex] + "");
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cellWidth = getMeasuredWidth();

        mHeight = getMeasuredHeight();
        cellHeight = mHeight * 1.0f / LETTERS.length;
    }

    public  int dip2px(int dip) {
        // 获取dp和px的转换关系的变量
        float density =  mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }
}
