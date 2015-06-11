package com.widget.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.daimajia.numberprogressbar.OnProgressBarListener;
import com.daimajia.numberprogressbar.example.R;

public class CircleProgressBar extends View {

	private int progress;
	private int max;
	private int progressUnreachedColor;
	private int progressReachedColor;
	private int progressUnreachedStroke;
	private int progressReachedStroke;
	private int progressBarCircleSize;
	private int progressTextSize;
	private int progressTextColor;
	private Paint textPaint;
	private Paint progressReachedPaint;
	private Paint progressUnreachedPaint;
	private OnProgressBarListener listener;

	public CircleProgressBar(Context context) {
		super(context);
	}

	public CircleProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
		try {
			progress = a.getInt(R.styleable.CircleProgressBar_circle_progress, 0);
			max = a.getInt(R.styleable.CircleProgressBar_circle_max, 100);
			progressUnreachedColor = a.getColor(R.styleable.CircleProgressBar_circle_progress_unreached_color, 0xFFCCCCCC);
			progressReachedColor = a.getColor(R.styleable.CircleProgressBar_circle_progress_reached_color, 0xFF3498DB);
			progressUnreachedStroke = a.getDimensionPixelSize(R.styleable.CircleProgressBar_circle_progress_unreached_stroke, (int) dp2px(6));
			progressReachedStroke = a.getDimensionPixelSize(R.styleable.CircleProgressBar_circle_progress_reached_stroke, (int) dp2px(6));
			progressBarCircleSize = a.getDimensionPixelSize(R.styleable.CircleProgressBar_circle_progress_bar_circle_size, (int) dp2px(100));
			progressTextSize = a.getDimensionPixelSize(R.styleable.CircleProgressBar_circle_progress_text_size, (int) sp2px(14));
			progressTextColor = a.getColor(R.styleable.CircleProgressBar_circle_progress_text_color, 0xFF000000);
		} finally {
			a.recycle();
		}
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(progressTextSize);
		textPaint.setColor(progressTextColor);
		progressUnreachedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		progressUnreachedPaint.setColor(progressUnreachedColor);
		progressUnreachedPaint.setStyle(Paint.Style.STROKE);
		progressReachedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		progressReachedPaint.setColor(progressReachedColor);
		progressReachedPaint.setStyle(Paint.Style.STROKE);
		progressReachedPaint.setStrokeWidth(progressReachedStroke);
	}

	@Override
	public int getMinimumHeight() {
		return Math.max(progressTextSize, progressBarCircleSize);
	}

	@Override
	public int getMinimumWidth() {
		return Math.max(progressTextSize, progressBarCircleSize);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measure(widthMeasureSpec, true), measure(heightMeasureSpec, false));
	}

	private int measure(int measureSpec, boolean isWidth) {
		int result;
		int size = MeasureSpec.getSize(measureSpec);
		int mode = MeasureSpec.getMode(measureSpec);
		int padding = isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom();
		if (mode == MeasureSpec.EXACTLY) {
			result = size;
		} else {
			result = isWidth ? getSuggestedMinimumWidth() : getSuggestedMinimumHeight();
			result += padding;
			if (mode == MeasureSpec.AT_MOST) {
				result = Math.max(result, size);
			}
		}
		return result;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		final float angel = 360 * progress / max;

		RectF unreachedOval = new RectF(getPaddingLeft() + progressUnreachedStroke / 2,
				getPaddingTop() + progressUnreachedStroke / 2,
				getPaddingLeft() + progressBarCircleSize + progressUnreachedStroke,
				getPaddingTop() + progressBarCircleSize + progressUnreachedStroke);
		canvas.drawArc(unreachedOval, angel - 90, 360 - angel, false, progressUnreachedPaint);

		RectF reachedOval = new RectF(getPaddingLeft() + progressReachedStroke / 2,
				getPaddingTop() + progressReachedStroke / 2,
				getPaddingLeft() + progressBarCircleSize + progressReachedStroke,
				getPaddingTop() + progressBarCircleSize + progressReachedStroke);

		final int reachedColor = adjustAlpha(progressReachedColor, (255 * progress / (float) max) / 255);
		progressReachedPaint.setColor(reachedColor);
		canvas.drawArc(reachedOval, -90, angel, false, progressReachedPaint);

		final int radius = progressBarCircleSize / 2;
		final int centerX = getPaddingLeft() + radius;
		final int centerY = getPaddingTop() + radius;
		final float textWidth = textPaint.measureText(progress + "");
		final float textHeight = textPaint.ascent() + textPaint.descent();
		canvas.drawText(progress + " %", centerX - textWidth / 2, centerY - textHeight / 2, textPaint);
	}

	public void setProgressBarListener(OnProgressBarListener listener) {
		this.listener = listener;
	}

	public void incrementProgressBy(int by) {
		if (by > 0) {
			setProgress(progress + by);
		}
		if(listener != null){
			listener.onProgressChange(progress, max);
		}
	}

	public void setProgress(int progress) {
		if (progress <= max && progress >= 0) {
			this.progress = progress;
			invalidate();
		}
	}

	private int adjustAlpha(int color, float factor) {
		int alpha = Math.round(Color.alpha(color) * factor);
		int red = Color.red(color);
		int green = Color.green(color);
		int blue = Color.blue(color);
		return Color.argb(alpha, red, green, blue);
	}

	private float dp2px(float dp) {
		final float scale = getResources().getDisplayMetrics().density;
		return dp * scale + 0.5f;
	}

	private float sp2px(float sp) {
		final float scale = getResources().getDisplayMetrics().scaledDensity;
		return sp * scale;
	}

}
