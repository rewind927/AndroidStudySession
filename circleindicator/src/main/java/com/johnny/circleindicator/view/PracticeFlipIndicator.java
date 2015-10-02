package com.johnny.circleindicator.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.johnny.circleindicator.R;
import com.johnny.circleindicator.animation.AnimationFactory;

import java.util.ArrayList;

import static android.support.v4.view.ViewPager.OnPageChangeListener;

public class PracticeFlipIndicator extends LinearLayout implements OnPageChangeListener {

	private final static int DEFAULT_INDICATOR_WIDTH = 5;
	private ViewPager mViewpager;
	private int mIndicatorMargin = -1;
	private int mIndicatorWidth = -1;
	private int mIndicatorHeight = -1;
	// TODO Don't need mAnimatorResId & mAnimatorReverseResId
	// TODO Don't need mAnimatorReverseResId
	// TODO Don't need mIndicatorBackgroundResId
	// TODO Don't need mIndicatorUnselectedBackgroundResId
	private int mCurrentPosition = 0;
	// TODO Don't need mAnimationOut
	// TODO Don't need mAnimationIn
	// TODO Add ViewFilpIcon's ArrayList
	private ArrayList<ViewFilpIcon> filpIcons = new ArrayList<>();

	public PracticeFlipIndicator(Context context) {
		super(context);
		init(context, null);
	}

	public PracticeFlipIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		setOrientation(LinearLayout.HORIZONTAL);
		setGravity(Gravity.CENTER);
		handleTypedArray(context, attrs);
		checkIndicatorConfig(context);
	}

	private void handleTypedArray(Context context, AttributeSet attrs) {
		if (attrs == null) {
			return;
		}

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
		mIndicatorWidth =
				typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_width, -1);
		mIndicatorHeight =
				typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_height, -1);
		mIndicatorMargin =
				typedArray.getDimensionPixelSize(R.styleable.CircleIndicator_ci_margin, -1);
		typedArray.recycle();
	}

	/**
	 * Create and configure Indicator in Java code.
	 */
	public void configureIndicator(int indicatorWidth, int indicatorHeight, int indicatorMargin) {
		configureIndicator(indicatorWidth, indicatorHeight, indicatorMargin,
				R.animator.scale_with_alpha, 0, R.drawable.white_radius, R.drawable.white_radius);
	}

	public void configureIndicator(int indicatorWidth, int indicatorHeight, int indicatorMargin,
	                               @AnimatorRes int animatorId, @AnimatorRes int animatorReverseId,
	                               @DrawableRes int indicatorBackgroundId,
	                               @DrawableRes int indicatorUnselectedBackgroundId) {

		mIndicatorWidth = indicatorWidth;
		mIndicatorHeight = indicatorHeight;
		mIndicatorMargin = indicatorMargin;

		checkIndicatorConfig(getContext());
	}

	private void checkIndicatorConfig(Context context) {
		mIndicatorWidth = (mIndicatorWidth < 0) ? dip2px(DEFAULT_INDICATOR_WIDTH) : mIndicatorWidth;
		mIndicatorHeight =
				(mIndicatorHeight < 0) ? dip2px(DEFAULT_INDICATOR_WIDTH) : mIndicatorHeight;
		mIndicatorMargin =
				(mIndicatorMargin < 0) ? dip2px(DEFAULT_INDICATOR_WIDTH) : mIndicatorMargin;
	}

	public void setViewPager(ViewPager viewPager) {
		mViewpager = viewPager;
		mCurrentPosition = mViewpager.getCurrentItem();
		createIndicators(viewPager);
		mViewpager.removeOnPageChangeListener(this);
		mViewpager.addOnPageChangeListener(this);
		onPageSelected(mCurrentPosition);
	}

	/**
	 * @deprecated User ViewPager addOnPageChangeListener
	 */
	@Deprecated
	public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
		if (mViewpager == null) {
			throw new NullPointerException("can not find Viewpager , setViewPager first");
		}
		mViewpager.removeOnPageChangeListener(onPageChangeListener);
		mViewpager.addOnPageChangeListener(onPageChangeListener);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {

		if (mViewpager.getAdapter() == null || mViewpager.getAdapter().getCount() <= 0) {
			return;
		}

		// TODO Edit to Previous ViewFilpIcon
		ViewFilpIcon currentIndicator = filpIcons.get(mCurrentPosition);
		// TODO Filp it!
		currentIndicator.doAnimation();

		// TODO Edit to ViewFilpIcon
		ViewFilpIcon selectedIndicator = filpIcons.get(position);
		// TODO Filp it!
		selectedIndicator.doAnimation();

		mCurrentPosition = position;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	private void createIndicators(ViewPager viewPager) {
		removeAllViews();
		if (viewPager.getAdapter() == null) {
			return;
		}

		int count = viewPager.getAdapter().getCount();
		if (count <= 0) {
			return;
		}
		addIndicator(true);
		for (int i = 1; i < count; i++) {
			addIndicator(false);
		}
	}

	private void addIndicator(boolean selected) {
		// TODO Edit to ViewFilpIcon
		ViewFilpIcon indicator = new ViewFilpIcon(getContext());
		addView(indicator.viewRoot, mIndicatorWidth, mIndicatorHeight);
		LayoutParams lp = (LayoutParams) indicator.viewRoot.getLayoutParams();
		lp.leftMargin = mIndicatorMargin;
		lp.rightMargin = mIndicatorMargin;
		indicator.viewRoot.setLayoutParams(lp);


		// TODO Filp at the first selected item
		if (selected) {
			indicator.showNext();
		}

		// TODO Add to ArrayList
		filpIcons.add(indicator);
	}

	private class ReverseInterpolator implements Interpolator {
		@Override
		public float getInterpolation(float value) {
			return Math.abs(1.0f - value);
		}
	}

	public int dip2px(float dpValue) {
		final float scale = getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	// TODO Custom view holder
	class ViewFilpIcon {
		ViewFlipper viewRoot;
		ImageView imageFront;
		ImageView imageBack;

		public ViewFilpIcon(Context context) {
			LayoutInflater inflater = LayoutInflater.from(context);
			viewRoot = (ViewFlipper) inflater.inflate(R.layout.include_flip, null);
			imageFront = (ImageView) viewRoot.findViewById(R.id.flip_img1);
			imageBack = (ImageView) viewRoot.findViewById(R.id.flip_img2);
		}

		protected void doAnimation() {
			// TODO doAnimation
			AnimationFactory.flipTransition(viewRoot, AnimationFactory.FlipDirection.RIGHT_LEFT, 200);
		}

		protected void showNext() {
			// TODO showNext
			viewRoot.showNext();
		}
	}
}
