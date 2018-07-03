package com.secretk.move.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.secretk.move.R;


public class MyLetterListView extends View {

	OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	public static String[] myLetter = {"#","A", "B", "C", "D", "E", "F", "G", "H", "J", "K","L", "M", "N", "P", "Q", "R", "S", "T",  "W", "X","Y", "Z"};

	int choose = -1;
	Paint paint = new Paint();
	boolean showBkg = false;

	public MyLetterListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyLetterListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyLetterListView(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (showBkg) {
			canvas.drawColor(Color.parseColor("#40000000"));
		}

		int height = getHeight();
		int width = getWidth();
		int singleHeight = height / myLetter.length;
		for (int i = 0; i < myLetter.length; i++) {
			paint.setColor(getResources().getColor(R.color.title_gray_66));
			paint.setTextSize(30);
			paint.setTypeface(Typeface.DEFAULT);
			paint.setAntiAlias(false);
			if (i == choose) {
				paint.setColor(getResources().getColor(R.color.app_background));
				paint.setFakeBoldText(false);
			}
			float xPos = width / 2 - paint.measureText(myLetter[i]) / 2;
			float yPos = singleHeight * i + singleHeight;
			canvas.drawText(myLetter[i], xPos, yPos, paint);
			paint.reset();
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float y = event.getY();
		final int oldChoose = choose;
		final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y / getHeight() * myLetter.length);

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			showBkg = false;
			if (oldChoose != c && listener != null) {
				if (c < myLetter.length) {
					listener.onTouchingLetterChanged(myLetter[c]);
					choose = c;
					invalidate();
				}
			}

			break;
		case MotionEvent.ACTION_MOVE:
			if (oldChoose != c && listener != null) {
				if (c >= 0 &&c < myLetter.length) {
					listener.onTouchingLetterChanged(myLetter[c]);
					choose = c;
					invalidate();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			showBkg = false;
			choose = -1;
			invalidate();
			break;
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	public interface OnTouchingLetterChangedListener {
		public void onTouchingLetterChanged(String s);
	}

}
