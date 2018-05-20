package com.secretk.move.customview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.secretk.move.MoveApplication;
import com.secretk.move.R;
import com.secretk.move.bean.SearchedBean;
import com.secretk.move.utils.UiUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 快速索引栏
 *
 * @author jyh
 */
public class QuickIndexBar extends View {


    private Paint mPaint;

    private int cellWidth;

    private int mHeight;

    private float cellHeight;

    int touchIndex = -1;
    private Context mContext;


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
        initPop();
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initPop();
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
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
        if (list==null)
            return;
        for (int i = 0; i < list.size(); i++) {
            String letter = list.get(i);
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
            case MotionEvent.ACTION_MOVE:
                panduan(event);
                break;
            case MotionEvent.ACTION_UP:
                this.setBackgroundColor(Color.TRANSPARENT);
                touchIndex = -1;

                if (pop != null) {
                    pop.dismiss();
                }

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
        float x = event.getX();
        // 根据当前的y值, 除以单元格高度, 获取当前按下字母的索引
        index = (int) (y / cellHeight);


        if (index >= 0 && index < list.size()) {
            // 判断跟刚刚的索引是否一致, 不一致才显示出来
            if (index != touchIndex) {
                // 得到按下的字母

                String letter = list.get(index);
                if (onLetterChangeListener != null) {
                    onLetterChangeListener.onLetterChange(letter);
                }


                int pop_y = (int) event.getRawY();
                pop.showAtLocation(this, Gravity.LEFT, UiUtils.dip2px(20), pop_y - UiUtils.getWindowWidth() / 2 - UiUtils.dip2px(110));
                tvShow.setText(letter);
                pop.update();
                tvShow.invalidate();

                touchIndex = index;
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        cellWidth = getMeasuredWidth();

        mHeight = getMeasuredHeight();

        cellHeight = mHeight * 1.0f / 27;
    }

    public int dip2px(int dip) {
        // 获取dp和px的转换关系的变量
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    private List<String> list;

    public void setData(List<String> list) {
        this.list = list;
        postInvalidate();
    }
    public void setDatax( List<SearchedBean.Projects> xlist) {
        for (int i=0;i<xlist.size();i++){
            String str=xlist.get(i).getProjectCode().charAt(0)+"";
            if (list==null){
                list=new ArrayList<>();
            }
            this.list.add(str.toUpperCase());
        }
        removeDuplicateWithOrder( this.list);
    }
    public  void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        postInvalidate();
    }
    public PopupWindow pop;
    public TextView tvShow;

    public void initPop() {

        View contentView = LayoutInflater.from(MoveApplication.getContext()).inflate(R.layout.fragment_topic_pop, null);
        tvShow = contentView.findViewById(R.id.tvShow);
        pop = new PopupWindow(contentView, RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 指定 PopupWindow 的背景
        pop.setFocusable(true);                   // 设定 PopupWindow 取的焦点，创建出来的 PopupWindow 默认无焦点


    }
}
