package com.jpardogo.android.googleprogressbar.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.jpardogo.android.googleprogressbar.R;

public class CustomCirclesDrawable extends Drawable implements Drawable.Callback {
	private final int MAX_LEVEL = 10000;
	private final int SWEEP_ANGLE = 30;
	private Path path;
	private RectF mainOval = new RectF();
	private Paint redPaint;
	private Paint bluePaint;
	private Paint yellowPaint;
	private Paint greenPaint;
	private Paint blackPaint;
	private int diameter;
	private int animationLevel;

	public CustomCirclesDrawable(int[] colors) {
		initPathPaints(colors);
	}

	private void initPathPaints(int[] colors) {
		path = new Path();

		blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		blackPaint.setColor(Color.BLACK);
		blackPaint.setAlpha(125);
		blackPaint.setAntiAlias(true);

		redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		redPaint.setColor(colors[0]);
		redPaint.setAntiAlias(true);

		bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		bluePaint.setColor(colors[1]);
		bluePaint.setAntiAlias(true);

		yellowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		yellowPaint.setColor(colors[2]);
		yellowPaint.setAntiAlias(true);

		greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		greenPaint.setColor(colors[3]);
		greenPaint.setAntiAlias(true);
	}

	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);
		measureCircleProgress(bounds.width(), bounds.height());
	}

	private void measureCircleProgress(int width, int height) {
		diameter = Math.min(width, height);
		mainOval.set(0, 0, diameter, diameter);
	}

	@Override
	protected boolean onLevelChange(int level) {
		animationLevel = (level == MAX_LEVEL ? 0 : level);
		return true;
	}

	public static class Builder {
		private int[] mColors;

		public Builder(Context context) {
			initDefaults(context);
		}

		private void initDefaults(Context context) {
			mColors = context.getResources().getIntArray(R.array.google_colors);
		}

		public Builder colors(int[] colors) {
			if (colors == null || colors.length == 0) {
				throw new IllegalArgumentException("Your color array must contains at least 4 values");
			}
			mColors = colors;
			return this;
		}

		public Drawable build() {
			return new CustomCirclesDrawable(mColors);
		}
	}

	@Override
	public void invalidateDrawable(Drawable who) {
		final Callback callback = getCallback();
		if (callback != null) {
			callback.invalidateDrawable(this);
		}
	}

	@Override
	public void scheduleDrawable(Drawable who, Runnable what, long when) {
		final Callback callback = getCallback();
		if (callback != null) {
			callback.scheduleDrawable(this, what, when);
		}
	}

	@Override
	public void unscheduleDrawable(Drawable who, Runnable what) {
		final Callback callback = getCallback();
		if (callback != null) {
			callback.unscheduleDrawable(this, what);
		}
	}

	@Override
	public void draw(Canvas canvas) {
		if (animationLevel != 0) {
			int startAngel = animationLevel * 360 / MAX_LEVEL;

			for (int i = 0; i < (360 / SWEEP_ANGLE); i++) {
				switch (i % 4) {
					case 0:
						canvas.drawArc(mainOval, startAngel + i * SWEEP_ANGLE, SWEEP_ANGLE, true, redPaint);
						break;
					case 1:
						//TODO.1 finish draw arc by bluePaint
						break;
					case 2:
						//TODO.2 finish draw arc by yellowPaint
						break;
					case 3:
						//TODO.3 finish draw arc by greenPaint
						break;
				}
			}
			path.reset();
			//TODO.4 finish reset path by moveTo()
			//TODO.5 finish reset path by arcTo()
			canvas.drawPath(path, blackPaint);
		}
	}

	@Override
	public void setAlpha(int alpha) {}

	@Override
	public void setColorFilter(ColorFilter cf) {}

	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}
}