package com.karen.processbutton;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class PracticProcessButton extends ProcessButton {

	public PracticProcessButton(Context context) {
		super(context);
	}

	public PracticProcessButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PracticProcessButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void drawProgress(Canvas canvas) {
		float scale = (float) getProgress() / (float) getMaxProgress();
		float indicatorHeight = (float) getMeasuredHeight() * scale;
		int top = (int) (getMeasuredHeight() - indicatorHeight);
		getProgressDrawable().setBounds(0, top, getMeasuredWidth(), getMeasuredHeight());
		Resources resource = getContext().getResources();
		if (getProgress() > 0 && getProgress() <= 25) {
			getProgressDrawable().setColor(resource.getColor(R.color.purple_progress));
		}
		if (getProgress() > 25 && getProgress() <= 50) {
			getProgressDrawable().setColor(resource.getColor(R.color.holo_green_light));
		}
		if (getProgress() > 50 && getProgress() <= 75) {
			getProgressDrawable().setColor(resource.getColor(R.color.holo_orange_light));
		}
		if (getProgress() > 75 && getProgress() <= 100) {
			getProgressDrawable().setColor(resource.getColor(R.color.holo_red_light));
		}
		getProgressDrawable().draw(canvas);
	}
}
