package com.secretk.move.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.secretk.move.R;
import com.secretk.move.baseManager.BaseManager;


public class LoadingDialog {

	private View loadingRing;
	private TextView loadingMsg;
	private ImageView loadingLogo;
	private Dialog dialog;

	public LoadingDialog(Activity ownerActivity) {
		dialog = new Dialog(ownerActivity, R.style.RemindDialog);
		LayoutInflater inflater = (LayoutInflater) BaseManager.app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.dialog_loading,null);
		loadingRing = view.findViewById(R.id.iv_loading_ring);
		loadingMsg =  view.findViewById(R.id.tv_loading_text);
		loadingLogo =  view.findViewById(R.id.iv_loading_logo);
		dialog = new Dialog(ownerActivity, R.style.RemindDialog);
		dialog.getWindow().setContentView(view);
		dialog.setCancelable(true);
		dialog.setCanceledOnTouchOutside(false);
	}

	public void show() {
		if (dialog != null && !dialog.isShowing()) {
			RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotate.setDuration(1000);
			rotate.setRepeatCount(Animation.INFINITE);
			rotate.setRepeatMode(Animation.RESTART);
			rotate.setInterpolator(new LinearInterpolator());
			loadingRing.startAnimation(rotate);
			dialog.show();
		}
	}

	public void setOwnerActivity(Activity activity) {
		dialog.setOwnerActivity(activity);
	}

	public boolean isShowing() {
		return dialog != null && dialog.isShowing();
	}

	public void setCancelable(boolean flag) {
		dialog.setCancelable(flag);
	}

	public void setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
	}

	public void setMsg(String msg) {
		loadingMsg.setText(msg);
	}

	public void setLogo(Bitmap bm) {
		loadingLogo.setImageBitmap(bm);
	}

	public void setLogo(int resId) {
		loadingLogo.setImageResource(resId);
	}

	public void dismiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
