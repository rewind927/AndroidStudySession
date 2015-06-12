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
	private int progressBarSize;
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
			// TODO: get attributes .....


		} finally {
			a.recycle();
		}
		// initial paint
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
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// TODO: setMeasuredDimension .....


	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// TODO: [tips] canvas.drawArc(reachedOval, -90, angel, false, progressReachedPaint);


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
