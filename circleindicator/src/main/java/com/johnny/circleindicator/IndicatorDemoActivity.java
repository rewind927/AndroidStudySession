package com.johnny.circleindicator;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.johnny.circleindicator.adapter.DemoPagerAdapter;
import com.johnny.circleindicator.animation.AnimationFactory;
import com.johnny.circleindicator.view.PracticeFlipIndicator;

import me.relex.circleindicator.CircleIndicator;

public class IndicatorDemoActivity extends AppCompatActivity {

	private ViewAnimator viewAnimator;
	private ImageView flip_img1;
	private ImageView flip_img2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewpager_activity);

		// PRACTICE INDICATOR
		ViewPager viewPager =
				(ViewPager) findViewById(R.id.viewpager_unselected_background);
		PracticeFlipIndicator practiceIndicator =
				(PracticeFlipIndicator) findViewById(R.id.indicator_unselected_background);
		DemoPagerAdapter pagerAdapter =
				new DemoPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		practiceIndicator.setViewPager(viewPager);

		// 3D FLIP
		viewAnimator = (ViewAnimator) findViewById(R.id.flip_include);
		flip_img1 = (ImageView) viewAnimator.findViewById(R.id.flip_img1);
		flip_img1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.RIGHT_LEFT);
			}
		});

		flip_img2 = (ImageView) viewAnimator.findViewById(R.id.flip_img2);
		flip_img2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AnimationFactory.flipTransition(viewAnimator, AnimationFactory.FlipDirection.RIGHT_LEFT);
			}
		});

		// CUSTOM
		ViewPager customViewpager = (ViewPager) findViewById(R.id.viewpager_custom);
		CircleIndicator customIndicator = (CircleIndicator) findViewById(R.id.indicator_custom);
		DemoPagerAdapter customPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
		customViewpager.setAdapter(customPagerAdapter);
		customIndicator.setViewPager(customViewpager);
		customViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int i, float v, int i2) {

			}

			@Override
			public void onPageSelected(int i) {
				Log.d("OnPageChangeListener", "Current selected = " + i);
			}

			@Override
			public void onPageScrollStateChanged(int i) {

			}
		});

		// DEFAULT
		ViewPager defaultViewpager = (ViewPager) findViewById(R.id.viewpager_default);
		CircleIndicator defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
		DemoPagerAdapter defaultPagerAdapter = new DemoPagerAdapter(getSupportFragmentManager());
		defaultViewpager.setAdapter(defaultPagerAdapter);
		defaultIndicator.setViewPager(defaultViewpager);
	}
}
