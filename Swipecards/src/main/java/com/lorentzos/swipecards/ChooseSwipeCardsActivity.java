package com.lorentzos.swipecards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseSwipeCardsActivity extends Activity {

	private final View.OnClickListener swipeCardsOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent(ChooseSwipeCardsActivity.this, SwipeCardsActivity.class));
		}
	};

	private final View.OnClickListener practiceOnClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent(ChooseSwipeCardsActivity.this, PracticeSwipeCardsActivity.class));
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose);
		findViewById(R.id.swipe_cards).setOnClickListener(swipeCardsOnClickListener);
		findViewById(R.id.practice).setOnClickListener(practiceOnClickListener);
	}


}
