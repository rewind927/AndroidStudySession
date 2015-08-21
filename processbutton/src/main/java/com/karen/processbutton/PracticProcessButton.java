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
		//TODO Draw the progress
	}
}
